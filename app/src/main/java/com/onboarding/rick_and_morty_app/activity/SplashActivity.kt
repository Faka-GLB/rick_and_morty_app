package com.onboarding.rick_and_morty_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.onboarding.rick_and_morty_app.R
import com.onboarding.rick_and_morty_app.databinding.SplashActivityBinding
import com.onboarding.rick_and_morty_app.util.Event
import com.onboarding.rick_and_morty_app.viewmodel.Data
import com.onboarding.rick_and_morty_app.viewmodel.SplashViewModel
import com.onboarding.rick_and_morty_app.viewmodel.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMortyApp)
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getLiveDataSplash().observe(this, updateUIObserver)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMainActivity()
    }

    private val updateUIObserver = Observer<Event<Data<Boolean>>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.responseType) {
            Status.LOAD_COMPLETE -> {
                successState()
            }
        }
    }

    private fun successState() {
        startActivity(MainActivity.getIntent(this))
        this.finish()
    }
}
