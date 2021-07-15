package com.github.johnnysc.holybibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Asatryan on 15.07.2021
 **/
abstract class BaseFactory<T : ViewModel>(private val module: BaseModule<T>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = module.getViewModel() as T
}