package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper


/**
 * @author Asatryan on 29.06.2021
 **/
abstract class BaseBooksRepositoryTest {

    protected class TestToBookMapper : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            BookData.Base(id, name, testament, isFavorite)
    }
}