package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomain

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDataToDomainMapper<T> : Abstract.Mapper.Data<ChapterId, T>