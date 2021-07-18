package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Repository
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDb
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChapterCloud
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersRepository : Repository<ChaptersData> {

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        cloudMapper: ChaptersCloudMapper,
        cacheMapper: ChaptersCacheMapper,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) :
        Repository.Base<ChapterDb, ChapterCloud, ChapterData, ChaptersData>(
            cacheDataSource,
            cloudMapper,
            cacheMapper
        ), ChaptersRepository {

        private val bookId by lazy {
            bookIdContainer.read().first
        }

        override suspend fun fetchCloudData() = cloudDataSource.fetchChapters(bookId)
        override fun getCachedDataList() = cacheDataSource.fetchChapters(ChapterId.Base(bookId))
        override fun returnSuccess(list: List<ChapterData>) = ChaptersData.Success(list)
        override fun returnFail(e: Exception) = ChaptersData.Fail(e)
    }
}