package com.onboarding.rick_and_morty_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.rick_and_morty_app.R
import com.onboarding.rick_and_morty_app.databinding.ItemCharacterBinding

class CharacterItemAdapter(private val characters: List<CharacterResult>) : RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: CharacterResult) {
            with(ItemCharacterBinding.bind(itemView)) {
                textViewItemCharacterName.text = character.name
                Glide.with(itemView.context)
                    .load(character.image)
                    .transform(RoundedCorners(CORNER_RADIUS))
                    .into(imageViewItemCharacterAvatar)
            }
        }
    }

    companion object {
        private const val CORNER_RADIUS = 8
    }
}
