package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonLowest_priceResponse(
    @SerializedName("amount") val amount : Int,
    @SerializedName("currency") val currency : String,
    @SerializedName("display_amount") val display_amount : String,
    @SerializedName("price_per_unit") val price_per_unit : String,
    @SerializedName("savings") val savings : String
)
