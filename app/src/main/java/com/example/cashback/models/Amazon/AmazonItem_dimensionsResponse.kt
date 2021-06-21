package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonItem_dimensionsResponse(
    @SerializedName("height") val height : AmazonHeightResponse,
    @SerializedName("length") val length : AmazonLengthResponse,
    @SerializedName("weight") val weight : AmazonWeightResponse,
    @SerializedName("width") val width : AmazonWidthResponse
)
