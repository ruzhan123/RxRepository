package com.ruzhan.rxrepository.ui.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.ruzhan.rxrepository.model.UserModel;

/**
 * create ruzhan: 2018/6/21 10:11
 */
public class RemotePresenter {

    private MediatorLiveData<UserModel> useLiveData = new MediatorLiveData<>();

    public void getRemoteUser() {

    }

    public LiveData<UserModel> getUseLiveData() {
        return useLiveData;
    }
}
