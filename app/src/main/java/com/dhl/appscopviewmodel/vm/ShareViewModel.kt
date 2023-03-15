package com.dhl.appscopviewmodel.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * 用于Fragment 共享数据
 */
class ShareViewModel :ViewModel() {

    val data = MutableLiveData<String>()

    var count = 0

    fun getData(){

        Handler(Looper.getMainLooper()).postDelayed({
            count++
            data.value = "from the activity$count"

        },2000)
        data.value = "from the activity$count"
    }
}