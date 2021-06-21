package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategoryAir_conditionersResponse(

    @SerializedName("availableVariants") val availableVariants : FlipkartCategoryAvailableVariantsResponse,
    @SerializedName("apiName") val apiName : String
)
