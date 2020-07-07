package com.moreyeah.test.domain.mapper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicEntity(
        @Expose
        @SerializedName("name")
        var name: String,
        @Expose
        @SerializedName("phrases")
        var phrase: List<Phrase>)

data class Phrase(
        @Expose
        @SerializedName("name")
        var name: String,

        @Expose
        @SerializedName("imageUrl")
        var imageUrl: String)