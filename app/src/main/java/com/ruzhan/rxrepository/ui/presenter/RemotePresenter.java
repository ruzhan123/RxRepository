package com.ruzhan.rxrepository.ui.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ruzhan.rxrepository.db.entity.UserEntity;
import com.ruzhan.rxrepository.model.LoadStatus;
import com.ruzhan.rxrepository.model.UserModel;
import com.ruzhan.rxrepository.source.RxRepository;
import com.ruzhan.rxrepository.source.Subscriber;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create ruzhan: 2018/6/21 10:11
 */
public class RemotePresenter {

    private static final String TAG = "RemotePresenter";

    private MutableLiveData<UserModel> useLiveData = new MutableLiveData<>();
    private MutableLiveData<LoadStatus> loadLiveData = new MutableLiveData<>();

    public void getRemoteUser() {
        RxRepository.get().getRemoteUser()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.i(TAG, "getRemoteUser doOnError: " +
                        throwable))// handler exception
                .doOnSubscribe(disposable -> { // handler request start
                    Log.i(TAG, "getRemoteUser doOnSubscribe: ");
                    loadLiveData.setValue(LoadStatus.LOADING);
                })
                .doFinally(() -> { // handler request end
                    Log.i(TAG, "getRemoteUser doFinally: ");
                    loadLiveData.setValue(LoadStatus.LOADED);
                })
                .doOnSuccess(userModel -> { // handler request success
                    Log.i(TAG, "getRemoteUser doOnSuccess: ");
                    useLiveData.setValue(userModel);

                    // save to db
                    setUserModelToLocal(userModel);
                })
                .subscribe(Subscriber.create());
    }

    private void setUserModelToLocal(UserModel userModel) {
        Flowable.create(e -> {
            UserEntity userEntity = UserEntity.getUserEntity(userModel);
            RxRepository.get().insertNewsList(userEntity);
            e.onComplete();

        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.i(TAG, "setUserModelToLocal doOnError: "))
                .doOnComplete(() -> Log.i(TAG, "setUserModelToLocal doOnComplete: "))
                .subscribe(Subscriber.create());
    }

    public LiveData<UserModel> getUseLiveData() {
        return useLiveData;
    }

    public LiveData<LoadStatus> getLoadLiveData() {
        return loadLiveData;
    }
}
