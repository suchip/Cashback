package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonProgram_eligibilityResponse(
    @SerializedName("is_prime_exclusive") val is_prime_exclusive : Boolean,
    @SerializedName("is_prime_pantry") val is_prime_pantry : Boolean
)
