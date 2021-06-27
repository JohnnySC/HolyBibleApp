package com.github.johnnysc.holybibleapp.data.net

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.google.gson.annotations.SerializedName

/**
 * {"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
 * @author Asatryan on 26.06.2021
 **/
data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<Book, BookCloudMapper>() {
    override fun map(mapper: BookCloudMapper) = mapper.map(id, name)
}