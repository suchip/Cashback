package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonManufacture_infoResponse(
    @SerializedName("item_part_number") val item_part_number : AmazonItem_part_numberResponse,
    @SerializedName("model") val model : AmazonModelResponse,
    @SerializedName("warranty") val warranty : AmazonWarrantyResponse
)
