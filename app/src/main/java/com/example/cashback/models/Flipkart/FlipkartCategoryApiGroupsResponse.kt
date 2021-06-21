package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartCategoryAffiliateResponse
import com.google.gson.annotations.SerializedName

data class FlipkartCategoryApiGroupsResponse(


    @SerializedName("affiliate") val affiliate : FlipkartCategoryAffiliateResponse
)
