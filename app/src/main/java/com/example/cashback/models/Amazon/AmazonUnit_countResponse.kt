package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonUnit_countResponse(
    @SerializedName("display_value") val display_value : Int,
    @SerializedName("label") val label : String,
    @SerializedName("locale") val locale : String
)




