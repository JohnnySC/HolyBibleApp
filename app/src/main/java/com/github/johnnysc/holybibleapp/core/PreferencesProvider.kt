package com.github.johnnysc.holybibleapp.core

import android.content.SharedPreferences

/**
 * @author Asatryan on 15.07.2021
 **/
interface PreferencesProvider {

    fun provideSharedPreferences(name: String): SharedPreferences
}