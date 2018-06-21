package com.ruzhan.rxrepository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ruzhan.rxrepository.db.entity.UserEntity;

import io.reactivex.Flowable;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user_entity WHERE id = :id")
    Flowable<UserEntity> loadUserEntity(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsList(UserEntity userEntity);
}
