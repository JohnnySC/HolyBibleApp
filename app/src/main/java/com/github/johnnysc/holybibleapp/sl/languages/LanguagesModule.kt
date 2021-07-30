package com.github.johnnysc.holybibleapp.sl.languages

import com.github.johnnysc.holybibleapp.presentation.languages.Language
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesCommunication
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesViewModel
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 18.07.2021
 **/
class LanguagesModule(
    private val coreModule: CoreModule
) : BaseModule<LanguagesViewModel> {

    override fun viewModel() = LanguagesViewModel(
        LanguagesCommunication.Base(),
        Language.Change(coreModule.language, coreModule.realmProvider, coreModule.resourceProvider),
        coreModule.navigationCommunication,
        coreModule.navigator,
        coreModule.resourceProvider
    )
}