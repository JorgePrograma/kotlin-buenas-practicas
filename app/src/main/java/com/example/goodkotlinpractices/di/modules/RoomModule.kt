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
import com.example.goodkotlinpractices.domain.usecase.DeleteCharacterUseCase
import com.example.goodkotlinpractices.domain.usecase.SaveCharacterUseCase
import com.example.goodkotlinpractices.domain.usecase.UpdateCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "app_database"

    /**
     *
     *         fun buildDatabase(context: Context): AppDatabase {
     *             return Room.databaseBuilder(
     *                 context.applicationContext,
     *                 AppDatabase::class.java,
     *                 DATABASE_NAME
     *             )
     *             .fallbackToDestructiveMigration() // Permitir migraciones destructivas
     *             .build()
     *         }
     *
     * */
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase) = appDatabase.characterDao()

    @Provides
    @Singleton
    fun provideCharacterRepository(characterDao: CharacterDao): ICharacterRepository {
        return CharacterRepository(characterDao)
    }

    @Provides
    @Singleton
    fun provideRickRepository(rickDatasource: RickDatasource): RickRepository {
        return RickRepository(rickDatasource)
    }

    @Provides
    @Singleton
    fun provideSaveCharacterUseCase(characterRepository: ICharacterRepository): SaveCharacterUseCase {
        return SaveCharacterUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateCharacterUseCase(characterRepository: ICharacterRepository): UpdateCharacterUseCase {
        return UpdateCharacterUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCharacterUseCase(characterRepository: ICharacterRepository): DeleteCharacterUseCase {
        return DeleteCharacterUseCase(characterRepository)
    }
}