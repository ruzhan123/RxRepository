package com.ruzhan.rxrepository.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ruzhan.rxrepository.R
import com.ruzhan.rxrepository.model.LoadStatus
import com.ruzhan.rxrepository.ui.presenter.LocalPresenter
import kotlinx.android.synthetic.main.activity_local.*

class LocalActivity : AppCompatActivity() {

    private val presenter = LocalPresenter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)
        // local refresh userModel
        presenter.getUseLiveData().observe(this, Observer { userModel ->
            if (userModel != null) {
                name_tv.text = "name: " + userModel.name
                desc_tv.text = "desc: " + userModel.desc
                Toast.makeText(this, "name:  " + userModel.name +
                        "\n" + " desc:  " + userModel.desc,
                        Toast.LENGTH_SHORT).show()
            }
        })
        presenter.getLoadLiveData().observe(this, Observer { loadStatus ->
            if (loadStatus === LoadStatus.LOADING) { // loading
                load_tv.text = "local db loading..."

            } else if (loadStatus === LoadStatus.LOADED) { // loaded
                load_tv.text = "local db loaded !"
            }
        })
        presenter.getLocalUser()
    }

    companion object {

        fun launch(context: Context) {
            val intent = Intent(context, LocalActivity::class.java)
            context.startActivity(intent)
        }
    }
}
