package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartImageUrlsResponse(

    @SerializedName("200x200") val resolutionType1 : String,
    @SerializedName("400x400") val resolutionType2 : String,
    @SerializedName("800x800") val resolutionType3 : String
)
