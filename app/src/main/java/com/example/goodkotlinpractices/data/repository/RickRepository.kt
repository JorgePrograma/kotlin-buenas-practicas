package com.example.goodkotlinpractices.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.goodkotlinpractices.data.CharacterPagingSource
import com.example.goodkotlinpractices.data.datasource.remote.RickDatasource
import com.example.goodkotlinpractices.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickRepository @Inject constructor(private val rickDatasource: RickDatasource) {

    companion object {
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 3 // cuando falte 3 se ara el paginado
    }

    fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { CharacterPagingSource(rickDatasource) }
        ).flow
    }
    /*
        suspend fun getAllCharacters(): List    <ResponseCharacter> {
            return try {
                val response = rickDatasource.getCharacters().results
                if (response.isNullOrEmpty()) {
                    Log.e("RickRepository", "No characters found")
                    emptyList()
                } else {
                    response
                }
            } catch (e: Exception) {
                Log.e("RickRepository", "Error fetching characters", e)
                emptyList()
            }
        }*/
}