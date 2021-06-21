package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonItem_infoResponse(
    @SerializedName("by_line_info") val by_line_info : AmazonBy_line_infoResponse,
    @SerializedName("classifications") val classifications : AmazonClassificationsResponse,
    @SerializedName("content_info") val content_info : AmazonClassificationsResponse,
    @SerializedName("content_rating") val content_rating : String,
    @SerializedName("external_ids") val external_ids : AmazonExternal_idsResponse,
    @SerializedName("features") val features : AmazonFeaturesResponse,
    @SerializedName("manufacture_info") val manufacture_info : AmazonManufacture_infoResponse,
    @SerializedName("product_info") val product_info : AmazonProduct_infoResponse,
    @SerializedName("technical_info") val technical_info : String,
    @SerializedName("title") val title : AmazonTitleResponse,
    @SerializedName("trade_in_info") val trade_in_info : String
)
