package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.core.CacheDataSource
import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.core.RealmProvider
import io.realm.Realm

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheDataSource : Read<List<BookDb>>, CacheDataSource<BookData> {

    class Base(
        realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper<BookDb>,
    ) : CacheDataSource.Abstract<BookData>(realmProvider), BooksCacheDataSource {

        override fun read(): List<BookDb> = realmProvider.provide().use { realm ->
            val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
            return realm.copyFromRealm(booksDb)
        }

        override fun save(data: List<BookData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction { val dbWrapper = BookDbWrapper(it)
                data.forEach { book -> book.map(mapper, dbWrapper) }
            }
        }

        private inner class BookDbWrapper(realm: Realm) : DbWrapper.Base<BookDb>(realm) {
            override fun dbClass() = BookDb::class.java
        }
    }
}