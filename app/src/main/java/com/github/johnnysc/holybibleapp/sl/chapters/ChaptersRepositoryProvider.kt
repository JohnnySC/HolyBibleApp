package com.github.johnnysc.holybibleapp.sl.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository

/**
 * @author Asatryan on 30.07.2021
 **/
interface ChaptersRepositoryProvider {

    fun chaptersRepository(): ChaptersRepository
}