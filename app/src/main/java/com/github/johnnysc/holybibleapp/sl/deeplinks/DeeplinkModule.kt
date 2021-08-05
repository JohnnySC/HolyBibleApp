package com.github.johnnysc.holybibleapp.sl.deeplinks

import com.github.johnnysc.holybibleapp.data.verse.DeeplinkIds
import com.github.johnnysc.holybibleapp.data.verse.VerseIdCache
import com.github.johnnysc.holybibleapp.presentation.books.BookCache
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterCache
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkViewModel
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkModule(private val coreModule: CoreModule) : BaseModule<DeeplinkViewModel> {

    override fun viewModel() = coreModule.resourceProvider.let { resourceProvider ->
        DeeplinkViewModel(
            resourceProvider,
            DeeplinkIds(
                BookCache.Deeplink(resourceProvider),
                ChapterCache.Deeplink(resourceProvider),
                VerseIdCache.Deeplink(resourceProvider),
                coreModule.deeplinkData
            ),
            NavigationCommunication.Base()
        )
    }
}