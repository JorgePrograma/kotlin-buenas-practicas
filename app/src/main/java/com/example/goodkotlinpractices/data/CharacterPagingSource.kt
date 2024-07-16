package com.example.goodkotlinpractices.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.goodkotlinpractices.data.datasource.remote.RickDatasource
import com.example.goodkotlinpractices.data.mapper.toDomainModel
import com.example.goodkotlinpractices.domain.models.CharacterModel
import okio.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val rickDatasource: RickDatasource) :
    PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = rickDatasource.getCharacters(page)

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (response.info.next != null) page + 1 else null
            val characters = response.results.map { characterModel ->
                characterModel.toDomainModel()
            }

            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}
