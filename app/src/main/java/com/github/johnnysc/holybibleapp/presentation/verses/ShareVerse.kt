package com.github.johnnysc.holybibleapp.presentation.verses

import android.content.Intent

/**
 * @author Asatryan on 05.08.2021
 **/
interface ShareVerse {

    fun share(mapper: ShareMapper)

    class Base(private val title: String, private val text: String) : ShareVerse {
        override fun share(mapper: ShareMapper) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            mapper.share(Intent.createChooser(intent, title))
        }
    }
}

interface ShareMapper {

    fun share(share: Intent)
}