package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonImagesResponse(
    @SerializedName("primary") val primary : AmazonPrimaryResponse,
    @SerializedName("variants") val variants : List<AmazonVariantsResponse>
)
