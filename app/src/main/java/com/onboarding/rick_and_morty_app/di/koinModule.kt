package com.onboarding.rick_and_morty_app.di

import com.onboarding.rick_and_morty_app.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
}
