package com.github.johnnysc.holybibleapp.data.core

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

/**
 * @author Asatryan on 27.06.2021
 **/
class FavoriteMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        if (oldVersion == 0L) schema.create("FavoriteDb")
            .addField("id", Int::class.java, FieldAttribute.PRIMARY_KEY)
    }
}