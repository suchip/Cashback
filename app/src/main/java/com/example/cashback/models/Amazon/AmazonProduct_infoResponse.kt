package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonProduct_infoResponse(
    @SerializedName("color") val color : AmazonColorResponse,
    @SerializedName("is_adult_product") val is_adult_product : AmazonEa_nsResponse,
    @SerializedName("item_dimensions") val item_dimensions : AmazonItem_dimensionsResponse,
    @SerializedName("release_date") val release_date : AmazonRelease_dateResponse,
    @SerializedName("size") val size : AmazonSizeResponse,
    @SerializedName("unit_count") val unit_count : AmazonUnit_countResponse
)
