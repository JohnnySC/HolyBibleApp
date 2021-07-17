package com.github.johnnysc.holybibleapp.sl.verses

import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesRepository
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesService
import com.github.johnnysc.holybibleapp.domain.verses.*
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VersesCommunication
import com.github.johnnysc.holybibleapp.presentation.verses.VersesViewModel
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesModule(private val coreModule: CoreModule) : BaseModule<VersesViewModel> {

    override fun getViewModel() = VersesViewModel(
        coreModule.navigator,
        getInteractor(),
        VersesCommunication.Base(),
        BaseVersesDomainToUiMapper(BaseVerseDomainToUiMapper(), coreModule.resourceProvider),
        coreModule.bookCache,
        coreModule.chapterCache
    )

    private fun getInteractor() = VersesInteractor.Base(
        getRepository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper())
    )

    private fun getRepository(): VersesRepository.Base {
        val mapper = ToVerseMapper.Base()
        return VersesRepository.Base(
            VersesCloudDataSource.Base(
                coreModule.makeService(VersesService::class.java),
                coreModule.gson
            ),
            VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base()),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            coreModule.chapterCache,
            coreModule.bookCache
        )
    }
}