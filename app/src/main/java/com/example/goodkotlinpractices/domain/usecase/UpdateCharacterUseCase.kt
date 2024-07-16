package com.example.goodkotlinpractices.domain.usecase

import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.domain.repository.ICharacterRepository
import javax.inject.Inject

class UpdateCharacterUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {
    suspend operator fun invoke(characterModel: CharacterModel){
        characterRepository.updateCharacter(characterModel)
    }
}