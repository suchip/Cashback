package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonBindingResponse(
    @SerializedName("display_value") val display_value : String,
    @SerializedName("label") val label : String,
    @SerializedName("locale") val locale : String
)
