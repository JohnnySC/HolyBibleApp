package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.google.gson.annotations.SerializedName

/**
 * {"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
 * @author Asatryan on 26.06.2021
 **/
data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("testament")
    private val testament: String,
) : Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper) = mapper.map(id, name, testament)
}