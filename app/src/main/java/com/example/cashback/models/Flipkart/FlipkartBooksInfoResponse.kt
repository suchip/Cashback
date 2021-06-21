package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartBooksInfoResponse(

    @SerializedName("language") val language : String,
    @SerializedName("binding") val binding : String,
    @SerializedName("pages") val pages : String,
    @SerializedName("publisher") val publisher : String,
    @SerializedName("year") val year : Int,
    @SerializedName("authors") val authors : List<String>
)
