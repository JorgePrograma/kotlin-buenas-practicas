import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodkotlinpractices.domain.models.CharacterModel

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel = viewModel()) {
    val favoriteCharacters = favoriteViewModel.favoriteCharacters.value

    LazyColumn {
        items(favoriteCharacters) { character ->
            CharacterListItem(character = character)
        }
    }
}

@Composable
fun CharacterListItem(character: CharacterModel) {
    Text(text = character.name)
}

@Preview
@Composable
fun PreviewFavoriteScreen() {
    // Aquí puedes usar datos de ejemplo para previsualizar cómo se verá la pantalla
    val dummyCharacters = listOf(
        CharacterModel(id = 1, name = "Rick Sanchez", status = "Alive", species = "Humanoid", image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
        CharacterModel(id = 2, name = "Morty Smith", status = "Alive", species = "Human", image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg")
    )
    LazyColumn {
        items(dummyCharacters) { character ->
            CharacterListItem(character = character)
        }
    }
}
