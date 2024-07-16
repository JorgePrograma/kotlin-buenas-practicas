import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _favoriteCharacters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val favoriteCharacters: StateFlow<List<CharacterModel>> = _favoriteCharacters

    init {
        loadFavoriteCharacters()
    }

    private fun loadFavoriteCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = getCharactersUseCase()
                _favoriteCharacters.value = characters
            } catch (e: Exception) {
                // Handle error, e.g., log or display error message
            }
        }
    }
}
