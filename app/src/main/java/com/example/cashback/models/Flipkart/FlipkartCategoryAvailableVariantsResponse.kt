package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategoryAvailableVariantsResponse(

    @SerializedName("v1.1.0") val variants : FlipkartCategoryversionResponse
)
