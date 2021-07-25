package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomain

/**
 * @author Asatryan on 11.07.2021
 **/
abstract class ChaptersDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Pair<List<ChapterData>, String>, T>()