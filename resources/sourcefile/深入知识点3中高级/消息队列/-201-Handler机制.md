####  必知：
####  必会：
`Android`消息机制是指`Handler`运行机制,及`Handler`所附带的`MeessageQueue`和`Looper`的工作过程.

消息队列(`MesssageQueue`)主要包含两个操作: `enqueueMessage()`和`next()`. `next()`读写操作本身会伴随着删除操作. 消息队列本质上是一个**单链表的数据结构**, 单链表在插入和删除上效率会比较高.  消息队列主要应用在`native`层(不做讨论).

轮训器(`Looper`)在`Android`消息机制中扮演这消息循环的角色. 具体来说, `Looper`对象不停调用`next()`从`MessageQueue`中取出`Message`, 如果有消息(`MessageQueue`会调用`nativeWake()`唤醒,如果之前是阻塞的话)就会立即处理消息, 而没有消息的话就会阻塞(`MessageQueue`调用`nativePollOnce()`来阻塞,如果之前是非阻塞状态的话.)

`Handler`主要是发送和接收消息过程, `Handler`主要通过`send`系列方法和`post`系列方法来发送消息(`Message`).

从源码的角度来看:

- 构建`Looper`对象

  ```java
  private Looper(boolean quitAllowed) {
    		//创建MessageQueue对象
          mQueue = new MessageQueue(quitAllowed);
          mThread = Thread.currentThread();
  }
  ```

- 构建`MessageQueue`对象

  ```java
   MessageQueue(boolean quitAllowed) {
          mQuitAllowed = quitAllowed;
          mPtr = nativeInit();
   }
  ```

- 构建`Looper`对象后, 调用`looper.prepare()`以及`looper.loop()`

  ```java
  private static void prepare(boolean quitAllowed) {
          if (sThreadLocal.get() != null) {
              throw new RuntimeException("Only one Looper may be created per thread");
          }
  		//将Looper对象保存到当前线程的ThreadLocal对象中,方便后面取出使用
          sThreadLocal.set(new Looper(quitAllowed));
  }
  ```

  ```java
  public static void loop() {
    	//取出Looper对象
      final Looper me = myLooper();
      if (me == null) {
          throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
      }
    	//将先前创建的MessageQueue赋值给局部变量
      final MessageQueue queue = me.mQueue;
  	//...
      for (;;) {
          Message msg = queue.next(); // might block
          if (msg == null) {
              // No message indicates that the message queue is quitting.
              return;
          }
  		//...
        	//处理消息
          msg.target.dispatchMessage(msg);
          //....
          msg.recycleUnchecked();
      }
  }
   public static @Nullable Looper myLooper() {
   		//取出先前保存的looper对象
          return sThreadLocal.get();
   }

  ```

- 构建 `Handler`对象,发送消息并处理消息

  ```java
  //伪代码:
  Handler handler = new Handler(Looper.myLooper()){
     @Override
     public void handleMessage(Message msg) {
       //...
     }
  }
  Message msg = handler.obtainMessage();
  msg.what=1;
  msg.sendToTarget();
  ```

  上面一段伪代码表示在`Android`主线程中发送消息. 我们一步步来解释底层到底做了哪些事:

  - 构建Handler对象

    ```java
      public Handler(Callback callback, boolean async) {
          	//....
            mLooper = Looper.myLooper();
            if (mLooper == null) {
                throw new RuntimeException(
                    "Can't create handler inside thread that has not called Looper.prepare()");
            }
        	//赋值
            mQueue = mLooper.mQueue;
            mCallback = callback;
            mAsynchronous = async;
        }
    ```

    > 初始化`handler`对象, 取出当前线程中`looper`对象, 并将`looper`对象关联的`mLooper.mQueue`对象赋值给变量`mQueue`


- 构建Message对象

  ```java
  public final Message obtainMessage(int what){
     return Message.obtain(this, what);
  }

    public static Message obtain(Handler h, int what) {
          Message m = obtain();
      	//将handler赋值给Message对象中的target
          m.target = h;
          m.what = what;
          return m;
   }
  ```

  > 此步: 构建了一个Message对象, 并将Handler对象赋值给Message对象中target变量.

- 发送消息`sendToTarget()`

  ```java
  	public void sendToTarget() {
          target.sendMessage(this);
      }
   	public final boolean sendMessage(Message msg){
          return sendMessageDelayed(msg, 0);
   	}
  	public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
          MessageQueue queue = mQueue;
          if (queue == null) {
              RuntimeException e = new RuntimeException(
                      this + " sendMessageAtTime() called with no mQueue");
              Log.w("Looper", e.getMessage(), e);
              return false;
          }
          return enqueueMessage(queue, msg, uptimeMillis);
   	}
  	private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
          msg.target = this;
          if (mAsynchronous) {
              msg.setAsynchronous(true);
          }
        	//将msg对象放入到MessageQueue对象中
          return queue.enqueueMessage(msg, uptimeMillis);
      }
  ```

  由于`looper`一直处于死循环中(先前没有消息处于阻塞休眠状态), 当`queue.enqueueMessage()`后, `MessageQueue`调用`nativeWake`唤醒去取出消息:

  ```java
  public static void loop() {
    	//取出Looper对象
      final Looper me = myLooper();
      if (me == null) {
          throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
      }
    	//将先前创建的MessageQueue赋值给局部变量
      final MessageQueue queue = me.mQueue;
  	//...
      for (;;) {
        	//1.取出消息(此时msg不为null)
          Message msg = queue.next(); // might block
          if (msg == null) {
              // No message indicates that the message queue is quitting.
              return;
          }
  		//...
        	//2.处理消息
          msg.target.dispatchMessage(msg);
          //....
          msg.recycleUnchecked();
      }
  }
  ```

  ```java
  boolean enqueueMessage(Message msg, long when) {
      //...
      synchronized (this) {
          //...
          Message p = mMessages;
          boolean needWake;
          if (p == null || when == 0 || when < p.when) {
              // New head, wake up the event queue if blocked.
              msg.next = p;
              mMessages = msg;
            	//在头部插入数据，如果之前MessageQueue是阻塞的，那么现在需要唤醒
              needWake = mBlocked;
          } else {
            	 // 若MessageQueue当前处于阻塞状态，并且新加入的消息为异步消息，并且异步消息的target等于null，那么有可能唤醒MessageQueue
              // 注意有两种方式创建一个消息是异步：
              // 1、创建一个异步的handler，我们常用的用looper构造的Handler是同步的。用异步的handler发送消息，消息将被设置为异步的；
              // 2、创建一个Message，调用Message的setAsynchronous将该Message指定为异步的
              // 考虑到MessageQueue的enqueueMessage函数，要求target必须不等于null，因此这里的needWake应该一直是false
              needWake = mBlocked && p.target == null && msg.isAsynchronous();
              Message prev;
              for (;;) {
                  prev = p;
                  p = p.next;
                  if (p == null || when < p.when) {
                      break;
                  }
                  if (needWake && p.isAsynchronous()) {
                      needWake = false;
                  }
              }
              msg.next = p; // invariant: p == prev.next
              prev.next = msg;
          }
          if (needWake) {
            	//唤醒
              nativeWake(mPtr);
          }
      }
      return true;
  }
  ```

  ```java
  Message next() {
         	//...
          for (;;) {
             	//处理native层的数据，此处会利用epoll进行blocked
              nativePollOnce(ptr, nextPollTimeoutMillis);
              synchronized (this) {
                  final long now = SystemClock.uptimeMillis();
                  Message prevMsg = null;
                  Message msg = mMessages;
                	//下面其实就是找出下一个异步处理类型的消息；
              	//由于enqueueMessage函数的限制，msg.target不会等于null，正常情况下，下面的代码应该不会执行
                  if (msg != null && msg.target == null) {
                      do {
                          prevMsg = msg;
                          msg = msg.next;
                      } while (msg != null && !msg.isAsynchronous());
                  }
                  if (msg != null) {
                      if (now < msg.when) {
                          nextPollTimeoutMillis = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
                      } else {
                          mBlocked = false;
                          if (prevMsg != null) {
                              prevMsg.next = msg.next;
                          } else {
                              mMessages = msg.next;
                          }
                          msg.next = null;
                          if (DEBUG) Log.v(TAG, "Returning message: " + msg);
                          msg.markInUse();
                          return msg;
                      }
                  } else {
                      // No more messages.
                      nextPollTimeoutMillis = -1;
                  }
  			//...
          }
      }
  ```

  > 此步主要是handler将消息发送给`MessageQueue`, `messageQueue`调用`enqueueMessage()`将消息放入队列中, 并唤醒` nativeWake(mPtr);`此时`queue.next()`不阻塞并取出消息`msg`,并调用`msg.target.handleMessage()`处理消息. 此处的`handleMessage()`方法正是我们在初始化handler时重写的方法.

- **总结**: **在主线程中使用消息机制发送消息,`App`启动时会调用`ActivityThread`的`main()`方法初始化`looper`(主要为`looper.prepare()和looper.loop()`)并初始化`MessageQueue`对象, 而当我们初始化`handler`并使用`handler`发送消息时实际上是将`handler`与`msg`绑定并将`msg`插入到`MessageQueue`中,由于`looper`一直处于死循环中, 当有消息过来时会立即唤醒(情况不同可能不需要唤醒), 并调用`messageQueue.next()`将消息取出, 并调用`msg.target.handleMessage()`处理消息. 而在子线程中使用`Handler`发送消息时, 需要自己手动初始化`Looper`对象(包括`looper.prepare()`和`looper.loop()`)**