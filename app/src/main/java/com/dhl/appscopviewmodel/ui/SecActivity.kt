package com.dhl.appscopviewmodel.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dhl.appscopviewmodel.R
import com.dhl.appscopviewmodel.vm.AppScope
import com.dhl.appscopviewmodel.vm.EditViewModel

/**
 * Test activity 通信
 */

private const val TAG = "SecActivity"
class SecActivity : BaseActivity() {


    private val tv:TextView by lazy{
        findViewById<TextView>(R.id.tv)
    }

    private val editViewModel: EditViewModel by lazy {
        AppScope.getAppScopeViewModel(EditViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)
        editViewModel.inputData.observe(this, Observer {
            tv.text = it
            Log.e(TAG,"it = $it")
        })
        findViewById<Button>(R.id.btn).setOnClickListener {
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }

    }
}