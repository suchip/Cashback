package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategoryAutomotiveResponse(

    @SerializedName("availableVariants") val availableVariants : FlipkartCategoryAvailableVariantsResponse,
    @SerializedName("apiName") val apiName : String
)
