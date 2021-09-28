package com.onboarding.rick_and_morty_app

import android.app.Application
import com.onboarding.rick_and_morty_app.di.viewModelModule
import org.koin.core.context.startKoin

class RickAndMortyverseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelModule))
        }
    }
}
