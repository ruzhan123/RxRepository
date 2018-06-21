package com.ruzhan.rxrepository.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.ruzhan.rxrepository.R;
import com.ruzhan.rxrepository.model.LoadStatus;
import com.ruzhan.rxrepository.ui.presenter.LocalPresenter;

/**
 * create ruzhan: 2018/6/21 09:50
 */
public class LocalActivity extends AppCompatActivity {

    private TextView nameTv;
    private TextView descTv;
    private TextView loadTv;

    private LocalPresenter presenter = new LocalPresenter();

    public static void launch(Context context) {
        Intent intent = new Intent(context, LocalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        nameTv = findViewById(R.id.name_tv);
        descTv = findViewById(R.id.desc_tv);
        loadTv = findViewById(R.id.load_tv);

        // local refresh userModel
        presenter.getUseLiveData().observe(this, userModel -> {
            if (userModel != null) {
                nameTv.setText("name: " + userModel.name);
                descTv.setText("desc: " + userModel.desc);
                Toast.makeText(this, "name:  " + userModel.name +
                                "\n" + " desc:  " + userModel.desc,
                        Toast.LENGTH_SHORT).show();
            }
        });

        presenter.getLoadLiveData().observe(this, loadStatus -> {
            if (loadStatus == LoadStatus.LOADING) { // loading
                loadTv.setText("local db loading...");

            } else if (loadStatus == LoadStatus.LOADED) { // loaded
                loadTv.setText("local db loaded !");
            }
        });

        presenter.getLocalUser();
    }
}
