package com.onboarding.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEpisode(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "air_date") val airDate: String,
    @ColumnInfo(name = "episode") val episodeCode: String,
    @ColumnInfo(name = "characters") val characters: List<String>,
    @ColumnInfo(name = "url") val episodeUrl: String,
    @ColumnInfo(name = "created") val created: String
)
