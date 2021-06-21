package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonResponse(
    @SerializedName("asin") val asin : String,
    @SerializedName("browse_node_info") val browse_node_info : AmazonBrowse_node_infoResponse,
    @SerializedName("detail_page_url") val detail_page_url : String,
    @SerializedName("images") val images : AmazonImagesResponse,
    @SerializedName("item_info") val item_info : AmazonItem_infoResponse,
    @SerializedName("offers") val offers : AmazonOffersResponse,
    @SerializedName("parent_asin") val parent_asin : String,
    @SerializedName("rental_offers") val rental_offers : String,
    @SerializedName("score") val score : String,
    @SerializedName("variation_attributes") val variation_attributes : String

)
