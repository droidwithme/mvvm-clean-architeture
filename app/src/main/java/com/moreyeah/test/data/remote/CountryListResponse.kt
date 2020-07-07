package com.moreyeah.test.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryListResponse(
        var countryList: List<Country>)


data class Country(
        var name: String,
        var timeZone: String)