package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.github.johnnysc.holybibleapp.data.verses.cache.VersesCacheMapper
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.github.johnnysc.holybibleapp.data.verses.cloud.VersesCloudMapper

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesRepository {

    suspend fun fetchVerses(): VersesData

    class Base(
        private val cloudDataSource: VersesCloudDataSource,
        private val cacheDataSource: VersesCacheDataSource,
        private val cloudMapper: VersesCloudMapper,
        private val cacheMapper: VersesCacheMapper,
        private val chapterIdContainer: Read<Int>,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) : VersesRepository {
        override suspend fun fetchVerses(): VersesData = try {
            val bookId = bookIdContainer.read().first
            val chapterId = chapterIdContainer.read()
            val versesCacheList = cacheDataSource.fetchVerses(bookId, chapterId)
            if (versesCacheList.isEmpty()) {
                val versesCloudList = cloudDataSource.fetchVerses(bookId, chapterId)
                val verses = cloudMapper.map(versesCloudList)
                cacheDataSource.save(verses)
                VersesData.Success(verses)
            } else {
                VersesData.Success(cacheMapper.map(versesCacheList))
            }
        } catch (e: Exception) {
            VersesData.Fail(e)
        }
    }
}