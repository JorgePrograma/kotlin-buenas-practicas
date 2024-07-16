package com.example.goodkotlinpractices.data.repository

import com.example.goodkotlinpractices.data.datasource.local.room.dao.CharacterDao
import com.example.goodkotlinpractices.data.mapper.toDomainModel
import com.example.goodkotlinpractices.data.mapper.toEntityModel
import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.domain.repository.ICharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) : ICharacterRepository {

    override suspend fun getCharacters(): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            try {
                characterDao.getAllCharacters().map {
                    it.toDomainModel()
                }
            } catch (e: Exception) {
                println("Error al obtener los personajes: ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun saveCharacter(character: CharacterModel) {
        return withContext(Dispatchers.IO) {
            try {
                characterDao.insert(character.toEntityModel())
            } catch (e: Exception) {
                println("Error al guardar el personaje: ${e.message}")
            }
        }
    }

    override suspend fun updateCharacter(character: CharacterModel) {
        return withContext(Dispatchers.IO) {
            try {
                //characterDao.(character.toEntityModel())
            } catch (e: Exception) {
                println("Error al actualizar el personaje: ${e.message}")
            }
        }
    }

    override suspend fun deleteCharacter(id: Int) {
        return withContext(Dispatchers.IO) {
            try {
                characterDao.deleteCharacters(id)
            } catch (e: Exception) {
                println("Error al eliminar el personaje: ${e.message}")
            }
        }
    }

    override suspend fun getCharacterById(id: Int): CharacterModel? {
        return withContext(Dispatchers.IO) {
            try {
                characterDao.getCharacterById(id)?.toDomainModel()
            } catch (e: Exception) {
                println("Error al obtener el personaje por ID: ${e.message}")
                null
            }
        }
    }
}
