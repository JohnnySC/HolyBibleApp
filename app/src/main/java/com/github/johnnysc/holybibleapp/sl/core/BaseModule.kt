package com.github.johnnysc.holybibleapp.sl.core

import androidx.lifecycle.ViewModel

/**
 * @author Asatryan on 15.07.2021
 **/
interface BaseModule<T : ViewModel> {

    fun viewModel(): T
}