package com.github.johnnysc.holybibleapp.sl.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersService
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.RepositoryContainer

/**
 * @author Asatryan on 30.07.2021
 **/
class ChaptersRepositoryContainer(
    private val coreModule: CoreModule,
    private val useMocks: Boolean
) : RepositoryContainer<ChaptersRepository> {

    override fun repository() = ChaptersRepository.Base(
        cloudDataSource(),
        cacheDataSource(),
        cloudMapper(),
        cacheMapper(),
        coreModule.bookCache
    )

    private fun cacheMapper() = ChaptersCacheMapper.Base(ToChapterMapper.Db(coreModule.bookCache))

    private fun cacheDataSource() =
        ChaptersCacheDataSource.Base(coreModule.realmProvider, ChapterDataToDbMapper.Base())

    private fun cloudDataSource() = if (useMocks)
        mockCloudDataSource()
    else
        ChaptersCloudDataSource.Base(
            coreModule.language,
            english(),
            russian()
        )

    private fun mockCloudDataSource() = if (coreModule.language.isChosenRussian())
        russian()
    else
        ChaptersCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)

    private fun russian() =
        ChaptersCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)

    private fun english() = ChaptersCloudDataSource.English(
        coreModule.makeService(ChaptersService::class.java),
        coreModule.gson
    )

    private fun cloudMapper() =
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(coreModule.bookCache))
}