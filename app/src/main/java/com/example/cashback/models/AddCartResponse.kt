package com.example.cashback.models

import com.google.gson.annotations.SerializedName

data class AddCartResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("product_url") val product_url : String,
    @SerializedName("product_id") val product_id : String,
    @SerializedName("product_info") val product_info : String,
    @SerializedName("amount") val amount : String,
    @SerializedName("saving") val saving : Int,
    @SerializedName("product_image") val product_image : String,
    @SerializedName("discount_percentage") val discount_percentage : Int,
    @SerializedName("platform") val platform : Int,
    @SerializedName("user_id") val user_id : Int
)
