package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.core.TextMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 06.03.2023
 */
class LanguagesUiTest {

    @Test
    fun test() {
        val choice = FakeLanguageChoice()
        val english = FakeTextMapper()
        val russian = FakeTextMapper()
        val languagesUi = LanguagesUi.Base(
            choice,
            "english",
            "russian"
        )
        languagesUi.map(english, russian, FakeChoose())

        assertEquals("english", english.mapList[0])
        assertEquals("russian", russian.mapList[0])
        assertEquals(1, english.mapList.size)
        assertEquals(1, russian.mapList.size)
        assertEquals(1, choice.count)
    }
}

private class FakeChoose : ChooseLanguageUi {
    override fun noLanguageChosen() = Unit
    override fun russianChosen() = Unit
    override fun englishChosen() = Unit
}

private class FakeTextMapper : TextMapper {
    var mapList = mutableListOf<String>()
    override fun map(data: String) {
        mapList.add(data)
    }
}

private class FakeLanguageChoice : LanguageChoice {
    var count = 0
    override fun map(languageUi: ChooseLanguageUi) {
        count++
    }
}