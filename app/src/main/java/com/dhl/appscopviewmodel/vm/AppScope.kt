package com.dhl.appscopviewmodel.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhl.appscopviewmodel.app.MyApp

/**
 * 构建全局 appScopeViewModel
 * 可用于activity 通信
 */
object AppScope {
    private lateinit var myApp: MyApp
    fun init(application: MyApp){
        myApp = application
    }

    fun <T : ViewModel?> getAppScopeViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(myApp).get(modelClass)
    }
}