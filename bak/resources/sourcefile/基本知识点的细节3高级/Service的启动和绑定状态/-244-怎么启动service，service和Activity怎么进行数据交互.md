### 1.通过startService启动
使用Service的步骤：
> 1. 定义一个类继承Service
> 2. 在Manifest.xml文件中配置该Service
> 3. 使用Context的startService(Intent)方法启动该Service
> 4. 不再使用时，调用stopService(Intent)方法停止该服务

生命周期：
> onCreate()--->onStartCommand() ---> onDestory()  
> 当Service已经启动时，不再调用onCreate方法，而会调用onStart和onStartCommand方法   
> 生命周期与Activity不同，Activity结束后Service并不跟随结束

### 2.通过bindService启动
使用Service的步骤：
> 1. 定义一个类继承Service
> 2. 在Manifest.xml文件中配置该Service
> 3. 使用Context的bindService(Intent, ServiceConnection, int)方法启动该Service
> 4. 不再使用时，调用unbindService(ServiceConnection)方法停止该服务

生命周期：
> onCreate() --->onBind()--->onunbind()--->onDestory()    
> 生命周期与Activity相同，Activity结束后Service跟随结束

## Activity与Service的通信方式

1. 不论是start还是bind，都可以在开始时通过intent携带一定量数据
2. 通过Broadcast，Activity发送广播，Service接收；或者Service发送广播，Activity接收
3. 通过bind方式启动时，可以通过实现ServiceConnection来完成Activity和Service的交互

## 参考资料
[Android 服务两种启动方式的区别](https://www.jianshu.com/p/2fb6eb14fdec)

[Android Activity与Service的通信方式](https://www.cnblogs.com/aademeng/articles/6542189.html)