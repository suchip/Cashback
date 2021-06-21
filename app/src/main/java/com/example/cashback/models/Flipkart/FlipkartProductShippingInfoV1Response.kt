package com.example.cashback.models.Flipkart

import com.google.gson.annotations.SerializedName

data class FlipkartProductShippingInfoV1Response(

    @SerializedName("shippingCharges") val shippingCharges : FlipkartShippingChargesResponse,
    @SerializedName("estimatedDeliveryTime") val estimatedDeliveryTime : String,
    @SerializedName("sellerName") val sellerName : String,
    @SerializedName("sellerAverageRating") val sellerAverageRating : String,
    @SerializedName("sellerNoOfRatings") val sellerNoOfRatings : String,
    @SerializedName("sellerNoOfReviews") val sellerNoOfReviews : String
)
