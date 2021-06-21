package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartProductBaseInfoV1Response(

    @SerializedName("productId") val productId : String,
    @SerializedName("title") val title : String,
    @SerializedName("productDescription") val productDescription : String,
    @SerializedName("imageUrls") val imageUrls : FlipkartImageUrlsResponse,
    @SerializedName("productFamily") val productFamily : List<String>,
    @SerializedName("maximumRetailPrice") val maximumRetailPrice : FlipkartMaximumRetailPriceResponse,
    @SerializedName("flipkartSellingPrice") val flipkartSellingPrice : FlipkartSellingPriceResponse,
    @SerializedName("flipkartSpecialPrice") val flipkartSpecialPrice : FlipkartSpecialPriceResponse,
    @SerializedName("productUrl") val productUrl : String,
    @SerializedName("productBrand") val productBrand : String,
    @SerializedName("inStock") val inStock : Boolean,
    @SerializedName("codAvailable") val codAvailable : Boolean,
    @SerializedName("discountPercentage") val discountPercentage : Int,
    @SerializedName("offers") val offers : List<String>,
    @SerializedName("categoryPath") val categoryPath : String,
    @SerializedName("attributes") val attributes : FlipkartAttributesResponse
)
