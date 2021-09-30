package com.onboarding.rick_and_morty_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.onboarding.rick_and_morty_app.R
import com.onboarding.rick_and_morty_app.adapter.ViewPagerAdapter
import com.onboarding.rick_and_morty_app.databinding.ActivityMainBinding
import com.onboarding.rick_and_morty_app.fragment.CharactersFragment
import com.onboarding.rick_and_morty_app.fragment.LocationFragments
import com.onboarding.rick_and_morty_app.fragment.SeasonsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(
        CharactersFragment(),
        SeasonsFragment(),
        LocationFragments()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPagerMainActivity.adapter = ViewPagerAdapter(fragments, this.supportFragmentManager, this.lifecycle)
        attachMediator(binding.tabLayoutMainActivity, binding.viewPagerMainActivity)
    }

    private fun attachMediator(tabLayout: TabLayout, viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                CHARACTERS_TAB -> {
                    tab.text = getString(R.string.main_activity_tab_name_characters)
                }
                SEASONS_TAB -> {
                    tab.text = getString(R.string.main_activity_tab_name_seasons)
                }
                LOCATIONS_TAB -> {
                    tab.text = getString(R.string.main_activity_tab_name_locations)
                }
            }
        }.attach()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)

        private const val CHARACTERS_TAB = 0
        private const val SEASONS_TAB = 1
        private const val LOCATIONS_TAB = 2
    }
}
