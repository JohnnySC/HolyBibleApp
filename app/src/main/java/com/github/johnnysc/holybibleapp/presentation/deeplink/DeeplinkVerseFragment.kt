package com.github.johnnysc.holybibleapp.presentation.deeplink

import com.github.johnnysc.holybibleapp.presentation.verses.VersesFragment

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkVerseFragment : VersesFragment<DeeplinkVersesViewModel>() {
    override fun viewModelClass() = DeeplinkVersesViewModel::class.java
    override fun showBack() = false
}