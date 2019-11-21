package com.ruzhan.rxrepository

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {

        private var INSTANCE: App? = null

        fun get(): App {
            return INSTANCE!!
        }
    }
}
