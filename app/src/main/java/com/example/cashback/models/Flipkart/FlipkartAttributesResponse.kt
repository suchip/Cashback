package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartAttributesResponse(

    @SerializedName("size") val size : String,
    @SerializedName("color") val color : String,
    @SerializedName("storage") val storage : String,
    @SerializedName("sizeUnit") val sizeUnit : String,
    @SerializedName("displaySize") val displaySize : String
)
