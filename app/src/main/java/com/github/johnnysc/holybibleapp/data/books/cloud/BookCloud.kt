package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.google.gson.annotations.SerializedName

/**
 * {"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
 * @author Asatryan on 26.06.2021
 **/
interface BookCloud : Abstract.CloudObject, Matcher<Int> {
    fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("name")
        private val name: String,
        @SerializedName("testament")
        private val testament: String
    ) : BookCloud {
        override fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean) =
            mapper.map(id, name, testament, isFavorite)

        override fun matches(arg: Int) = arg == id
    }
}