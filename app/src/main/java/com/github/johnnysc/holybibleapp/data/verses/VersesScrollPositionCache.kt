package com.github.johnnysc.holybibleapp.data.verses

/**
 * @author Asatryan on 28.07.2021
 **/
interface VersesScrollPositionCache {
    fun saveVersesScrollPosition(position: Int)
    fun versesScrollPosition(): Int
}