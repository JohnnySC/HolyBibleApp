package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper


/**
 * @author Asatryan on 29.06.2021
 **/
abstract class BaseBooksRepositoryTest {

    protected class TestToBookMapper : ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}