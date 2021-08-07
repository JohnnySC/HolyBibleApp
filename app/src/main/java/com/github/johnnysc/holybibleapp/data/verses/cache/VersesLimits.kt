package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.data.core.Limits

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesLimits(
    private val bookId: Int,
    private val chapterId: Int,
    private val million: Multiply,
    private val thousand: Multiply,
) : Limits {

    override fun min() = million.map(bookId) + thousand.map(chapterId)
    override fun max() = million.map(bookId) + thousand.map(chapterId + 1)
}