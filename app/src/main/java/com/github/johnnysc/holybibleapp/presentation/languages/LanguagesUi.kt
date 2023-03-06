package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.core.TextMapper

/**
 * @author Asatryan on 24.07.2021
 **/
interface LanguagesUi {

    fun map(
        englishMapper: TextMapper,
        russianMapper: TextMapper,
        chooseLanguageUi: ChooseLanguageUi
    )

    class Base(
        private val choice: LanguageChoice,
        private val englishTitle: String,
        private val russianTitle: String
    ) : LanguagesUi {

        override fun map(
            englishMapper: TextMapper,
            russianMapper: TextMapper,
            chooseLanguageUi: ChooseLanguageUi
        ) {
            englishMapper.map(englishTitle)
            russianMapper.map(russianTitle)
            choice.map(chooseLanguageUi)
        }
    }
}

interface LanguageChoice {

    fun map(languageUi: ChooseLanguageUi)

    class English : LanguageChoice {
        override fun map(languageUi: ChooseLanguageUi) = languageUi.englishChosen()
    }

    class Russian : LanguageChoice {
        override fun map(languageUi: ChooseLanguageUi) = languageUi.russianChosen()
    }

    class None : LanguageChoice {
        override fun map(languageUi: ChooseLanguageUi) = languageUi.noLanguageChosen()
    }
}