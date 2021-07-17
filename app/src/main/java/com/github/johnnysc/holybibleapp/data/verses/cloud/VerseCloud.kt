package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.google.gson.annotations.SerializedName

/**
 * {"id":1001001,"verseId":1,"verse":"In the beginning God created the heaven and the earth."}
 * @author Asatryan on 17.07.2021
 **/
class VerseCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("verseId")
    private val verseId: Int,
    @SerializedName("verse")
    private val text: String
) : Abstract.Object<VerseData, ToVerseMapper> {
    override fun map(mapper: ToVerseMapper) = mapper.map(id, verseId, text)
}