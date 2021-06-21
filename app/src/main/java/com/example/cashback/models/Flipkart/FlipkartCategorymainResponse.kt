package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartCategoryApiGroupsResponse
import com.google.gson.annotations.SerializedName

data class FlipkartCategorymainResponse(

    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("apiGroups") val apiGroups : FlipkartCategoryApiGroupsResponse
)
