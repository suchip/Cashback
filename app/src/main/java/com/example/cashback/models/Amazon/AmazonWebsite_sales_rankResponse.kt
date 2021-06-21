package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonWebsite_sales_rankResponse(
    @SerializedName("context_free_name") val context_free_name : String,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("id") val id : String,
    @SerializedName("sales_rank") val sales_rank : Int
)
