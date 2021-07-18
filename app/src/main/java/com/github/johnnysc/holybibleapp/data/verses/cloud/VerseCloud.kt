package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.google.gson.annotations.SerializedName

/**
 * {"id":1001001,"verseId":1,"verse":"In the beginning God created the heaven and the earth."}
 * @author Asatryan on 17.07.2021
 **/
interface VerseCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToVerseMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("verseId")
        private val verseId: Int,
        @SerializedName("verse")
        private val text: String
    ) : VerseCloud {
        override fun <T> map(mapper: ToVerseMapper<T>) = mapper.map(id, verseId, text)
    }
}