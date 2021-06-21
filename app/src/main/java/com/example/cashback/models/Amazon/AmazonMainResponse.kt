package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonMainResponse(

    @SerializedName("array") val amazon_list : List<AmazonBrowse_node_infoResponse>
)
