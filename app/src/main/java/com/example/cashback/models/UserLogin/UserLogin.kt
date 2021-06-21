package com.example.cashback.models.UserLogin

import com.example.cashback.models.UserLogin.UserLoginListResponse
import com.google.gson.annotations.SerializedName

data class UserLogin(

    @SerializedName("Message") val message : String,
    @SerializedName("Status") val status : Boolean,
    @SerializedName("response") val response : UserLoginResponse

)
