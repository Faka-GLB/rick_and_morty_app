package com.onboarding.rick_and_morty_app.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onboarding.rick_and_morty_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
