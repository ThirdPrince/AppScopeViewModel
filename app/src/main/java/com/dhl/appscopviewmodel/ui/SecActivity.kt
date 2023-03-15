package com.dhl.appscopviewmodel.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dhl.appscopviewmodel.R
import com.dhl.appscopviewmodel.utils.LiveDataBus
import com.dhl.appscopviewmodel.vm.AppScope
import com.dhl.appscopviewmodel.vm.EditViewModel

/**
 * Test activity 通信
 */

private const val TAG = "SecActivity"
class SecActivity : BaseActivity() {


    private var tv:TextView ?= null

    private val editViewModel: EditViewModel by lazy {
        AppScope.getAppScopeViewModel(EditViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)
        findViewById<Button>(R.id.btn).setOnClickListener {
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }

        tv = findViewById(R.id.tv)

//        editViewModel.inputDataNoSticky.observe(this, Observer {
//            it.let {
//                tv?.text = it
//            }
//        })
        LiveDataBus.withSticky<String>("edit").observe(this, Observer {
            it.let {
                tv?.text = it
            }
        })




    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG," onDestroy}")
    }

}