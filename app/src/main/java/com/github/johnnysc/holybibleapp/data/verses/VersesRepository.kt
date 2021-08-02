package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Repository
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDb
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheMapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesLimits
import com.github.johnnysc.holybibleapp.data.verses.cloud.VerseCloud
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudMapper

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesRepository : Repository<VersesData> {

    class Base(
        private val cloudDataSource: VersesCloudDataSource,
        private val cacheDataSource: VersesCacheDataSource,
        cloudMapper: VersesCloudMapper,
        cacheMapper: VersesCacheMapper,
        private val chapterIdContainer: Read<Int>,
        private val bookIdContainer: Read<Int>
    ) : Repository.Base<VerseDb, VerseCloud, VerseData, VersesData>(
        cacheDataSource,
        cloudMapper,
        cacheMapper
    ), VersesRepository {
        private val bookId by lazy { bookIdContainer.read() }
        private val chapterId
            get() = chapterIdContainer.read()
        private val limits
            get() = VersesLimits(bookId, chapterId)

        override suspend fun fetchCloudData() = cloudDataSource.fetchVerses(bookId, chapterId)
        override fun cachedDataList() = cacheDataSource.fetchVerses(limits)

        override fun returnSuccess(list: List<VerseData>) = VersesData.Success(list)
        override fun returnFail(e: Exception) = VersesData.Fail(e)
        override fun limits() = limits
    }
}