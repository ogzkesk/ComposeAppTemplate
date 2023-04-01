package com.ogzkesk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogzkesk.data.local.entities.AppEntity

@Database(entities = [AppEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDao(): AppDao
}