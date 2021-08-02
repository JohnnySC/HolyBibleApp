package com.github.johnnysc.holybibleapp.core

import io.realm.RealmObject

/**
 * @author Asatryan on 17.07.2021
 **/
interface Repository<E : Abstract.DataObject> : ChangeFavorite<Int> {
    suspend fun fetchData(): E

    abstract class Base<T : RealmObject,
            C : Abstract.CloudObject,
            D : Abstract.DataObject,
            E : Abstract.DataObject>(
        private val cacheDataSource: CacheDataSource<D>,
        private val cloudMapper: Abstract.Mapper.Data<Pair<List<C>, List<Int>>, List<D>>,
        private val cacheMapper: Abstract.Mapper.Data<Pair<List<T>, List<Int>>, List<D>>
    ) : Repository<E> {

        override suspend fun fetchData(): E = try {
            val cachedList = cachedDataList()
            val favoriteList = cacheDataSource.favorites(limits())
            if (cachedList.isEmpty()) {
                val cloudList = fetchCloudData()
                val list = cloudMapper.map(Pair(cloudList, favoriteList))
                cacheDataSource.save(list)
                returnSuccess(list)
            } else {
                returnSuccess(cacheMapper.map(Pair(cachedList, favoriteList)))
            }
        } catch (e: Exception) {
            returnFail(e)
        }

        protected abstract suspend fun fetchCloudData(): List<C>
        protected abstract fun cachedDataList(): List<T>
        protected abstract fun returnSuccess(list: List<D>): E
        protected abstract fun returnFail(e: Exception): E
        protected abstract fun limits(): Limits
        override fun changeFavorite(id: Int) = cacheDataSource.changeFavorite(id)
    }
}