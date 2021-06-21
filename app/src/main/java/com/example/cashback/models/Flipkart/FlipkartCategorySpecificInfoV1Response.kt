package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategorySpecificInfoV1Response(

    @SerializedName("keySpecs") val keySpecsSpecs : List<String>,
    @SerializedName("detailedSpecs") val detailedSpecs : List<String>,
    @SerializedName("specificationList") val specificationList : List<FlipkartSpecificationListResponse>,
    @SerializedName("booksInfo") val booksInfo : FlipkartBooksInfoResponse,
    @SerializedName("lifeStyleInfo") val lifeStyleInfo : FlipkartLifeStyleInfoResponse
)
