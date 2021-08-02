package com.github.johnnysc.holybibleapp.presentation.main

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 24.07.2021
 **/
abstract class BaseViewModel(private val resourceProvider: ResourceProvider) : ViewModel(),
    ScrollPosition {

    fun title(): String = resourceProvider.string(titleResId())

    @StringRes
    open fun titleResId(): Int = R.string.loading

    protected fun changeFavorite(
        id: Int,
        communication: ChangeFavorite<Int>,
        vararg changeFavorite: ChangeFavorite<Int>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            changeFavorite.forEach {
                it.changeFavorite(id)
            }
            withContext(Dispatchers.Main) {
                communication.changeFavorite(id)
            }
        }
    }
}

interface ScrollPosition {

    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}