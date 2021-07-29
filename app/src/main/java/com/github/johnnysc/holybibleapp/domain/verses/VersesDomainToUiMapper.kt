package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.BuildString
import com.github.johnnysc.holybibleapp.core.ResourceProvider

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<Triple<List<VerseDomain>, BuildString, Int>, T>(resourceProvider)