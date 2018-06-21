package com.ruzhan.rxrepository.ui.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ruzhan.rxrepository.model.UserModel;

/**
 * create ruzhan: 2018/6/21 10:11
 */
public class LocalPresenter {

    private MutableLiveData<UserModel> useLiveData = new MutableLiveData<>();

    public void getLocalUser() {

    }

    public LiveData<UserModel> getUseLiveData() {
        return useLiveData;
    }
}
