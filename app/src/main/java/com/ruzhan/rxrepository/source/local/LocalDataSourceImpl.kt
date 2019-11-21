package com.ruzhan.rxrepository.source.local

import com.ruzhan.rxrepository.App
import com.ruzhan.rxrepository.db.AppDatabase
import com.ruzhan.rxrepository.db.entity.UserEntity

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers


class LocalDataSourceImpl : ILocalDataSource {

    private val database: AppDatabase = AppDatabase[App.get()]

    override fun loadUserEntity(id: String): Flowable<UserEntity> {
        return database.userDao().loadUserEntity(id).subscribeOn(Schedulers.io())
    }

    override fun insertNewsList(userEntity: UserEntity) {
        database.userDao().insertNewsList(userEntity)
    }
}
