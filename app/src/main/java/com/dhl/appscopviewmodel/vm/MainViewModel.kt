package com.dhl.appscopviewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhl.appscopviewmodel.model.User

class MainViewModel :ViewModel() {

    private val  userLiveData = MutableLiveData<User>()

    fun getUser():LiveData<User>{
        userLiveData.value = User("charlie")
        return userLiveData
    }
}