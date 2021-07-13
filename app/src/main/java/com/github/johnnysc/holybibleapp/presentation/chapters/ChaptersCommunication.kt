package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 12.07.2021
 **/
interface ChaptersCommunication : Communication<List<ChapterUi>> {

    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}