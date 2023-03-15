package com.dhl.appscopviewmodel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dhl.appscopviewmodel.ui.BaseActivity
import com.dhl.appscopviewmodel.ui.FragmentActivity
import com.dhl.appscopviewmodel.ui.SecActivity
import com.dhl.appscopviewmodel.utils.LiveDataBus
import com.dhl.appscopviewmodel.vm.AppScope
import com.dhl.appscopviewmodel.vm.EditViewModel
import com.dhl.appscopviewmodel.vm.MainViewModel

class MainActivity : BaseActivity() {


    private val TAG = "MainActivity"

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }


    private val tv: TextView by lazy {
        findViewById(R.id.tv)
    }

    private val btn: Button by lazy {
        findViewById(R.id.btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

        Log.e(TAG, "-----")



    }


}