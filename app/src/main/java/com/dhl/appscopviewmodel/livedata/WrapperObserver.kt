package com.dhl.appscopviewmodel.livedata

import android.util.Log
import androidx.lifecycle.Observer

/**
 * Observer 包装类
 * 通过改变mLastVersion 的值 就能做到非粘性事件
 *
 */
class WrapperObserver<T>(
    var liveData: NoStickyLiveData<T>,
    var observer: Observer<in T>,
    sticky: Boolean
) : Observer<T> {

    private val TAG = "WrapperObserver"

    //标记该liveData已经发射几次数据了，用以过滤老数据重复接收
    private var mLastVersion = if (sticky) {
        -1
    } else {
        liveData.getVersion()
    }


    override fun onChanged(t: T) {

        if (mLastVersion >= liveData.getVersion()) {
            return
        }
        mLastVersion = liveData.getVersion()
        observer?.onChanged(t)
    }



}