package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonPrimaryResponse(
    @SerializedName("small") val small : AmazonSmallResponse,
    @SerializedName("medium") val medium : AmazonMediumResponse,
    @SerializedName("large") val large : AmazonLargeResponse
)
