package com.github.johnnysc.holybibleapp.data.core

import com.github.johnnysc.holybibleapp.core.Abstract.DataObject
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.Save
import io.realm.Realm

/**
 * @author Asatryan on 17.07.2021
 **/
interface CacheDataSource<D : DataObject> : Save<List<D>>, Favorites, ChangeFavorite<Int> {

    abstract class Abstract<D : DataObject>(protected val realmProvider: RealmProvider) :
        CacheDataSource<D> {

        override fun favorites(limits: Limits): List<Int> =
            realmProvider.provide(true).use { realm ->
                val favorites = realm.where(FavoriteDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return favorites.map { it.id }
            }

        override fun changeFavorite(id: Int) = realmProvider.provide(true).use {
            val favoriteItem = it.where(FavoriteDb::class.java).equalTo("id", id).findFirst()
            it.executeTransaction { realm ->
                if (favoriteItem == null)
                    FavoriteDbWrapper(realm).createObject(id)
                else
                    favoriteItem.deleteFromRealm()
            }
        }

        private inner class FavoriteDbWrapper(realm: Realm) : DbWrapper.Base<FavoriteDb>(realm) {
            override fun dbClass() = FavoriteDb::class.java
        }
    }
}