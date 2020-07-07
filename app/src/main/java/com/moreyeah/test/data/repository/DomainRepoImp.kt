package com.moreyeah.test.data.repository

import android.util.Log
import com.moreyeah.test.data.remote.TopicsRemote
import com.moreyeah.test.data.remote.dataSource.MoreYeahApi
import com.moreyeah.test.domain.mapper.Country
import com.moreyeah.test.domain.mapper.CountryEntity
import com.moreyeah.test.domain.mapper.DataToDomainMapper
import com.moreyeah.test.domain.mapper.TopicEntity
import com.moreyeah.test.domain.repository.DomainRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DomainRepoImp @Inject constructor(
        private val moreYeahApi: MoreYeahApi,
        private val toDomainMapper: DataToDomainMapper) : BaseRepoImp(), DomainRepo {

    override fun getCountryList(): Flow<CountryEntity> = flow {
        Log.e("TopicRepoImp", "getCountryList()")
        emit(CountryEntity(getList()))
    }

    private suspend fun getList(): ArrayList<Country> {
        val countryList = ArrayList<Country>()
        countryList.add(Country("India", "GMT+05:30"))
        countryList.add(Country("Pakistan", "GMT+04:30"))
        countryList.add(Country("UK", "GMT+00:00"))
        countryList.add(Country("USA", "GMT+12:00"))
        countryList.add(Country("USA1", "GMT+1:00"))
        countryList.add(Country("RUSSIA1", "GMT+3:30"))
        countryList.add(Country("India2", "GMT+07:30"))
        countryList.add(Country("RUSSIA", "GMT+2:30"))
        countryList.add(Country("RUSSIA", "GMT+2:30"))
        countryList.add(Country("India1", "GMT+06:30"))
        countryList.add(Country("Pakistan1", "GMT+03:30"))
        countryList.add(Country("UK", "GMT+00:00"))
        countryList.add(Country("USA", "GMT+12:00"))
        countryList.add(Country("USA", "GMT+12:00"))
        countryList.add(Country("UK1", "GMT+01:00"))
        countryList.add(Country("UK1", "GMT+01:00"))
        countryList.add(Country("USA1", "GMT+1:00"))
        countryList.add(Country("RUSSIA1", "GMT+3:30"))
        countryList.add(Country("India2", "GMT+07:30"))
        countryList.add(Country("Pakistan2", "GMT+06:30"))
        countryList.add(Country("UK2", "GMT+02:00"))
        countryList.add(Country("USA2", "GMT+4:00"))
        countryList.add(Country("RUSSIA2", "GMT+4:30"))
        return countryList
    }

    override fun getTopics(): Flow<List<TopicEntity>?> = flow {
        Log.e("TopicRepoImp", "getTopics()")
        val data = fetchTopicRemote()?.map { toDomainMapper.mapFromRemoteToEntity(it) }?.toList()
        Log.e("TopicRepoImp", "getTopics($data)")
        emit(data)
    }


    private suspend fun fetchTopicRemote(): ArrayList<TopicsRemote>? {
        val data = safeApiCall({ moreYeahApi.getMostPopular() }, "fetching List")
        Log.e("TopicRepoImp", "fetchTopicRemote()")
        return if (data != null) data.topics as ArrayList<TopicsRemote> else null
    }

}