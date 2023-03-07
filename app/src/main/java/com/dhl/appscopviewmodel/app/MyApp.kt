package com.dhl.appscopviewmodel.app

import android.app.Application
import android.os.Process
import android.util.Log
import androidx.lifecycle.*
import com.dhl.appscopviewmodel.vm.AppScope


/**
 * 全局的ViewModel
 * 首先 要对Application 实现 ViewModelStoreOwner接口
 */
class MyApp : Application(), ViewModelStoreOwner {


    private val TAG = "MyApp"


    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }


    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate -->" + Process.myPid())
        AppScope.init(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onForeground(){
                Log.e(TAG,"onForeground")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onDestroy() {
                Log.e(TAG,"ON_PAUSE")
            }
        })

    }

    override fun onTerminate() {
        super.onTerminate()
        appViewModelStore.clear()
    }

    override fun getViewModelStore(): ViewModelStore {

        return appViewModelStore
    }


}