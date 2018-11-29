#### 1. Android主线程中Looper死循环为什么不会造成ANR?

首先: `ANR`(卡死)是指不响应消息, 而死循环其实也是在处理消息.

造成`ANR`的原因一般有两种:

- 当前事件没得到处理(即主线程正在处理其他事件或者Looper由于某种原因阻塞了).
- 当前事件正在处理,但没有及时完成.

```java
class ActivityThread{
  //...
  public static void main(String[] args) {
        //...
    	//创建Looper和MessageQueue用于处理主线程的消息
        Looper.prepareMainLooper();

        ActivityThread thread = new ActivityThread();
    	//建立Binder通道(创建新线程)
        thread.attach(false);
        if (sMainThreadHandler == null) {
            sMainThreadHandler = thread.getHandler();
        }
        // End of event ActivityThreadMain.
        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
    	//开始Looper循环
        Looper.loop();
		//如果Looper循环结束,说明应用崩溃或者退出了.
        throw new RuntimeException("Main thread loop unexpectedly exited");
    }
}
```

```java
public class Looper{
   public static void loop() {
        final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        final MessageQueue queue = me.mQueue;
		//...
        for (;;) {  //死循环
            Message msg = queue.next(); // might block
            if (msg == null) {
                // No message indicates that the message queue is quitting.
              	//调用loop.quit()会导致循环退出
                return;
            }
          	//处理消息
            msg.target.dispatchMessage(msg);
			//...
            msg.recycleUnchecked();
        }
    }
}
```

显然, **如果`ActivityThread`的`main()`中没有`looper`进行死循环,那么主线程一运行完毕就会退出.**

那么为什么这消息循环不会造成`ANR`异常呢? 

> 因为`Android`是由事件驱动的,`Looper.loop()`不断接受事件,处理事件,每一个点击触摸或者`Activity`的生命周期都运行在`Looper.loop()`的循环中,如果`Looper.loop()`结束,那么应用也就退出了. **只能是某个消息处理事件阻塞了Looper.loop(),而不可能是Looper.loop()阻塞了它.**

对于线程即是一段可执行的代码, 当可执行的代码执行完毕后, 线程生命周期便结束了,线程退出.而对于主线程, 运行一段时间退出是绝对不行, 那么如何保证一直存活? 

> 简单的做法就是可执行的代码是能一直执行下去, 死循环便能保证不会退出. 例如,`Bind`线程池就是采用死循环的方法,通过循环方式不同与`Binder`驱动进行读写操作, 当然并**非简单的死循环而是无消息时会休眠.**但是既然是死循环又如何处理其他事务, **通过创建新线程的方法**. 真正卡死主线程的操作是在回到方法`onCreate/onStart/onResume`等操作时间过长,回导致掉帧,甚至发生`ANR`.

那么如果`Looper.loop()`死循环不会导致应用卡死, 会不会导致消耗的资源越来越多呢? 

> 其实不然, 这里涉及到`linux/pipe/epoll`机制, 简单说在主线程中`MessageQueue`没有消息时,便阻塞在`loop的queue.next()`中的`nativePollOnce()`方法里, 此时线程会释放`CPU`资源进入休眠状态,直到下一个消息到达或者事务发生,通过往`pipe管道`写入数据来唤醒主线程工作. 这里采用`epoll机制`,是一种`IO多路复用机制`, 可同时监控多个描述符,当某个描述符(读写)就绪, 则立即通知程序进行读写操作,本质同步`IO`. 所以说,主线程大多时候都处于休眠状态,并不会消耗大量的`cpu`资源.

```java
 private class H extends Handler {
   public void handleMessage(Message msg) {
            switch (msg.what) {
                case LAUNCH_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
                    final ActivityClientRecord r = (ActivityClientRecord) msg.obj;

                    r.packageInfo = getPackageInfoNoCheck(
                            r.activityInfo.applicationInfo, r.compatInfo);
                    handleLaunchActivity(r, null);
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
                case RELAUNCH_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityRestart");
                    ActivityClientRecord r = (ActivityClientRecord)msg.obj;
                    handleRelaunchActivity(r);
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
                case PAUSE_ACTIVITY:
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityPause");
                    handlePauseActivity((IBinder)msg.obj, false, (msg.arg1&1) != 0, msg.arg2,
                            (msg.arg1&2) != 0);
                    maybeSnapshot();
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                    break;
                //.....
            }
   }
}
```

**Activity的生命周期都是依靠主线程的Looper.loop()**,当收到不同`Meessage`时采用相应的措施(如上源码).

> 在`ActivityThread类中main()`有`thread.attach(false)`方法中会创建一个`Binder`线程(具体是`ApplicationThread`,`Binder服务端`,用于接收系统服务AMS发送来的事件),该`Binder`线程通过`Handler`将`Message`发送给主线程, 通过`handleMeessage()`方法处理消息(如上源码).

**主线程的消息又是哪来的呢？**当然是App进程中的其他线程通过Handler发送给主线:

从进程与线程间通信的角度加深对`APP`运行过程的理解:

![](https://pic1.zhimg.com/80/7fb8728164975ac86a2b0b886de2b872_hd.jpg)

**system_server进程**

> system_server进程是系统进程，java framework框架的核心载体，里面运行了大量的系统服务，比如这里提供`ApplicationThreadProxy`（简称ATP），`ActivityManagerService`（简称AMS），这个两个服务都运行在system_server进程的不同线程中，由于ATP和AMS都是基于IBinder接口，都是binder线程，binder线程的创建与销毁都是由binder驱动来决定的。

**App进程**

> App进程则是我们常说的应用程序，主线程主要负责Activity/Service等组件的生命周期以及UI相关操作都运行在这个线程； 另外，每个App进程中至少会有两个binder线程 `ApplicationThread`(简称AT)和`ActivityManagerProxy`（简称AMP），除了图中画的线程，其中还有很多线程

**Binder**

> Binder用于不同进程之间通信，由一个进程的Binder客户端向另一个进程的服务端发送事务，比如图中线程2向线程4发送事务；而handler用于同一个进程中不同线程的通信，比如图中线程4向主线程发送消息

结合图说说`Activity`生命周期，比如暂停`Activity`，流程如下：

1. 线程1的`AMS`中调用线程2的`ATP`；（由于同一个进程的线程间资源共享，可以相互直接调用，但需要注意多线程并发问题）
2. 线程2通过`binder`传输到`App`进程的线程4；
3. 线程4通过`handler`消息机制，将暂停`Activity`的消息发送给主线程；
4. 主线程在`looper.loop()`中循环遍历消息，当收到暂停`Activity`的消息时，便将消息分发给`ActivityThread.H.handleMessage()`方法，再经过方法的调用，最后便会调用到`Activity.onPause()`，当`onPause()`处理完后，继续循环loop下去。

`ActivityThread`通过`ApplicationThread`和AMS进行进程间通讯，AMS以进程间通信的方式完成`ActivityThread`的请求后会回调`ApplicationThread中`的Binder方法，然后`ApplicationThread`会向H发送消息，H收到消息后会将`ApplicationThread`中的逻辑切换到`ActivityThread`中去执行，即切换到主线程中去执行，这个过程就是。主线程的消息循环模型

参考资料:

[Android 消息机制——你真的了解Handler？](http://blog.csdn.net/qian520ao/article/details/78262289?locationNum=2&fps=1#4-handler-是如何能够线程切换)

[主线程中的Looper.loop()一直无限循环为什么不会造成ANR？](http://blog.csdn.net/ksjay_1943/article/details/54893773)

[Android中为什么主线程不会因为Looper.loop()里的死循环卡死？](https://www.zhihu.com/question/34652589)



