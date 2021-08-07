package com.github.johnnysc.holybibleapp.sl.core

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.data.books.cloud.RussianTranslation
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 02.08.2021
 **/
class RussianBooksContainer(
    private val coreModule: CoreModule, private val typeToken: TypeToken<*>,
) : BooksRuProvider {
    override fun booksRu(): List<BookRu> {
        val text = coreModule.resourceProvider.readText(R.raw.synodal)
        val response = coreModule.gson.fromJson<RussianTranslation>(text, typeToken.type)
        return response.contentAsList();
    }
}

class RussianBooksTypeToken : TypeToken<RussianTranslation>()

interface BooksRuProvider {
    fun booksRu(): List<BookRu>
}

interface ClearRussianBooks {
    fun clearBooksRu()
}