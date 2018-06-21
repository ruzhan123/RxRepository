package com.ruzhan.rxrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ruzhan.rxrepository.ui.RemoteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void remoteClick(View view) {
        RemoteActivity.launch(this);
    }
}
