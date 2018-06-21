package com.ruzhan.rxrepository.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ruzhan.rxrepository.R;
import com.ruzhan.rxrepository.RemoteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void remoteClick(View view) {
        RemoteActivity.launch(this);
    }

    public void localClick(View view) {
        LocalActivity.launch(this);
    }
}
