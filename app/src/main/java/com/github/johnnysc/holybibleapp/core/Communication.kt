package com.github.johnnysc.holybibleapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author Asatryan on 12.07.2021
 **/
interface Communication<T> {
    fun map(data: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T : Any> : Communication<T> {
        protected val liveData = MutableLiveData<T>()
        override fun map(data: T) {
            liveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)

        abstract class Favorites<T : ChangeFavorite<Int>> : Base<T>(), ChangeFavorite<Int> {
            override fun changeFavorite(id: Int) {
                liveData.value?.changeFavorite(id)
                liveData.value?.let { map(it) }
            }
        }
    }
}