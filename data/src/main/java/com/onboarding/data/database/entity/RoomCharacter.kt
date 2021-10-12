package com.onboarding.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacter(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "origin") val origin: RoomLocationLink,
    @ColumnInfo(name = "location") val location: RoomLocationLink,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episodes") val episodes: List<String>,
    @ColumnInfo(name = "characterUrl") val characterUrl: String,
    @ColumnInfo(name = "created") val created: String
)
