RxRepository
===============



![](https://github.com/ruzhan123/RxRepository/raw/master/gif/image.png)


**RxRepository**

```java

public class RxRepository {

    private static RxRepository INSTANCE;

    private IRemoteDataSource remoteDataSource;
    private ILocalDataSource localDataSource;

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

```


**Presenter**

```java

public class RemotePresenter {

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
}

```

**Activity / Fragment**

```java

    // refresh userModel, update ui
    presenter.getUseLiveData().observe(this, userModel -> {
        if (userModel != null) {
            nameTv.setText("name: " + userModel.name);
            descTv.setText("desc: " + userModel.desc);
            Toast.makeText(this, "name:  " + userModel.name +
                            "\n" + " desc:  " + userModel.desc,
                    Toast.LENGTH_SHORT).show();
        }
    });


    // request network
    presenter.getRemoteUser();

```