package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonPromotionsResponse(
    @SerializedName("amount") val amount : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("discount_percent") val discount_percent : String,
    @SerializedName("display_amount") val display_amount : String,
    @SerializedName("price_per_unit") val price_per_unit : String,
    @SerializedName("type") val type : String

)
