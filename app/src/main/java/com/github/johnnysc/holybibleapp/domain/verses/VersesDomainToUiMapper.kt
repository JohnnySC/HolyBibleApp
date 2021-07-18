package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.verses.VersesUi

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
        Abstract.Mapper.DomainToUi.Base<List<VerseDomain>, T>(resourceProvider)