package com.onboarding.viewmodel

import com.onboarding.rick_and_morty_app.viewmodel.SplashViewModel
import com.onboarding.rick_and_morty_app.viewmodel.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {
    private val viewModel = SplashViewModel()

    @Test
    fun loadMainActivityTest() {
        runBlocking {
            viewModel.loadMainActivity().join()
        }
        assertEquals(Status.LOAD_COMPLETE, viewModel.getLiveDataSplash().value?.peekContent()?.responseType)
        assertEquals(true, viewModel.getLiveDataSplash().value?.peekContent()?.data)
    }
}
