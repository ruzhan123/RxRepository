package com.ruzhan.rxrepository.db.converter


import androidx.room.TypeConverter
import java.util.*

object RoomDataConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
