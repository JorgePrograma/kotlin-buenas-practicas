package com.example.goodkotlinpractices.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.goodkotlinpractices.data.repository.CharacterRepository
import com.example.goodkotlinpractices.data.repository.RickRepository
import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.domain.usecase.GetCharactersUseCase
import com.example.goodkotlinpractices.domain.usecase.SaveCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickListViewModel @Inject constructor(
    private val repository: RickRepository,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val characters: Flow<PagingData<CharacterModel>> = repository.getAllCharacters()
    var characterFavorite: List<CharacterModel> = emptyList()

    private val _saveCharacterEvent = MutableSharedFlow<Result<Unit>>()
    val saveCharacterEvent = _saveCharacterEvent.asSharedFlow()

    fun saveCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            val result = saveCharacterUseCase(characterModel)
            _saveCharacterEvent.emit(result)
        }
    }

    init {
        Log.d("RickListViewModel", "Verificando si funciona la room")
        viewModelScope.launch {
            try {
                characterFavorite = getCharactersUseCase()
                if (characterFavorite.isEmpty()) {
                    Log.d("RickListViewModel", "No characters in database")
                } else {
                    Log.d("RickListViewModel", "Characters in database:")
                    characterFavorite.forEach { character ->
                        Log.d("RickListViewModel", character.name)
                    }
                }
            } catch (e: Exception) {
                Log.e("RickListViewModel", "Error collecting characters", e)
            }
        }
    }
}
