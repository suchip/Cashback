package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonBy_line_infoResponse(
    @SerializedName("brand") val brand : AmazonBrandResponse,
    @SerializedName("contributors") val contributors : List<AmazonContributorResponse>,
    @SerializedName("manufacturer") val manufacturer : AmazonManufactureResponse
)
