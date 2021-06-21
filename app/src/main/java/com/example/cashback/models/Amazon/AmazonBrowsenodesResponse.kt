package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonBrowsenodesResponse(
    @SerializedName("ancestor") val ancestor : AmazonAncestorResponse,
    @SerializedName("children") val children : String,
    @SerializedName("context_free_name") val context_free_name : String,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("id") val id : String,
    @SerializedName("is_root") val is_root : Boolean,
    @SerializedName("sales_rank") val sales_rank : String
)
