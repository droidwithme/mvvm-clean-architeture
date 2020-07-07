package com.moreyeah.test.domain.mapper

import com.moreyeah.test.data.remote.Country
import com.moreyeah.test.data.remote.TopicsRemote

interface DataToDomainMapper {
    fun mapFromRemoteToEntity(from: TopicsRemote): TopicEntity
}