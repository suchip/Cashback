package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonUp_csResponse(
    @SerializedName("display_values") val display_values : List<String>,
    @SerializedName("label") val label : String,
    @SerializedName("locale") val locale : String
)
