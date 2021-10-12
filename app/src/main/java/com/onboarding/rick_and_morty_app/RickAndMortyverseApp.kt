package com.onboarding.rick_and_morty_app

import android.app.Application
import com.onboarding.di.repositoryModule
import com.onboarding.di.useCasesModule
import com.onboarding.rick_and_morty_app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyverseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyverseApp)
            modules(listOf(viewModelModule, repositoryModule, useCasesModule))
        }
    }
}
