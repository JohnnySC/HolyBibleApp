package com.github.johnnysc.holybibleapp.presentation.verses

/**
 * @author Asatryan on 30.07.2021
 **/
class VersesPage {
    val nextChapterRussian = "Перейти к следующей главе"

    fun titleRussian(bookName: String, chapterNumber: Int) = "$bookName Гл.$chapterNumber"
}