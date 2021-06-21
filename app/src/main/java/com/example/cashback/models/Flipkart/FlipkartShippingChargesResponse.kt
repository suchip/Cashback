package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartShippingChargesResponse(

    @SerializedName("amount") val amount : Int,
    @SerializedName("currency") val currency : String
)
