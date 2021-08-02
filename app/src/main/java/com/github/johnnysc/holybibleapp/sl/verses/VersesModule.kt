package com.github.johnnysc.holybibleapp.sl.verses

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesRepository
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesService
import com.github.johnnysc.holybibleapp.domain.verses.BaseVerseDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.verses.BaseVersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VersesCommunication
import com.github.johnnysc.holybibleapp.presentation.verses.VersesViewModel
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesModule(
    private val coreModule: CoreModule,
    private val booksRepository: BooksRepository,
    private val chaptersRepository: ChaptersRepository,
    private val useMocks: Boolean,
    private val booksRu: () -> List<BookRu>
) : BaseModule<VersesViewModel> {

    override fun viewModel() = VersesViewModel(
        coreModule.navigator,
        interactor(),
        communications(),
        mapper(),
        coreModule.resourceProvider
    )

    private fun communications() = VersesCommunication.Base()

    private fun mapper() =
        BaseVersesDomainToUiMapper(
            BaseVerseDomainToUiMapper(coreModule.resourceProvider),
            coreModule.resourceProvider
        )

    private fun interactor() = VersesInteractor.Base(
        repository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()),
        booksRepository,
        coreModule.bookCache,
        coreModule.scrollPositionCache,
        chaptersRepository,
        coreModule.chapterCache,
    )

    private fun repository(): VersesRepository {
        val mapper = ToVerseMapper.Base()
        return VersesRepository.Base(
            cloudDataSource(),
            cacheDataSource(),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            coreModule.chapterCache,
            coreModule.bookCache
        )
    }

    private fun cacheDataSource() =
        VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base())

    private fun cloudDataSource() = if (useMocks)
        VersesCloudDataSource.Mock()
    else
        VersesCloudDataSource.Base(
            coreModule.language,
            english(),
            russian()
        )

    private fun russian() = VersesCloudDataSource.Russian(booksRu)

    private fun english() = VersesCloudDataSource.English(
        coreModule.makeService(VersesService::class.java),
        coreModule.gson
    )
}