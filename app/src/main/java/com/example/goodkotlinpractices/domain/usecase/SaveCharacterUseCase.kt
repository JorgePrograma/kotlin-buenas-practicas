package com.example.goodkotlinpractices.domain.usecase

import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.domain.repository.ICharacterRepository
import javax.inject.Inject

class SaveCharacterUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {
    suspend operator fun invoke(characterModel: CharacterModel): Result<Unit> {
        return try {
            characterRepository.saveCharacter(characterModel)
            Result.success(Unit)
        } catch (e: Exception) {
            println("Error al guardar el personaje: ${e.message}")
            Result.failure(e)
        }
    }
}
