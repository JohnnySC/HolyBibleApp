package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCommunication : Communication<List<BookUi>> {

    class Base : Communication.Base<List<BookUi>>(), BooksCommunication
}