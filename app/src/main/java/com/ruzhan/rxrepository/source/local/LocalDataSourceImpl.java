package com.ruzhan.rxrepository.source.local;

import com.ruzhan.rxrepository.App;
import com.ruzhan.rxrepository.db.AppDatabase;
import com.ruzhan.rxrepository.db.entity.UserEntity;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * create ruzhan: 2018/6/21 10:09
 */
public class LocalDataSourceImpl implements ILocalDataSource {

    private AppDatabase database;

    public LocalDataSourceImpl() {
        database = AppDatabase.get(App.get());
    }

    @Override
    public Flowable<UserEntity> loadUserEntity(String id) {
        return database.userDao().loadUserEntity(id).subscribeOn(Schedulers.io());
    }

    @Override
    public void insertNewsList(UserEntity userEntity) {
        database.userDao().insertNewsList(userEntity);
    }
}
