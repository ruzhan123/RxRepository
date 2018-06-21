package com.ruzhan.rxrepository.source.remote;

import com.ruzhan.rxrepository.model.UserModel;

import io.reactivex.Single;

/**
 * create ruzhan: 2018/6/21 10:10
 */
public interface IRemoteDataSource {

    Single<UserModel> getRemoteUser();
}
