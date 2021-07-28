package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 20.07.2021
 **/
data class VerseRu(@SerializedName("verse") private val text: String) :
    Abstract.Object<VerseCloud, VerseToWrapperMapper> {
    override fun map(mapper: VerseToWrapperMapper) = mapper.map(text)
}

data class VerseRuWrapper(
    private val finalId: Int,
    private val id: Int,
    private val verse: String,
) : VerseCloud {
    override fun <T> map(mapper: ToVerseMapper<T>): T {
        return mapper.map(finalId, id, verse)
    }
}