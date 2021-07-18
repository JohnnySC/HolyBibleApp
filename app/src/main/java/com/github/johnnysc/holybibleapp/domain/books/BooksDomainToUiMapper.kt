package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.books.BooksUi

/**
 * @author Asatryan on 27.06.2021
 **/
abstract class BooksDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BookDomain>, T>(resourceProvider)