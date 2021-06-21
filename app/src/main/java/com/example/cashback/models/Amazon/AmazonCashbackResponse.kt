package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonCashbackResponse(

    @SerializedName("amazon_cashback_percentage") val amazon_cashback_percentage : String,
    @SerializedName("flipkart_cashback_percentage") val flipkart_cashback_percentage : String,
    @SerializedName("snapdeal_cashback_percentage") val snapdeal_cashback_percentage : String
)
