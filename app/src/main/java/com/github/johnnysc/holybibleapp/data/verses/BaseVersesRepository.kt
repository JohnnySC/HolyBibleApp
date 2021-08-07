package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.core.AbstractRepository
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDb
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheMapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesLimits
import com.github.johnnysc.holybibleapp.data.verses.cloud.VerseCloud
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesRepository

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesRepository(
    private val cloudDataSource: VersesCloudDataSource,
    private val cacheDataSource: VersesCacheDataSource,
    cloudMapper: VersesCloudMapper,
    cacheMapper: VersesCacheMapper,
    private val chapterIdContainer: Read<Int>,
    private val bookIdContainer: Read<Int>,
    private val multiply: Multiply,
    private val multiplyTwice: Multiply,
) : AbstractRepository<VerseDb, VerseCloud, VerseData, VersesData>(
    cacheDataSource, cloudMapper, cacheMapper
), VersesRepository {
    private val bookId by lazy { bookIdContainer.read() }
    private val chapterId get() = chapterIdContainer.read()
    private val limits get() = VersesLimits(bookId, chapterId, multiplyTwice, multiply)

    override suspend fun fetchCloudData() = cloudDataSource.fetchVerses(bookId, chapterId)
    override fun cachedDataList() = cacheDataSource.fetchVerses(limits)
    override fun returnSuccess(list: List<VerseData>) = VersesData.Success(list)
    override fun returnFail(e: Exception) = VersesData.Fail(e)
    override fun limits() = limits
}