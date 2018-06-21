package com.ruzhan.rxrepository.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ruzhan.rxrepository.R;
import com.ruzhan.rxrepository.ui.presenter.LocalPresenter;

/**
 * create ruzhan: 2018/6/21 09:50
 */
public class LocalActivity extends AppCompatActivity {

    private TextView nameTv;
    private TextView descTv;

    private LocalPresenter presenter = new LocalPresenter();

    public static void launch(Context context) {
        Intent intent = new Intent(context, LocalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        nameTv = findViewById(R.id.name_tv);
        descTv = findViewById(R.id.desc_tv);
    }
}
