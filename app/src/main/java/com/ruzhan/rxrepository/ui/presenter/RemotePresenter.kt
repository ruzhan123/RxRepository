package com.ruzhan.rxrepository.ui.presenter

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.ruzhan.rxrepository.db.entity.UserEntity
import com.ruzhan.rxrepository.model.LoadStatus
import com.ruzhan.rxrepository.model.UserModel
import com.ruzhan.rxrepository.source.RxRepository
import com.ruzhan.rxrepository.source.Subscriber

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemotePresenter {

    private val useLiveData = MutableLiveData<UserModel>()
    private val loadLiveData = MutableLiveData<LoadStatus>()

    fun getRemoteUser() {
        RxRepository.get().remoteUser
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { throwable ->
                    Log.i(TAG, "getRemoteUser doOnError: $throwable")
                }// handler exception
                .doOnSubscribe {
                    // handler request start
                    Log.i(TAG, "getRemoteUser doOnSubscribe: ")
                    loadLiveData.setValue(LoadStatus.LOADING)
                }
                .doFinally {
                    // handler request end
                    Log.i(TAG, "getRemoteUser doFinally: ")
                    loadLiveData.setValue(LoadStatus.LOADED)
                }
                .doOnSuccess { // handler request success
                    userModel ->
                    Log.i(TAG, "getRemoteUser doOnSuccess: ")
                    useLiveData.value = userModel

                    // save to db
                    setUserModelToLocal(userModel)
                }
                .subscribe(Subscriber.create())
    }

    private fun setUserModelToLocal(userModel: UserModel) {
        Flowable.create<Any>({ e ->
            val userEntity = UserEntity.getUserEntity(userModel)
            RxRepository.get().insertNewsList(userEntity)
            e.onComplete()

        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Log.i(TAG, "setUserModelToLocal doOnError: ") }
                .doOnComplete { Log.i(TAG, "setUserModelToLocal doOnComplete: ") }
                .subscribe(Subscriber.create())
    }

    fun getUseLiveData(): LiveData<UserModel> {
        return useLiveData
    }

    fun getLoadLiveData(): LiveData<LoadStatus> {
        return loadLiveData
    }

    companion object {

        private const val TAG = "RemotePresenter"
    }
}
