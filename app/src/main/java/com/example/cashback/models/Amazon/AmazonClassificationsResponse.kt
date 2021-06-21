package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonClassificationsResponse(
    @SerializedName("binding") val binding : AmazonBindingResponse,
    @SerializedName("product_group") val product_group : AmazonProduct_grouopResponse
)
