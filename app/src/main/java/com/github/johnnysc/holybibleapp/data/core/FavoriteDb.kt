package com.github.johnnysc.holybibleapp.data.core

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class FavoriteDb : RealmObject() {
    @PrimaryKey
    var id: Int = -1
}