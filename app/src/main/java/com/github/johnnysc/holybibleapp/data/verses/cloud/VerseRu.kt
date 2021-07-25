package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 20.07.2021
 **/
data class VerseRu(@SerializedName("verse") private val verse: String) {

    fun toVerseCloud(bookId: Int, chapterId: Int, id: Int): VerseCloud.Base {
        val finalId = MULTIPLY * MULTIPLY * bookId + MULTIPLY * chapterId + id
        return VerseCloud.Base(finalId, id, verse)
    }

    private companion object {
        const val MULTIPLY = 1000
    }
}