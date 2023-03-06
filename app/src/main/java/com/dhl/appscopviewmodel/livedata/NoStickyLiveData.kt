package com.dhl.appscopviewmodel.livedata

import androidx.lifecycle.*
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
        super.observeForever(WrapperObserver(this,observer,sticky))
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