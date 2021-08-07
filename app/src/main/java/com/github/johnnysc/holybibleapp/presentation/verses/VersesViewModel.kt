package com.github.johnnysc.holybibleapp.presentation.verses

import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkData
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import com.github.johnnysc.holybibleapp.sl.core.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesViewModel(
    private val navigator: VersesNavigator,
    private val interactor: VersesInteractor,
    communication: VersesCommunication,
    private val mapper: VersesDomainToUiMapper<VersesUi>,
    private val deeplinkData: DeeplinkData,
    resourceProvider: ResourceProvider,
    private val multiply: Multiply,
) : BaseViewModel<VersesCommunication, VersesUi>(resourceProvider, communication),
    ChangeFavorite<Int> {

    abstract fun init()
    abstract val feature: Feature

    override fun changeFavorite(id: Int) {
        super.changeFavorite(id, communication, interactor)
    }

    override fun scrollPosition() = interactor.scrollPosition(feature)
    override fun saveScrollPosition(position: Int) =
        interactor.saveScrollPosition(feature, position)

    override fun fetch() {
        communication.map(VersesUi.Base(ArrayList(listOf(VerseUi.Progress)), title()))
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchVerses()
            val ui = list.map(mapper)
            withContext(Dispatchers.Main) { communication.map(ui) }
        }
    }

    fun showNextChapterVerses() {
        interactor.showNextChapter()
        fetch()
    }

    fun share(item: VerseUi): String {
        var bookNameAndChapterNumber = ""
        communication.title(object : TextMapper {
            override fun map(data: String) {
                bookNameAndChapterNumber = data
            }
        })
        return item.map(VerseUiMapper.Share(deeplinkData, bookNameAndChapterNumber, multiply))
    }

    protected fun saveVersesScreen() = navigator.save(feature)

    class Base(
        navigator: VersesNavigator,
        interactor: VersesInteractor,
        communication: VersesCommunication,
        mapper: VersesDomainToUiMapper<VersesUi>,
        resourceProvider: ResourceProvider,
        deeplinkData: DeeplinkData,
        multiply: Multiply,
    ) : VersesViewModel(
        navigator, interactor, communication, mapper, deeplinkData, resourceProvider, multiply
    ) {
        override val feature = Feature.VERSES
        override fun init() {
            saveVersesScreen()
            fetch()
        }
    }
}