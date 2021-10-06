package com.onboarding.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onboarding.data.database.entity.RoomCharacter

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: RoomCharacter)

    @Query("SELECT * FROM roomcharacter")
    fun getAllCharacters(): List<RoomCharacter>
}
