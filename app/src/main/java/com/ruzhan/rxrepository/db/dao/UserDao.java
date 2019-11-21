package com.ruzhan.rxrepository.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ruzhan.rxrepository.db.entity.UserEntity;

import io.reactivex.Flowable;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user_entity WHERE id = :id")
    Flowable<UserEntity> loadUserEntity(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsList(UserEntity userEntity);
}
