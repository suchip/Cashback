package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonAncestorResponse(

    @SerializedName("ancestor") val ancestor : AmazonAncestorResponse,
    @SerializedName("context_free_name") val context_free_name : String,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("id") val id : String
)
