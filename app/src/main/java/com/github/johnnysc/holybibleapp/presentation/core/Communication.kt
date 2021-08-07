package com.github.johnnysc.holybibleapp.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ChangeFavorite

/**
 * @author Asatryan on 12.07.2021
 **/
interface Communication<T> : Observe<T>, Abstract.Mapper.Data<T, Unit> {

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