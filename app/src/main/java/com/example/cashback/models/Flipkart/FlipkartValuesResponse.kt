package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartValuesResponse(
    @SerializedName("key") val key : String,
    @SerializedName("value") val value : List<String>

)
