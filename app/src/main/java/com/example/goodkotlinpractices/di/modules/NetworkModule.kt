package com.example.goodkotlinpractices.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.goodkotlinpractices.data.datasource.local.room.AppDatabase
import com.example.goodkotlinpractices.data.datasource.local.room.dao.CharacterDao
import com.example.goodkotlinpractices.data.datasource.remote.RickDatasource
import com.example.goodkotlinpractices.data.repository.CharacterRepository
import com.example.goodkotlinpractices.data.repository.RickRepository
import com.example.goodkotlinpractices.domain.repository.ICharacterRepository
import com.example.goodkotlinpractices.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideRickDatasource(retrofit: Retrofit): RickDatasource =
        retrofit.create(RickDatasource::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()


}
