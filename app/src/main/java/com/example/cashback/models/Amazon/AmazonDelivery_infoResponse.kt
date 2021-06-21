package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonDelivery_infoResponse(

    @SerializedName("is_amazon_fulfilled") val is_amazon_fulfilled : Boolean,
    @SerializedName("is_free_shipping_eligible") val is_free_shipping_eligible : Boolean,
    @SerializedName("is_prime_eligible") val is_prime_eligible : Boolean,
    @SerializedName("shipping_charges") val shipping_charges : String
)
