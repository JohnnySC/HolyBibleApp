package com.github.johnnysc.holybibleapp.presentation.deeplink

import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkViewModel(
    resourceProvider: ResourceProvider,
    private val saveIds: Save<String>,
    private val communication: NavigationCommunication
) : BaseViewModel(resourceProvider) {

    fun init(intent: Intent?) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = intent?.data.toString()
            saveIds.save(data)
            withContext(Dispatchers.Main) {
                communication.map(1)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }
}