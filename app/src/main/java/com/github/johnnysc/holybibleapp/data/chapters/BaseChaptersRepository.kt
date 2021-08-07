package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDb
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChapterCloud
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.github.johnnysc.holybibleapp.data.core.AbstractRepository
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersRepository

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersRepository(
    private val cloudDataSource: ChaptersCloudDataSource,
    private val cacheDataSource: ChaptersCacheDataSource,
    cloudMapper: ChaptersCloudMapper,
    cacheMapper: ChaptersCacheMapper,
    private val bookIdContainer: Read<Int>,
    private val multiply: Multiply,
) : AbstractRepository<ChapterDb, ChapterCloud, ChapterData, ChaptersData>(
    cacheDataSource,
    cloudMapper,
    cacheMapper
), ChaptersRepository {

    private val bookId by lazy { bookIdContainer.read() }
    private val chapterId by lazy { ChapterId.Base(bookId, multiply = multiply) }

    override suspend fun fetchCloudData() = cloudDataSource.fetchChapters(bookId)
    override fun cachedDataList() = cacheDataSource.fetchChapters(chapterId)
    override fun returnSuccess(list: List<ChapterData>) = ChaptersData.Success(list)
    override fun returnFail(e: Exception) = ChaptersData.Fail(e)
    override fun limits() = chapterId
}