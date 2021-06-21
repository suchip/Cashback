package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartCategorySpecificInfoV1Response
import com.example.cashback.models.Flipkart.FlipkartProductBaseInfoV1Response
import com.example.cashback.models.Flipkart.FlipkartProductShippingInfoV1Response
import com.google.gson.annotations.SerializedName

data class FlipkartProductsResponse(

    @SerializedName("productBaseInfoV1") val productBaseInfoV1 : FlipkartProductBaseInfoV1Response,
    @SerializedName("productShippingInfoV1") val productShippingInfoV1 : FlipkartProductShippingInfoV1Response,
    @SerializedName("categorySpecificInfoV1") val categorySpecificInfoV1 : FlipkartCategorySpecificInfoV1Response
)
