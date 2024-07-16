package com.example.goodkotlinpractices.data.response.rick

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("info") val info: ResponseInfo,
    @SerializedName("results") val results: List<ResponseCharacter>
)
