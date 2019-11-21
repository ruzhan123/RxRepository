package com.ruzhan.rxrepository.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteClient {

    private const val HOST = "https://raw.githubusercontent.com"

    @Volatile
    private var api: RemoteApi? = null

    fun get(): RemoteApi {
        if (api == null) {
            synchronized(RemoteClient::class.java) {
                if (api == null) {
                    val client = Retrofit.Builder().baseUrl(HOST)
                            .client(HttpClient.getHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                    api = client.create(RemoteApi::class.java)
                }
            }
        }
        return api!!
    }
}
