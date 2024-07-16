package com.example.goodkotlinpractices.data.response.rick

import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.google.gson.annotations.SerializedName

data class ResponseCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("image") val image: String
)