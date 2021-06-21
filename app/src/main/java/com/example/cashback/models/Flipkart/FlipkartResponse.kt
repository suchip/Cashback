package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartProductsResponse
import com.google.gson.annotations.SerializedName

data class FlipkartResponse(

    @SerializedName("nextUrl") val nextUrl : String,
    @SerializedName("validTill") val validTill : String,
    @SerializedName("lastProductId") val lastProductId : String,
    @SerializedName("products") val products : ArrayList<FlipkartProductsResponse>
)
