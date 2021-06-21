package com.example.cashback.models

import com.google.gson.annotations.SerializedName

data class GetCartResponse(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<GetCartListResponse>

)
