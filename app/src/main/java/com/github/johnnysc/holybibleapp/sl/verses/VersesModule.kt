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
import com.github.johnnysc.holybibleapp.sl.books.BooksModule
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesModule(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val useMocks: Boolean
) : BaseModule<VersesViewModel> {

    override fun getViewModel() = VersesViewModel(
        coreModule.navigator,
        getInteractor(),
        VersesCommunication.Base(),
        BaseVersesDomainToUiMapper(BaseVerseDomainToUiMapper(), coreModule.resourceProvider),
        coreModule.resourceProvider
    )

    private fun getInteractor() = VersesInteractor.Base(
        getRepository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()),
        coreModule.resourceProvider,
        coreModule.chapterCache,
        booksModule.repository(),
        coreModule.bookCache,
    )

    private fun getRepository(): VersesRepository {
        val mapper = ToVerseMapper.Base()
        return VersesRepository.Base(
            if (useMocks)
                VersesCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)
            else
                VersesCloudDataSource.Base(
                    coreModule.language,
                    VersesCloudDataSource.English(
                        coreModule.makeService(VersesService::class.java),
                        coreModule.gson
                    ),
                    VersesCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)
                ),
            VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base()),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            coreModule.chapterCache,
            coreModule.bookCache
        )
    }
}