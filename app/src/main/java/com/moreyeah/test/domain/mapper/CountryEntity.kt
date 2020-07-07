package com.moreyeah.test.domain.mapper

data class CountryEntity(
        var countryList: List<Country>)

data class Country(
        var name: String,
        var timeZone: String)