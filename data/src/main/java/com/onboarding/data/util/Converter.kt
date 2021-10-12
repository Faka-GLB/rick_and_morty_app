package com.onboarding.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.onboarding.data.database.entity.RoomLocationLink

class Converter {
    @TypeConverter
    fun List<String>.fromStringList(): String = Gson().toJson(this, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun String.toStringList(): List<String> = Gson().fromJson(this, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun RoomLocationLink.fromRoomLocationLink(): String = Gson().toJson(this, object : TypeToken<RoomLocationLink>() {}.type)

    @TypeConverter
    fun String.toRoomLocationLink(): RoomLocationLink = Gson().fromJson(this, object : TypeToken<RoomLocationLink>() {}.type)
}
