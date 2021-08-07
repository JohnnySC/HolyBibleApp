package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.books.BookDomain

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesDomainToUiMapper<T> :
    Abstract.Mapper.DomainToUi<Triple<List<VerseDomain>, BookDomain, Int>, T>