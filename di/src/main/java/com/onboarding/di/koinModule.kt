package com.onboarding.di

import androidx.room.Room
import com.onboarding.data.database.RickAndMortyDatabaseImpl
import com.onboarding.data.database.RickAndMortyRoomDatabase
import com.onboarding.data.service.RickAndMortyRepositoryImpl
import com.onboarding.data.util.ConstantUtil
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.RickAndMortyDatabase
import com.onboarding.domain.usecase.RickAndMortyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get(), get()) }
}

val repositoryModule = module {
    single<RickAndMortyRepository> { RickAndMortyRepositoryImpl() }

    single {
        Room.databaseBuilder(
            androidContext(),
            RickAndMortyRoomDatabase::class.java,
            ConstantUtil.DATABASE_NAME
        ).build()
    }

    fun provideDao(database: RickAndMortyRoomDatabase) = database.characterDao()
    factory { provideDao(get()) }
    single<RickAndMortyDatabase> { RickAndMortyDatabaseImpl(get()) }
}
