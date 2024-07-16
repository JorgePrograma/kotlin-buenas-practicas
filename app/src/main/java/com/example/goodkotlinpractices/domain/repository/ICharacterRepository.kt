package com.example.goodkotlinpractices.domain.repository

import com.example.goodkotlinpractices.domain.models.CharacterModel

interface ICharacterRepository {
    suspend fun getCharacters(): List<CharacterModel>
    suspend fun saveCharacter(character: CharacterModel)
    suspend fun updateCharacter(character: CharacterModel)
    suspend fun deleteCharacter(id: Int)
    suspend fun getCharacterById(id: Int): CharacterModel?
}