package com.ruzhan.rxrepository.source.local

import com.ruzhan.rxrepository.db.entity.UserEntity

import io.reactivex.Flowable


interface ILocalDataSource {

    fun loadUserEntity(id: String): Flowable<UserEntity>

    fun insertNewsList(userEntity: UserEntity)
}
