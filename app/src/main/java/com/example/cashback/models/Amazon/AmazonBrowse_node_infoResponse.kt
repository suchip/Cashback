package com.example.cashback.models.Amazon

import com.google.gson.annotations.SerializedName

data class AmazonBrowse_node_infoResponse(
    @SerializedName("browse_nodes") val browse_nodes : List<AmazonBrowsenodesResponse>,
    @SerializedName("website_sales_rank") val website_sales_rank : AmazonWebsite_sales_rankResponse
)
