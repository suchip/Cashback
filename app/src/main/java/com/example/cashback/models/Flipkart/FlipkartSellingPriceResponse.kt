package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartSellingPriceResponse(

    @SerializedName("amount") val amount : Int,
    @SerializedName("currency") val currency : String
)
