package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 17.07.2021
 **/
data class VersesUi(
    private val list: List<VerseUi>
) : Abstract.Object<Unit, VersesCommunication> {
    override fun map(mapper: VersesCommunication) = mapper.map(list)
}