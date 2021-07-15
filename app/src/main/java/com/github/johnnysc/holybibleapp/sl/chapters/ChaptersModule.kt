package com.github.johnnysc.holybibleapp.sl.chapters

import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersService
import com.github.johnnysc.holybibleapp.domain.chapters.BaseChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersInteractor
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersCommunication
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersViewModel
import com.github.johnnysc.holybibleapp.sl.core.BaseModule

/**
 * @author Asatryan on 15.07.2021
 **/
class ChaptersModule(private val coreModule: CoreModule) : BaseModule<ChaptersViewModel> {

    override fun getViewModel() = ChaptersViewModel(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        coreModule.navigator,
        coreModule.bookCache
    )

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(),
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
    )

    private fun getChaptersRepository() = ChaptersRepository.Base(
        ChaptersCloudDataSource.Base(
            coreModule.makeService(ChaptersService::class.java),
            coreModule.gson
        ),
        ChaptersCacheDataSource.Base(coreModule.realmProvider, ChapterDataToDbMapper.Base()),
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(coreModule.bookCache)),
        ChaptersCacheMapper.Base(ToChapterMapper.Db(coreModule.bookCache)),
        coreModule.bookCache
    )

    private fun getChaptersCommunication() = ChaptersCommunication.Base()

    private fun getChaptersMapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
        coreModule.resourceProvider
    )
}