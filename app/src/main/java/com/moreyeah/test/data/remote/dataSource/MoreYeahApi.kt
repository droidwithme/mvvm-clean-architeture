package com.moreyeah.test.data.remote.dataSource

import com.moreyeah.test.data.remote.TopicResponse
import com.moreyeah.test.presentation.commons.AppConstants.Companion.HEADER_KEY
import com.moreyeah.test.presentation.commons.AppConstants.Companion.HEADER_TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Singleton


@Singleton
interface MoreYeahApi {

    companion object {
        const val POPULAR_MOVIES_QUERY: String = ("topics")
    }

    @GET(POPULAR_MOVIES_QUERY)
    suspend fun getMostPopular(@Header(HEADER_KEY) apiKey: String = HEADER_TOKEN): Response<TopicResponse>


}