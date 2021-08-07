package com.github.johnnysc.holybibleapp.presentation.deeplink

import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
import com.github.johnnysc.holybibleapp.presentation.verses.VersesCommunication
import com.github.johnnysc.holybibleapp.presentation.verses.VersesNavigator
import com.github.johnnysc.holybibleapp.presentation.verses.VersesUi
import com.github.johnnysc.holybibleapp.presentation.verses.VersesViewModel
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkVersesViewModel(
    navigator: VersesNavigator,
    interactor: VersesInteractor,
    communication: VersesCommunication,
    mapper: VersesDomainToUiMapper<VersesUi>,
    deeplinkData: DeeplinkData,
    resourceProvider: ResourceProvider,
    multiply: Multiply,
) : VersesViewModel(
    navigator, interactor, communication, mapper, deeplinkData, resourceProvider, multiply) {
    override val feature = Feature.DEEPLINK_VERSES
    override fun init() = fetch()
}