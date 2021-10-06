package com.onboarding.rick_and_morty_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.rick_and_morty_app.adapter.CharacterItemAdapter
import com.onboarding.rick_and_morty_app.databinding.FragmentCharactersBinding
import com.onboarding.rick_and_morty_app.util.Event
import com.onboarding.rick_and_morty_app.viewmodel.CharacterData
import com.onboarding.rick_and_morty_app.viewmodel.CharacterStatus
import com.onboarding.rick_and_morty_app.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveDataCharacters().observe(this.viewLifecycleOwner, updateUIObserver)
        viewModel.getCharacters()
    }

    private val updateUIObserver = Observer<Event<CharacterData<List<CharacterResult>>>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.responseType) {
            CharacterStatus.SUCCESS -> {
                eventData.data?.let { successState(it) }
            }
            CharacterStatus.ERROR -> {
                errorState()
            }
            CharacterStatus.EMPTY_RESPONSE_LIST -> {
                emptyResponseState()
            }
            CharacterStatus.LOADING -> {
                loadingState()
            }
        }
    }

    private fun successState(response: List<CharacterResult>) {
        binding.progressBarCharacterFragmentLoadingState.visibility = View.GONE
        binding.recyclerViewCharactersFragment.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewCharactersFragment.adapter = CharacterItemAdapter(characters = response)
        binding.recyclerViewCharactersFragment.visibility = View.VISIBLE
    }

    private fun loadingState() {
        binding.recyclerViewCharactersFragment.visibility = View.GONE
        binding.progressBarCharacterFragmentLoadingState.visibility = View.VISIBLE
    }

    private fun errorState() {
        binding.progressBarCharacterFragmentLoadingState.visibility = View.GONE
        binding.recyclerViewCharactersFragment.visibility = View.GONE
        binding.textViewCharacterFragmentConnectionError.visibility = View.VISIBLE
    }

    private fun emptyResponseState() {
        binding.progressBarCharacterFragmentLoadingState.visibility = View.GONE
        binding.textViewCharacterFragmentEmptyResponseError.visibility = View.VISIBLE
        binding.recyclerViewCharactersFragment.visibility = View.GONE
    }
}
