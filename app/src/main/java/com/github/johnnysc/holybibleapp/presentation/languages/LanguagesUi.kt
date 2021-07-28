package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 24.07.2021
 **/
class LanguagesUi(
    private val choice: LanguageChoice,
    private val englishTitle: String,
    private val russianTitle: String
) {
    fun show(
        englishMapper: TextMapper,
        russianMapper: TextMapper,
        russian: () -> Unit,
        english: () -> Unit,
        none: () -> Unit
    ) {
        englishMapper.map(englishTitle)
        russianMapper.map(russianTitle)
        when (choice) {
            LanguageChoice.ENGLISH -> english()
            LanguageChoice.RUSSIAN -> russian()
            LanguageChoice.NONE -> none()
        }
    }
}

enum class LanguageChoice {
    ENGLISH,
    RUSSIAN,
    NONE
}