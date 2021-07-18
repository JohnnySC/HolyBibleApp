package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUi

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDomainToUiMapper<T> : Abstract.Mapper.Data<ChapterId, T>