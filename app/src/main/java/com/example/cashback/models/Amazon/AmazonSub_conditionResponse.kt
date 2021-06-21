package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonSub_conditionResponse(
    @SerializedName("display_value") val display_value : String,
    @SerializedName("label") val label : String,
    @SerializedName("locale") val locale : String,
    @SerializedName("value") val value : String
)
