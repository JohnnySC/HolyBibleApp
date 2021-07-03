package com.github.johnnysc.holybibleapp.data


/**
 * @author Asatryan on 29.06.2021
 **/
abstract class BaseBooksRepositoryTest {

    protected class TestToBookMapper : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id, name)
    }
}