package com.example.cashback.models.UserLogin

import com.example.cashback.models.UserLogin.UserLoginListResponse
import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

    @SerializedName("token") val token : String,
    @SerializedName("user") val user : UserLoginListResponse
)
