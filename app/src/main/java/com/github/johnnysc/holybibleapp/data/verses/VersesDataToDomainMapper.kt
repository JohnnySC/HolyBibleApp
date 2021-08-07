package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain<Triple<List<VerseData>, BookData, Pair<Int, Boolean>>, T>