package com.ruzhan.rxrepository

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.ruzhan.rxrepository.ui.RemoteActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun remoteClick(view: View) {
        RemoteActivity.launch(this)
    }
}
