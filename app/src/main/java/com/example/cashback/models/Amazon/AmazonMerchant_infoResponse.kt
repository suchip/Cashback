package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonMerchant_infoResponse(
    @SerializedName("default_shipping_country") val default_shipping_country : String,
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String

)
