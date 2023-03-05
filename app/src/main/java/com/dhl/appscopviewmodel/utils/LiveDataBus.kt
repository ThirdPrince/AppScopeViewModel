package com.dhl.appscopviewmodel.utils

import androidx.lifecycle.*
import com.dhl.appscopviewmodel.livedata.NoStickyLiveData
import java.util.concurrent.ConcurrentHashMap

/**
 * 消息总线
 * 跨 activity
 */
object LiveDataBus {


    private val mHashMap = ConcurrentHashMap<String, NoStickyLiveData<*>>()

    /**
     * 不带粘性事件
     */
    fun <T> with(eventName: String): NoStickyLiveData<T> {
        var liveData = mHashMap[eventName]
        if (liveData == null) {
            liveData =
                NoStickyLiveData(
                    eventName,
                    mHashMap as ConcurrentHashMap<String, NoStickyLiveData<T>>
                )
            mHashMap[eventName] = liveData
        }
        return liveData as NoStickyLiveData<T>
    }

    /**
     * 带粘性事件的
     */

    fun <T> withSticky(eventName: String): NoStickyLiveData<T> {
        var liveData = mHashMap[eventName]
        if (liveData == null) {
            liveData =
                NoStickyLiveData(
                    eventName,
                    mHashMap as ConcurrentHashMap<String, NoStickyLiveData<T>>,
                    true
                )
            mHashMap[eventName] = liveData
        }
        return liveData as NoStickyLiveData<T>
    }


}