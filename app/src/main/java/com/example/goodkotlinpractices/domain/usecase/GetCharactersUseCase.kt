package com.example.goodkotlinpractices.domain.usecase

import com.example.goodkotlinpractices.domain.repository.ICharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {
    suspend operator fun invoke() = characterRepository.getCharacters()
}