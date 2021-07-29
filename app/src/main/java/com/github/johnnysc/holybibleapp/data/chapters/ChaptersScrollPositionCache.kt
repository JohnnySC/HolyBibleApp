package com.github.johnnysc.holybibleapp.data.chapters

/**
 * @author Asatryan on 28.07.2021
 **/
interface ChaptersScrollPositionCache {
    fun saveChaptersScrollPosition(position: Int)
    fun chaptersScrollPosition(): Int
}