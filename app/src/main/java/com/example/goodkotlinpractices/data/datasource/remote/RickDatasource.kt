package com.example.goodkotlinpractices.data.datasource.remote

import com.example.goodkotlinpractices.data.response.rick.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RickDatasource {
    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int):ResponseWrapper
}