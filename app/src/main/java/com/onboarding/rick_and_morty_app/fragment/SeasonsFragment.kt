package com.onboarding.rick_and_morty_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.rick_and_morty_app.adapter.EpisodeItemAdapter
import com.onboarding.rick_and_morty_app.databinding.FragmentSeasonsBinding
import com.onboarding.rick_and_morty_app.util.Event
import com.onboarding.rick_and_morty_app.viewmodel.EpisodeData
import com.onboarding.rick_and_morty_app.viewmodel.EpisodeStatus
import com.onboarding.rick_and_morty_app.viewmodel.SeasonsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeasonsFragment : Fragment() {
    private lateinit var binding: FragmentSeasonsBinding
    private val viewModel: SeasonsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentSeasonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveDataEpisodes().observe(this.viewLifecycleOwner, updateUIObserver)
        viewModel.getEpisodes()
    }

    private val updateUIObserver = Observer<Event<EpisodeData<List<EpisodeResult>>>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.responseType) {
            EpisodeStatus.SUCCESS -> {
                eventData.data?.let { successState(it) }
            }
            EpisodeStatus.ERROR -> {
                errorState()
            }
            EpisodeStatus.EMPTY_RESPONSE_LIST -> {
                emptyResponseState()
            }
            EpisodeStatus.LOADING -> {
                loadingState()
            }
        }
    }

    private fun successState(episodes: List<EpisodeResult>) {
        binding.progressBarSeasonsFragmentLoadingState.visibility = View.GONE
        binding.recyclerViewSeasonsFragment.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewSeasonsFragment.adapter = EpisodeItemAdapter(episodes)
        binding.recyclerViewSeasonsFragment.visibility = View.VISIBLE
    }

    private fun errorState() {
        binding.progressBarSeasonsFragmentLoadingState.visibility = View.GONE
        binding.textViewSeasonsFragmentConnectionError.visibility = View.VISIBLE
        binding.recyclerViewSeasonsFragment.visibility = View.GONE
    }

    private fun loadingState() {
        binding.recyclerViewSeasonsFragment.visibility = View.GONE
        binding.progressBarSeasonsFragmentLoadingState.visibility = View.VISIBLE
    }

    private fun emptyResponseState() {
        binding.progressBarSeasonsFragmentLoadingState.visibility = View.GONE
        binding.textViewSeasonsFragmentEmptyResponseError.visibility = View.VISIBLE
        binding.recyclerViewSeasonsFragment.visibility = View.VISIBLE
    }
}
