package com.example.goodkotlinpractices.data.response.rick

import com.google.gson.annotations.SerializedName

data class ResponseInfo(
    @SerializedName("count")  val count:Int,
    @SerializedName("pages") val pages:Int,
    @SerializedName("next") val next:String?,
    @SerializedName("prev") val prev:String?
)
