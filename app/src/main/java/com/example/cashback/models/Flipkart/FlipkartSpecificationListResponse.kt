package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartSpecificationListResponse(

    @SerializedName("key") val key : String,
    @SerializedName("values") val values : List<FlipkartValuesResponse>
)
