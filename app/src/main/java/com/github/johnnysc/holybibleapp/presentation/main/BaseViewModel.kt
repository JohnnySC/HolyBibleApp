package com.github.johnnysc.holybibleapp.presentation.main

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.core.Communication
import com.github.johnnysc.holybibleapp.presentation.core.Observe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 24.07.2021
 **/
abstract class BaseViewModel<E : Communication<T>, T>(
    private val resourceProvider: ResourceProvider,
    protected val communication: E,
) : ViewModel(), ScrollPositionUi, Observe<T> {

    abstract fun fetch()

    fun title(): String = resourceProvider.string(titleResId())

    @StringRes
    open fun titleResId(): Int = R.string.loading

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
        communication.observe(owner, observer)

    protected fun changeFavorite(
        id: Int, communication: ChangeFavorite<Int>, vararg changeFavorite: ChangeFavorite<Int>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            changeFavorite.forEach { it.changeFavorite(id) }
            withContext(Dispatchers.Main) { communication.changeFavorite(id) }
        }
    }
}

interface ScrollPositionUi {

    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}