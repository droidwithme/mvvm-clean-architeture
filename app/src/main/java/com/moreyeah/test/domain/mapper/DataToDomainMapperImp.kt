package com.moreyeah.test.domain.mapper

import com.moreyeah.test.data.remote.CountryListResponse
import com.moreyeah.test.data.remote.TopicsRemote

class DataToDomainMapperImp : DataToDomainMapper {

    override fun mapFromRemoteToEntity(from: TopicsRemote): TopicEntity {
        var phraseList: List<Phrase> = from.phrase.map { Phrase(it.name, it.imageUrl) }
        return TopicEntity(
                from.name,
                phraseList
        )
    }

}