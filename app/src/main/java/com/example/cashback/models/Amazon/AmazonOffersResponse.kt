package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonOffersResponse(
    @SerializedName("listings") val listings : List<AmazonListingResponse>,
    @SerializedName("summaries") val summaries : List<AmazonSummariesResponse>
)
