package com.onboarding.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomLocationLink(
    @PrimaryKey val locationName: String,
    @ColumnInfo(name = "url") val locationUrl: String
)
