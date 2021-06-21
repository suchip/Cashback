package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonContributorResponse(

    @SerializedName("locale") val locale : String,
    @SerializedName("name") val name : String,
    @SerializedName("role") val role : String
)
