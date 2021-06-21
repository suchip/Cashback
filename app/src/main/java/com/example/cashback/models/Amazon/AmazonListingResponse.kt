package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonListingResponse(
    @SerializedName("availability") val availability : AmazonAvailablityResponse,
    @SerializedName("condition") val condition : AmazonConditionResponse,
    @SerializedName("delivery_info") val delivery_info : AmazonDelivery_infoResponse,
    @SerializedName("id") val id : String,
    @SerializedName("is_buy_box_winner") val is_buy_box_winner : Boolean,
    @SerializedName("loyalty_points") val loyalty_points : String,
    @SerializedName("merchant_info") val merchant_info : AmazonMerchant_infoResponse,
    @SerializedName("price") val price : AmazonPriceResponse,
    @SerializedName("program_eligibility") val program_eligibility : AmazonProgram_eligibilityResponse,
    @SerializedName("promotions") val promotions : List<AmazonPromotionsResponse>,
    @SerializedName("saving_basis") val saving_basis : AmazonSaving_basisResponse,
    @SerializedName("violates_map") val violates_map : Boolean
)
