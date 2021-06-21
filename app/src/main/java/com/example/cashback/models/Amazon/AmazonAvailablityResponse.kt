package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonAvailablityResponse(

    @SerializedName("max_order_quantity") val max_order_quantity : Int,
    @SerializedName("message") val message : String,
    @SerializedName("min_order_quantity") val min_order_quantity : Int,
    @SerializedName("type") val type : String
)
