package com.onboarding.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onboarding.data.database.entity.RoomEpisode

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisode(episode: RoomEpisode)

    @Query("SELECT * FROM roomepisode")
    fun getAllEpisodes(): List<RoomEpisode>
}
