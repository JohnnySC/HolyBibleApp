package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCommunication : Communication<Pair<List<VerseUi>, String>>, Abstract.Mapper {
    class Base : Communication.Base<Pair<List<VerseUi>, String>>(), VersesCommunication
}