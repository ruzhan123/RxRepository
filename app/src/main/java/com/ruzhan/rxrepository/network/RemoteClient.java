package com.ruzhan.rxrepository.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
public class RemoteClient {

    private static final String HOST = "https://raw.githubusercontent.com";

    private static volatile RemoteApi api;

    private RemoteClient() {
    }

    public static RemoteApi get() {
        if (api == null) {
            synchronized (RemoteClient.class) {
                if (api == null) {
                    Retrofit client = new Retrofit.Builder().baseUrl(HOST)
                            .client(HttpClient.getHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    api = client.create(RemoteApi.class);
                }
            }
        }
        return api;
    }
}
