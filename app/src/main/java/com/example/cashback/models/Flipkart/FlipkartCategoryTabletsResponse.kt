package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartCategoryAvailableVariantsResponse
import com.google.gson.annotations.SerializedName

data class FlipkartCategoryTabletsResponse(

    @SerializedName("availableVariants") val availableVariants : FlipkartCategoryAvailableVariantsResponse,
    @SerializedName("apiName") val apiName : String
)
