package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.ChosenLanguage
import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudDataSource {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud>

    abstract class Abstract(private val gson: Gson, private val typeToken: TypeToken<*>) :
        VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud> =
            gson.fromJson(getDataAsString(bookId, chapterId), typeToken.type)

        protected abstract suspend fun getDataAsString(bookId: Int, chapterId: Int): String
    }

    class Base(
        private val language: ChosenLanguage,
        private val english: VersesCloudDataSource,
        private val russian: VersesCloudDataSource,
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int) = (
                if (language.isChosenRussian()) russian
                else english
                ).fetchVerses(bookId, chapterId)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    class English(
        private val service: VersesService, gson: Gson, typeToken: TypeToken<*>,
    ) : VersesCloudDataSource.Abstract(gson, typeToken) {
        override suspend fun getDataAsString(bookId: Int, chapterId: Int) =
            service.fetchVerses(bookId, chapterId).string()
    }

    class Russian(
        private val booksRu: () -> List<BookRu>,
        private val multiplyTwice: Multiply,
        private val multiply: Multiply,
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int) = booksRu().find {
            it.matches(bookId)
        }!!.contentAsList().find {
            it.matches(chapterId)
        }!!.contentAsList().map { (id, verse) ->
            verse.map(VerseToWrapperMapper.Base(bookId, chapterId, id, multiplyTwice, multiply))
        }
    }

    class Mock(
        private val multipleTwice: Multiply,
        private val multiply: Multiply,
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int) = (0..10).map {
            VerseCloud.Base(
                multipleTwice.map(bookId) + multiply.map(chapterId) + it,
                it,
                "Mock data $bookId $chapterId $it"
            )
        }
    }
}

class VerseTypeToken : TypeToken<List<VerseCloud.Base>>()