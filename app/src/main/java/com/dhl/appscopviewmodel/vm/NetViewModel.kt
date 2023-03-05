package com.dhl.appscopviewmodel.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.NetworkUtils

/**
 *
 */
class NetViewModel : ViewModel() {


    private val TAG = "NetViewModel"

    //val  isAvailable = MutableLiveData<Boolean>()

    val liveDataStr = MutableLiveData<String>()

    fun hasNetWork(): LiveData<Boolean> {
        val isAvailable = MutableLiveData<Boolean>()
        NetworkUtils.registerNetworkStatusChangedListener(object :
            NetworkUtils.OnNetworkStatusChangedListener {
            override fun onDisconnected() {
                isAvailable.value = false
            }

            override fun onConnected(networkType: NetworkUtils.NetworkType?) {
                isAvailable.value = true
            }

        })
        return isAvailable
    }


    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared")
    }
}