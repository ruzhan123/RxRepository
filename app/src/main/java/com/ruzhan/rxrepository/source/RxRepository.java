package com.ruzhan.rxrepository.source;

import com.ruzhan.rxrepository.db.entity.UserEntity;
import com.ruzhan.rxrepository.model.UserModel;
import com.ruzhan.rxrepository.source.local.ILocalDataSource;
import com.ruzhan.rxrepository.source.local.LocalDataSourceImpl;
import com.ruzhan.rxrepository.source.remote.IRemoteDataSource;
import com.ruzhan.rxrepository.source.remote.RemoteDataSourceImpl;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * create ruzhan: 2018/6/21 10:08
 */
public class RxRepository {

    private static RxRepository INSTANCE;

    private IRemoteDataSource remoteDataSource;
    private ILocalDataSource localDataSource;

    private RxRepository() {
        remoteDataSource = new RemoteDataSourceImpl();
        localDataSource = new LocalDataSourceImpl();
    }

    public static RxRepository get() {
        if (INSTANCE == null) {
            synchronized (RxRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Single<UserModel> getRemoteUser() {
        return remoteDataSource.getRemoteUser();
    }

    public Flowable<UserEntity> loadUserEntity(String id) {
        return localDataSource.loadUserEntity(id);
    }

    public void insertNewsList(UserEntity userEntity) {
        localDataSource.insertNewsList(userEntity);
    }

}
