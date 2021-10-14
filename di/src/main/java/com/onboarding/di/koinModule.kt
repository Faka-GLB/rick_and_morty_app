package com.onboarding.di

import androidx.room.Room
import com.onboarding.data.database.RickAndMortyDatabaseImpl
import com.onboarding.data.database.RickAndMortyRoomDatabase
import com.onboarding.data.database.migration.MIGRATION_1_2
import com.onboarding.data.service.RickAndMortyRepositoryImpl
import com.onboarding.data.util.ConstantUtil
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.GetAllEpisodesUseCase
import com.onboarding.domain.usecase.GetAllEpisodesUseCaseImpl
import com.onboarding.domain.usecase.RickAndMortyDatabase
import com.onboarding.domain.usecase.RickAndMortyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get(), get()) }
    single<GetAllEpisodesUseCase> { GetAllEpisodesUseCaseImpl(get(), get()) }
}

val repositoryModule = module {
    single<RickAndMortyRepository> { RickAndMortyRepositoryImpl() }

    single {
        Room.databaseBuilder(
            androidContext(),
            RickAndMortyRoomDatabase::class.java,
            ConstantUtil.DATABASE_NAME
        ).addMigrations(MIGRATION_1_2).build()
    }

    fun provideCharacterDao(database: RickAndMortyRoomDatabase) = database.characterDao()
    fun provideEpisodeDao(database: RickAndMortyRoomDatabase) = database.episodeDao()

    factory { provideCharacterDao(get()) }
    factory { provideEpisodeDao(get()) }
    single<RickAndMortyDatabase> { RickAndMortyDatabaseImpl(get(), get()) }
}
