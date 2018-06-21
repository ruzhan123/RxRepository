package com.ruzhan.rxrepository.network;

import com.ruzhan.rxrepository.model.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
public interface RemoteApi {

    @GET("ruzhan123/RxRepository/master/data/remote.json")
    Single<UserModel> getRemoteUser();
}
