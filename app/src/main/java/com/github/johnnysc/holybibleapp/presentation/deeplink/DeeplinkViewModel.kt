package com.github.johnnysc.holybibleapp.presentation.deeplink

import android.content.Intent
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
    communication: NavigationCommunication,
) : BaseViewModel<NavigationCommunication, Int>(resourceProvider, communication) {

    private var data = ""

    override fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            saveIds.save(data)
            withContext(Dispatchers.Main) { communication.map(1) }
        }
    }

    fun init(intent: Intent?) {
        data = intent?.data.toString()
        fetch()
    }
}