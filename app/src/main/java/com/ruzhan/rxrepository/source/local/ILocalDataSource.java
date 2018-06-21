package com.ruzhan.rxrepository.source.local;

import com.ruzhan.rxrepository.db.entity.UserEntity;

import io.reactivex.Flowable;

/**
 * create ruzhan: 2018/6/21 10:09
 */
public interface ILocalDataSource {

    Flowable<UserEntity> loadUserEntity(String id);

    void insertNewsList(UserEntity userEntity);
}
