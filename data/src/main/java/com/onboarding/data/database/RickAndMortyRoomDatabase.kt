package com.onboarding.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.onboarding.data.database.dao.CharacterDao
import com.onboarding.data.database.dao.EpisodeDao
import com.onboarding.data.database.entity.RoomCharacter
import com.onboarding.data.database.entity.RoomEpisode
import com.onboarding.data.util.ConstantUtil
import com.onboarding.data.util.Converter

@Database(entities = [RoomCharacter::class, RoomEpisode::class], version = ConstantUtil.DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RickAndMortyRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao
}
