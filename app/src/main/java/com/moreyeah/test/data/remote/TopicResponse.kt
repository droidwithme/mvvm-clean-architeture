package com.moreyeah.test.data.remote


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicResponse(

        @Expose
        @SerializedName("success")
        var success: Boolean,

        @Expose
        @SerializedName("topics")
        var topics: List<TopicsRemote>)


data class TopicsRemote(
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