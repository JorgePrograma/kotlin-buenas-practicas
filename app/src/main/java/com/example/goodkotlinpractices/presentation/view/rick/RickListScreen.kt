package com.example.goodkotlinpractices.presentation.view.rick

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.goodkotlinpractices.domain.models.CharacterModel
import com.example.goodkotlinpractices.presentation.viewmodel.RickListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun RickListScreen(rickListViewModel: RickListViewModel = hiltViewModel()) {

    val characters = rickListViewModel.characters.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        rickListViewModel.saveCharacterEvent.collect { result ->
            withContext(Dispatchers.Main) {
                result.onSuccess {
                    Toast.makeText(context, "Character saved", Toast.LENGTH_SHORT).show()
                }.onFailure { error ->
                    Toast.makeText(context, "Error saving character: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when {
        //Carga inicial
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.White
                )
            }
        }

        //Estado vacio
        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Todavía no hay personajes")
            }
        }

        characters.loadState.refresh is LoadState.Error -> {
            val error = characters.loadState.refresh as LoadState.Error
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Red), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ha ocurrido un error: ${error.error.localizedMessage}")
            }
        }

        else -> {
            CharactersList(characters, rickListViewModel::saveCharacter)

            if (characters.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp), color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CharactersList(
    characters: LazyPagingItems<CharacterModel>,
    onSaveCharacter: (CharacterModel) -> Unit
) {
    LazyColumn {
        items(characters.itemCount) { index ->
            characters[index]?.let { characterModel ->
                ItemList(characterModel, onSaveCharacter)
            }
        }
    }
}

@Composable
fun ItemList(characterModel: CharacterModel, onSaveCharacter: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Green, shape = RoundedCornerShape(0, 24, 0, 24))
            .fillMaxWidth()
            .height(250.dp), contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Text(
                    text = characterModel.name,
                    color = Color.White,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = { onSaveCharacter(characterModel) }) {
                    Text(text = "Guardar", color = Color.White)
                }
            }
        }
    }
}
