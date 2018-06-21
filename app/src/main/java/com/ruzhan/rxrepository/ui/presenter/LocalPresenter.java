package com.ruzhan.rxrepository.ui.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ruzhan.rxrepository.db.entity.UserEntity;
import com.ruzhan.rxrepository.model.LoadStatus;
import com.ruzhan.rxrepository.model.UserModel;
import com.ruzhan.rxrepository.source.EmptyConsumer;
import com.ruzhan.rxrepository.source.RxRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * create ruzhan: 2018/6/21 10:11
 */
public class LocalPresenter {

    private static final String TAG = "LocalPresenter";

    private MutableLiveData<UserModel> useLiveData = new MutableLiveData<>();
    private MutableLiveData<LoadStatus> loadLiveData = new MutableLiveData<>();

    private Disposable disposable;

    public void getLocalUser() {
        disposable = RxRepository.get().loadUserEntity(UserEntity.NORMAL_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.i(TAG, "getLocalUser doOnError: " +
                        throwable)) // handler exception
                .doOnSubscribe(disposable -> { // handler request start
                    Log.i(TAG, "getLocalUser doOnSubscribe: ");
                    loadLiveData.setValue(LoadStatus.LOADING);
                })
                .doFinally(() -> { // handler request end
                    Log.i(TAG, "getLocalUser doFinally: ");
                    loadLiveData.setValue(LoadStatus.LOADED);
                })
                .doOnNext(userEntity -> { // handler request success
                    Log.i(TAG, "getLocalUser doOnNext: ");

                    UserModel userModel = UserEntity.getUserModel(userEntity);
                    useLiveData.setValue(userModel);

                    disposable.dispose();
                })
                .subscribe(EmptyConsumer.create());
    }

    public LiveData<UserModel> getUseLiveData() {
        return useLiveData;
    }

    public LiveData<LoadStatus> getLoadLiveData() {
        return loadLiveData;
    }
}
