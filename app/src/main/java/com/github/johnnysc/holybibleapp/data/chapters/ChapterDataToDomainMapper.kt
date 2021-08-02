package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDataToDomainMapper<T> : Abstract.Mapper.Data<Pair<ChapterId, Boolean>, T>