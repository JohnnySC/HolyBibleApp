package com.github.johnnysc.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCommunication : Abstract.Mapper {
    fun map(books: List<BookUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>)

    class Base : BooksCommunication {
        private val listLiveData = MutableLiveData<List<BookUi>>()
        override fun map(books: List<BookUi>) {
            listLiveData.value = books
        }
        override fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
            listLiveData.observe(owner, observer)
        }
    }
}