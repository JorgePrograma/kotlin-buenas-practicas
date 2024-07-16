package com.example.goodkotlinpractices.domain.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)
