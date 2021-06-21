package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonExternal_idsResponse(
    @SerializedName("ea_ns") val ea_ns : AmazonEa_nsResponse,
    @SerializedName("isb_ns") val isb_ns : String,
    @SerializedName("up_cs") val up_cs : AmazonUp_csResponse
)
