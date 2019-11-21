package com.ruzhan.rxrepository.source

import com.ruzhan.rxrepository.db.entity.UserEntity
import com.ruzhan.rxrepository.model.UserModel
import com.ruzhan.rxrepository.source.local.ILocalDataSource
import com.ruzhan.rxrepository.source.local.LocalDataSourceImpl
import com.ruzhan.rxrepository.source.remote.IRemoteDataSource
import com.ruzhan.rxrepository.source.remote.RemoteDataSourceImpl

import io.reactivex.Flowable
import io.reactivex.Single


class RxRepository private constructor() {

    private val remoteDataSource: IRemoteDataSource
    private val localDataSource: ILocalDataSource

    val remoteUser: Single<UserModel>
        get() = remoteDataSource.remoteUser

    init {
        remoteDataSource = RemoteDataSourceImpl()
        localDataSource = LocalDataSourceImpl()
    }

    fun loadUserEntity(id: String): Flowable<UserEntity> {
        return localDataSource.loadUserEntity(id)
    }

    fun insertNewsList(userEntity: UserEntity) {
        localDataSource.insertNewsList(userEntity)
    }

    companion object {

        private var INSTANCE: RxRepository? = null

        fun get(): RxRepository {
            if (INSTANCE == null) {
                synchronized(RxRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = RxRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}
