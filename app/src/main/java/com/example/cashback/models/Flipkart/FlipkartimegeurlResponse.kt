package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartimegeurlResponse(


    @SerializedName("url") val url : String,
    @SerializedName("resolutionType") val resolutionType : String
)
