package com.onboarding.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE 'roomepisode' ('id' INTEGER NOT NULL, 'name' TEXT NOT NULL, 'air_date' TEXT NOT NULL, 'episode' TEXT NOT NULL," +
                " 'characters' TEXT NOT NULL, 'url' TEXT NOT NULL, 'created' TEXT NOT NULL, PRIMARY KEY('id'))"
        )
    }
}
