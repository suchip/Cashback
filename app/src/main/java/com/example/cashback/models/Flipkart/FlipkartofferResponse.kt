package com.example.cashback.models.Flipkart

import com.example.cashback.models.Flipkart.FlipkartlistResponse
import com.google.gson.annotations.SerializedName

data class FlipkartofferResponse(


    @SerializedName("allOffersList") val allOffersList : List<FlipkartlistResponse>
)
