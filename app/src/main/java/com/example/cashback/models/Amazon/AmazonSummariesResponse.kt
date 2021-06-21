package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonSummariesResponse(
    @SerializedName("condition") val condition : AmazonConditionResponse,
    @SerializedName("highest_price") val highest_price : AmazonHighest_priceResponse,
    @SerializedName("lowest_price") val lowest_price : AmazonLowest_priceResponse,
    @SerializedName("offer_count") val offer_count : Int

)
