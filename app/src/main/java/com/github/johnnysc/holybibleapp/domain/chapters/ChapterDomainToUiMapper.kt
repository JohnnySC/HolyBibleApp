package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDomainToUiMapper<T> : Abstract.Mapper.Data<Pair<ChapterId, Boolean>, T>