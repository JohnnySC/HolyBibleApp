package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.google.gson.annotations.SerializedName

/**
 * {"id":1}
 * @author Asatryan on 11.07.2021
 **/
interface ChapterCloud : Abstract.CloudObject {

    fun <T> map(mapper: ToChapterMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int
    ) : ChapterCloud {
        override fun <T> map(mapper: ToChapterMapper<T>) = mapper.map(id)
    }
}