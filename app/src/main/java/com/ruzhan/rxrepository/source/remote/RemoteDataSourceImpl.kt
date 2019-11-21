package com.ruzhan.rxrepository.source.remote

import com.ruzhan.rxrepository.model.UserModel
import com.ruzhan.rxrepository.network.RemoteClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class RemoteDataSourceImpl : IRemoteDataSource {

    private val api = RemoteClient.get()

    override val remoteUser: Single<UserModel>
        get() = api.remoteUser.subscribeOn(Schedulers.io())
}
