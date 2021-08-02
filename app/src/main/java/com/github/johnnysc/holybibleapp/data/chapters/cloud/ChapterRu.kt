package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Content
import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VerseRu
import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 20.07.2021
 **/
data class ChapterRu(
    @SerializedName("chapter_nr") private val number: Int,
    @SerializedName("chapter") private val content: Map<String, VerseRu>
) : ChapterCloud, Matcher<Int>, Content<Pair<Int, VerseRu>> {
    override fun matches(arg: Int) = number == arg
    override fun contentAsList() = content.map { (key, value) -> Pair(key.toInt(), value) }
    override fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean) =
        mapper.map(number, isFavorite)
}