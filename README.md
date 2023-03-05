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



## 定制事件总线

