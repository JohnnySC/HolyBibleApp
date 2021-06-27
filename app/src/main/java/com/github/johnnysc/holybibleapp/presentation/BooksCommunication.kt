package com.github.johnnysc.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.holybibleapp.core.Book

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCommunication {
    fun show(books: List<Book>)
    fun show(errorMessage: String)

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base : BooksCommunication {
        private val successLiveData = MutableLiveData<List<Book>>()
        private val failLiveData = MutableLiveData<String>()

        override fun show(books: List<Book>) {
            successLiveData.value = books
        }

        override fun show(errorMessage: String) {
            failLiveData.value = errorMessage
        }

        override fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>) {
            successLiveData.observe(owner, observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner, observer)
        }
    }
}