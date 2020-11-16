package me.vponomarenko.letmethink.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

/**
 * Author: Valery Ponomarenko
 * Date: 03/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

object DatabaseMigrationFactory {

    fun migrationFrom1to2() = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE story ADD COLUMN isForParty INTEGER NOT NULL DEFAULT 0")
        }
    }
}