package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Pair<List<VerseData>, String>, T>()