package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDbMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDb>

    fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<BookData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                books.forEach { book ->
                    book.mapTo(mapper, it)
                }
            }
        }
    }
}