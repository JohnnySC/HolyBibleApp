package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCommunication : Communication<BooksUi>, ChangeFavorite<Int> {
    class Base : Communication.Base.Favorites<BooksUi>(), BooksCommunication
}