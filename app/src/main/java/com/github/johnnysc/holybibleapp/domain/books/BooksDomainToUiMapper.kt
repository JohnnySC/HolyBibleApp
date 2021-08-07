package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksDomainToUiMapper<T> : Abstract.Mapper.DomainToUi<List<BookDomain>, T>