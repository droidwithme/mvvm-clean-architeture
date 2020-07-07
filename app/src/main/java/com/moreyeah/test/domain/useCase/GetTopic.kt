package com.moreyeah.test.domain.useCase

import com.moreyeah.test.domain.mapper.TopicEntity
import com.moreyeah.test.domain.repository.DomainRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopic @Inject constructor(val repository: DomainRepo)  {

      fun invoke() : Flow<List<TopicEntity>?> {
       return repository.getTopics()
    }


}