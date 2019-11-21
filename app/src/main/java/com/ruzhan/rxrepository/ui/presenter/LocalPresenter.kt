package com.ruzhan.rxrepository.ui.presenter

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.ruzhan.rxrepository.db.entity.UserEntity
import com.ruzhan.rxrepository.model.LoadStatus
import com.ruzhan.rxrepository.model.UserModel
import com.ruzhan.rxrepository.source.EmptyConsumer
import com.ruzhan.rxrepository.source.RxRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


class LocalPresenter {

    private val useLiveData = MutableLiveData<UserModel>()
    private val loadLiveData = MutableLiveData<LoadStatus>()

    private var disposable: Disposable? = null

    fun getLocalUser() {
        disposable = RxRepository.get().loadUserEntity(UserEntity.NORMAL_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { throwable ->
                    Log.i(TAG, "getLocalUser doOnError: $throwable")
                } // handler exception
                .doOnSubscribe {
                    // handler request start
                    Log.i(TAG, "getLocalUser doOnSubscribe: ")
                    loadLiveData.setValue(LoadStatus.LOADING)
                }
                .doFinally {
                    // handler request end
                    Log.i(TAG, "getLocalUser doFinally: ")
                    loadLiveData.setValue(LoadStatus.LOADED)
                }
                .doOnNext { // handler request success
                    userEntity ->
                    Log.i(TAG, "getLocalUser doOnNext: ")

                    val userModel = UserEntity.getUserModel(userEntity)
                    useLiveData.value = userModel

                    disposable!!.dispose()
                }
                .subscribe(EmptyConsumer.create())
    }

    fun getUseLiveData(): LiveData<UserModel> {
        return useLiveData
    }

    fun getLoadLiveData(): LiveData<LoadStatus> {
        return loadLiveData
    }

    companion object {

        private const val TAG = "LocalPresenter"
    }
}
