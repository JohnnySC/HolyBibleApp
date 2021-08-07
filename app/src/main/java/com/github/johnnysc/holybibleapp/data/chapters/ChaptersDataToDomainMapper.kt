package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain<Pair<List<ChapterData>, BookData>, T>