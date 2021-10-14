package com.onboarding.rick_and_morty_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.rick_and_morty_app.R
import com.onboarding.rick_and_morty_app.databinding.ItemSeasonsBinding

class EpisodeItemAdapter(private val episodes: List<EpisodeResult>) : RecyclerView.Adapter<EpisodeItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_seasons, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount() = episodes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(episode: EpisodeResult) {
            with(ItemSeasonsBinding.bind(itemView)) {
                textViewItemSeasonsEpisodeCode.text = episode.episodeCode
                textViewItemSeasonsAirDate.text =
                    itemView.context.getString(R.string.item_seasons_episode_air_date_text_view_text, episode.airDate)
                textViewItemSeasonsEpisodeName.text = episode.episodeName
            }
        }
    }
}
