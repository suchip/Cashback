package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartimegeurlResponse
import com.google.gson.annotations.SerializedName

data class FlipkartlistResponse(


    @SerializedName("name") val name : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("imageUrls") val imageUrls : List<FlipkartimegeurlResponse>,
    @SerializedName("availability") val availability : String
)
