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
import com.ruzhan.rxrepository.ui.presenter.RemotePresenter;

/**
 * create ruzhan: 2018/6/21 09:50
 */
public class RemoteActivity extends AppCompatActivity {

    private TextView nameTv;
    private TextView descTv;
    private TextView loadTv;

    private RemotePresenter presenter = new RemotePresenter();

    public static void launch(Context context) {
        Intent intent = new Intent(context, RemoteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        nameTv = findViewById(R.id.name_tv);
        descTv = findViewById(R.id.desc_tv);
        loadTv = findViewById(R.id.load_tv);

        // refresh userModel, update ui
        presenter.getUseLiveData().observe(this, userModel -> {
            if (userModel != null) {
                nameTv.setText(userModel.name);
                descTv.setText(userModel.desc);
                Toast.makeText(this, "name:  " + userModel.name +
                                "\n" + " desc:  " + userModel.desc,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // request network, show loading ui
        presenter.getLoadLiveData().observe(this, loadStatus -> {
            if (loadStatus == LoadStatus.LOADING) { // loading
                loadTv.setText("loading...");

            } else if (loadStatus == LoadStatus.LOADED) { // loaded
                loadTv.setText("loaded !");
            }
        });

        // request network
        presenter.getRemoteUser();
    }
}
