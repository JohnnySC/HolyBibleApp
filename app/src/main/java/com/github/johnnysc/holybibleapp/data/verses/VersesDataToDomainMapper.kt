package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomain

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<VerseData>, VersesDomain>()