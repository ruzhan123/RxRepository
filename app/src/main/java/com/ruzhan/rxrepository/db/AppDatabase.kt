package com.ruzhan.rxrepository.db

import android.content.Context

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.ruzhan.rxrepository.db.converter.RoomDataConverter
import com.ruzhan.rxrepository.db.dao.UserDao
import com.ruzhan.rxrepository.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomDataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    private val isDatabaseCreated = MutableLiveData<Boolean>()

    val databaseCreated: LiveData<Boolean>
        get() = isDatabaseCreated

    abstract fun userDao(): UserDao

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            isDatabaseCreated.postValue(true)
        }
    }

    companion object {

        @VisibleForTesting
        val DATABASE_NAME = "ruzhan-db"

        private var INSTANCE: AppDatabase? = null


        operator fun get(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                                .build()
                        INSTANCE!!.updateDatabaseCreated(context)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}