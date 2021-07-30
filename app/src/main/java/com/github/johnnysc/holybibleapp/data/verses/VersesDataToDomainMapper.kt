package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.BuildString

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Triple<List<VerseData>, BuildString, Pair<Int, Boolean>>, T>()