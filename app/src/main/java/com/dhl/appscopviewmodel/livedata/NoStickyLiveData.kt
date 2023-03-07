package com.dhl.appscopviewmodel.livedata

import android.os.Process
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ProcessLifecycleOwner
import com.dhl.appscopviewmodel.app.MyApp
import java.util.concurrent.ConcurrentHashMap

/**
 * 非粘性事件
 */
class NoStickyLiveData<T>(
    private val eventName: String = "default",
    private val map: ConcurrentHashMap<String, NoStickyLiveData<T>>? = null,
    private val sticky: Boolean = false
) : MutableLiveData<T>() {

    private val TAG = "StickyLiveData"

    private var mVersion = 0

    val mHashMap = ConcurrentHashMap<String, Observer<*>>()

    fun getVersion(): Int {
        return mVersion
    }


    override fun setValue(value: T) {
        mVersion++
        super.setValue(value)

    }

    override fun postValue(value: T) {
        mVersion++
        super.postValue(value)

    }

     override fun observeForever(observer: Observer<in T>) {
         if(!mHashMap.containsKey(eventName)){
            val wrapperObserver =  WrapperObserver(this,observer,sticky,true)
             mHashMap[eventName] = wrapperObserver
             super.observeForever(wrapperObserver)
         }

//         map.let {
//             it?.remove(eventName)
//         }
//        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_START)
//            fun onForeground(){
//                Log.e(TAG,"onForeground")
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//            fun onDestroy() {
//                Log.e(TAG,"ON_STOP")
//                mHashMap.clear()
//
//            }
//        })

    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observerSticky(owner, observer, sticky)
    }

    private fun observerSticky(owner: LifecycleOwner, observer: Observer<in T>, sticky: Boolean) {
        super.observe(owner, WrapperObserver(this, observer, sticky))
        owner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                map.let {
                    it?.remove(eventName)
                }
            }
        })





    }

    private fun observerSticky( observer: Observer<in T>,sticky: Boolean) {
        super.observeForever(WrapperObserver(this, observer,sticky))

    }
}