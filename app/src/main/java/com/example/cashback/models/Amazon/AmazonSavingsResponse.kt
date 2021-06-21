package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonSavingsResponse(
    @SerializedName("amount") val amount : Int,
    @SerializedName("currency") val currency : String,
    @SerializedName("display_amount") val display_amount : String,
    @SerializedName("percentage") val percentage : Int,
    @SerializedName("price_per_unit") val price_per_unit : String
)
