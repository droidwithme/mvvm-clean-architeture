package com.moreyeah.test.domain.useCase

import com.moreyeah.test.domain.mapper.CountryEntity
import com.moreyeah.test.domain.repository.DomainRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryList @Inject constructor(val repository: DomainRepo) {

    fun invoke(): Flow<CountryEntity?> {
        return repository.getCountryList()
    }


}