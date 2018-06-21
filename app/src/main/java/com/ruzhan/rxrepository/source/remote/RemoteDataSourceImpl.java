package com.ruzhan.rxrepository.source.remote;

import com.ruzhan.rxrepository.model.UserModel;
import com.ruzhan.rxrepository.network.RemoteApi;
import com.ruzhan.rxrepository.network.RemoteClient;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * create ruzhan: 2018/6/21 10:10
 */
public class RemoteDataSourceImpl implements IRemoteDataSource {

    private RemoteApi api;

    public RemoteDataSourceImpl() {
        api = RemoteClient.get();
    }

    @Override
    public Single<UserModel> getRemoteUser() {
        return api.getRemoteUser().subscribeOn(Schedulers.io());
    }
}
