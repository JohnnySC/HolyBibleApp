package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.books.BooksDomain

/**
 * @author Asatryan on 11.07.2021
 **/
abstract class BooksDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>()