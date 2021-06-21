package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategoryAffiliateResponse(

    @SerializedName("name") val name : String,
    @SerializedName("apiListings") val apiListings : FlipkartCategoryApiListingsResponse
)
