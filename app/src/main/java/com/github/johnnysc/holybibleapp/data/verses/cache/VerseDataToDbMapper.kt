package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>): VerseDb

    class Base : VerseDataToDbMapper {
        override fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>) =
            db.createObject(id).apply {
                this.verseId = verseId
                this.text = text
            }
    }
}