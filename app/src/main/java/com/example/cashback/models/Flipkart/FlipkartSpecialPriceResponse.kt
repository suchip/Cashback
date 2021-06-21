package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartSpecialPriceResponse(

    @SerializedName("amount") val amount : Int,
    @SerializedName("currency") val currency : String
)
