package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartCategoryversionResponse(

    @SerializedName("resourceName") val resourceName : String,
    @SerializedName("put") val put : String,
    @SerializedName("get") val get : String,
    @SerializedName("deltaGet") val deltaGet : String,
    @SerializedName("post") val post : String,
    @SerializedName("delete") val delete : String
)
