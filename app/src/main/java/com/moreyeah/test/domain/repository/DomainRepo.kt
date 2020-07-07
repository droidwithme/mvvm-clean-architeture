package com.moreyeah.test.domain.repository

import com.moreyeah.test.domain.mapper.CountryEntity
import com.moreyeah.test.domain.mapper.TopicEntity
import kotlinx.coroutines.flow.Flow

interface DomainRepo {
    fun getTopics(): Flow<List<TopicEntity>?>
    fun getCountryList(): Flow<CountryEntity?>
}