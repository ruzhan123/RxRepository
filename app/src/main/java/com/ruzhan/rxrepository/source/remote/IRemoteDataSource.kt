package com.ruzhan.rxrepository.source.remote

import com.ruzhan.rxrepository.model.UserModel

import io.reactivex.Single


interface IRemoteDataSource {

    val remoteUser: Single<UserModel>
}
