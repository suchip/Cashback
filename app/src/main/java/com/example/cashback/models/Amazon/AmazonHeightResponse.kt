package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonHeightResponse(
    @SerializedName("display_value") val display_value : Double,
    @SerializedName("label") val label : String,
    @SerializedName("locale") val locale : String,
    @SerializedName("unit") val unit : String

)
