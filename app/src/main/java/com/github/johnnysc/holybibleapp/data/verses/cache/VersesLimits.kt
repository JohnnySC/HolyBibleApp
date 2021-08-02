package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Limits
import com.github.johnnysc.holybibleapp.core.Multiply

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesLimits(
    private val bookId: Int,
    private val chapterId: Int
) : Limits {
    private val million = Multiply(2)
    private val thousand = Multiply(1)

    override fun min() = million.map(bookId) + thousand.map(chapterId)
    override fun max() = million.map(bookId) + thousand.map(chapterId + 1)
}