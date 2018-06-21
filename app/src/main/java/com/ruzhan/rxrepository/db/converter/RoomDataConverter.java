package com.ruzhan.rxrepository.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
public class RoomDataConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
