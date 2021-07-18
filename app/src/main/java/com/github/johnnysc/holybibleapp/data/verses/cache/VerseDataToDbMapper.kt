package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.DbWrapper
import io.realm.RealmObject

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDataToDbMapper<T: RealmObject> {

    fun map(id: Int, verseId: Int, text: String, db: DbWrapper<T>): T

    class Base : VerseDataToDbMapper<VerseDb> {

        override fun map(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>) =
            db.createObject(id).apply {
                this.verseId = verseId
                this.text = text
            }

    }
}