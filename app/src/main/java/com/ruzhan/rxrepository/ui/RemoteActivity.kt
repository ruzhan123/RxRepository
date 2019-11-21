package com.ruzhan.rxrepository.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ruzhan.rxrepository.R
import com.ruzhan.rxrepository.model.LoadStatus
import com.ruzhan.rxrepository.ui.presenter.RemotePresenter
import kotlinx.android.synthetic.main.activity_remote.*


class RemoteActivity : AppCompatActivity() {

    private val presenter = RemotePresenter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)

        // refresh userModel, update ui
        presenter.getUseLiveData().observe(this, Observer { userModel ->
            if (userModel != null) {
                name_tv.text = "name: " + userModel.name
                desc_tv.text = "desc: " + userModel.desc
                Toast.makeText(this, "name:  " + userModel.name +
                        "\n" + " desc:  " + userModel.desc,
                        Toast.LENGTH_SHORT).show()
            }
        })
        // request network, show loading ui
        presenter.getLoadLiveData().observe(this, Observer { loadStatus ->
            if (loadStatus === LoadStatus.LOADING) { // loading
                load_tv.text = "remote loading..."
                local_btn.visibility = View.INVISIBLE

            } else if (loadStatus === LoadStatus.LOADED) { // loaded
                load_tv.text = "remote loaded !"
                local_btn.visibility = View.VISIBLE
            }
        })
        // request network
        presenter.getRemoteUser()
    }

    fun localClick(view: View) {
        LocalActivity.launch(this)
    }

    companion object {

        fun launch(context: Context) {
            val intent = Intent(context, RemoteActivity::class.java)
            context.startActivity(intent)
        }
    }
}
