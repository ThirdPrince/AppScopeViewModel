package com.dhl.appscopviewmodel.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhl.appscopviewmodel.livedata.NoStickyLiveData

/**
 *
 */
class EditViewModel:ViewModel() {


    private  val TAG = "ClearViewModel"



    val  inputData = MutableLiveData<String>()
    val  inputDataNoSticky = NoStickyLiveData<String>()


    fun input(input:String):LiveData<String>{
        inputData.value = input
        return inputData
    }

    fun inputWithNoSticky(input:String):LiveData<String>{
        inputDataNoSticky.setValue(input)
        return inputDataNoSticky
    }


}