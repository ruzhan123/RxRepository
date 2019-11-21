package com.ruzhan.rxrepository.network

import com.ruzhan.rxrepository.model.UserModel

import io.reactivex.Single
import retrofit2.http.GET


interface RemoteApi {

    @get:GET("ruzhan123/android-repository/master/data/remote.json")
    val remoteUser: Single<UserModel>
}
