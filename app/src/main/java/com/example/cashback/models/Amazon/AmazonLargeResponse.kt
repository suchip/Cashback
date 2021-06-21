package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonLargeResponse(
    @SerializedName("url") val url : String,
    @SerializedName("height") val height : Int,
    @SerializedName("width") val width : Int
)
