package com.example.cashback.models.UserLogin

import com.google.gson.annotations.SerializedName

data class UserLoginListResponse(

    @SerializedName("username") val username : String,
    @SerializedName("full_name") val full_name : String,
    @SerializedName("first_name") val first_name : String,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("profile_pic") val profile_pic : String,
    @SerializedName("date_of_birth") val date_of_birth : String,
    @SerializedName("mobile_number") val mobile_number : String,
    @SerializedName("email") val email : String
)
