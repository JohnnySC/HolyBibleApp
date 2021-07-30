package com.github.johnnysc.holybibleapp.presentation.main

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider

/**
 * @author Asatryan on 24.07.2021
 **/
abstract class BaseViewModel(private val resourceProvider: ResourceProvider) : ViewModel(),
    ScrollPosition {

    fun title(): String = resourceProvider.string(titleResId())

    @StringRes
    open fun titleResId(): Int = R.string.loading
}

interface ScrollPosition {

    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}