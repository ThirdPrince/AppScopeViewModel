## AppScopeViewModel

ViewModel 跨activity 使用方法 Application 实现 ViewModelStoreOwner 接口,返回 ViewModelStore 对象

```
   // application
   override fun getViewModelStore(): ViewModelStore {

        return appViewModelStore
    }
    // 通过 getAppScopViewModel 生成的ViewModel 可以跨Activity 
     fun <T : ViewModel?> getAppScopeViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(myApp).get(modelClass)
    }
    
    
```

## LiveDate 处理粘性事件

LiveData 天生支持粘性事件,google 设计LiveData 并不是 为了粘性而设计，但却有粘性的效果。 从 源码的角度看LiveData支持粘性事件的原因是 observer
version 与 LiveData 的version没有保持一致性，Observer 每次的初始值为-1，这样因为

```
     if (observer.mLastVersion >= mVersion) {
            return;
        }
        observer.mLastVersion = mVersion;
        observer.mObserver.onChanged((T) mData);
       
```  

这样会导致粘性事件。只要让observer的 version 与 LiveData的version 保持一致，就能取消粘性事件。

## 定制事件总线

利用 LiveData 强大的事件分发能力，自定义事件总线，使用方式：

```

  LiveDataBus.with<String>("edit").observe(this, Observer {
         
            Log.e(TAG,"it = $it")
        })
  LiveDataBus.withSticky<String>("edit").setValue("1234")

```