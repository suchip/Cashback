package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartLifeStyleInfoResponse(

    @SerializedName("sleeve") val sleeve : String,
    @SerializedName("neck") val neck : String,
    @SerializedName("idealFor") val idealFor : List<String>
)
