package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Limits

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesLimits(
    private val bookId: Int,
    private val chapterId: Int
) : Limits {
    override fun min(): Int {
        return MULTIPLY * (MULTIPLY * bookId + chapterId)
    }

    override fun max(): Int {
        return MULTIPLY * (MULTIPLY * bookId + chapterId + 1)
    }

    private companion object {
        const val MULTIPLY = 1000
    }
}