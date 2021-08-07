package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.books.BookDomain

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersDomainToUiMapper<T> :
    Abstract.Mapper.DomainToUi<Pair<List<ChapterDomain>, BookDomain>, T>