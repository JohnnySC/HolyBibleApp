package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.core.Book

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDb>

    fun saveBooks(books: List<Book>)

    class Base(private val realmProvider: RealmProvider) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<Book>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                books.forEach { book ->
                    val bookDb = it.createObject(BookDb::class.java, book.id)
                    bookDb.name = book.name
                }
            }
        }
    }
}