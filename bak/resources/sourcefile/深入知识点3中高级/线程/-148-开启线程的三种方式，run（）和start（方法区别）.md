####  必知：
####  必会：

### Java有哪几种创建新线程的方法及区别 

#### 一、继承Thread类创建线程类

* （1）定义Thread类的子类，并重写该类的run方法，该run方法的方法体就代表了线程要完成的任务。因此把run()方法称为执行体。
* （2）创建Thread子类的实例，即创建了线程对象。
* （3）调用线程对象的start()方法来启动该线程。

 ```java
public class FirstThreadTest extends Thread {
    private int i = 0;
    //重写run方法，run方法的方法体就是现场执行体
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  : " + i);
            if (i == 20) {
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}
 ```

上述代码中`Thread.currentThread()`方法返回当前正在执行的线程对象。`getName()`方法返回调用该方法的线程的名字。

#### 二、通过Runnable接口创建线程类

* 定义runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的线程执行体。
* 创建 Runnable实现类的实例，并依此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象。
* 调用线程对象的start()方法来启动该线程。

```java
public class RunnableThreadTest implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                RunnableThreadTest rtt = new RunnableThreadTest();
                new Thread(rtt, "新线程1").start();
                new Thread(rtt, "新线程2").start();
            }
        }
    }
}
```

 #### 三、通过Callable和Future创建线程

* 创建`Callable`接口的实现类，并实现`call()`方法，该`call()`方法将作为线程执行体，并且有返回值。
* 创建`Callable`实现类的实例，使用`FutureTask`类来包装`Callable`对象，该`FutureTask`对象封装了该`Callable`对象的`call()`方法的返回值。
* 使用`FutureTask`对象作为`Thread`对象的`target`创建并启动新线程。
* 调用`FutureTask`对象的`get()`方法来获得子线程执行结束后的返回值

```java
public class CallableThreadTest implements Callable<Integer> {
    public static void main(String[] args) {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if (i == 20) {
                new Thread(ft, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
```

#### 创建线程的三种方式的比较

* 采用实现`Runnable`、`Callable`接口的方式创见多线程时:
  * 优势是：线程类只是实现了`Runnable`接口或`Callable`接口，还可以继承其他类。在这种方式下，多个线程可以共享同一个target对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以将CPU、代码和数据分开，形成清晰的模型，较好地体现了面向对象的思想。
  * 劣势是：编程稍微复杂，如果要访问当前线程，则必须使用`Thread.currentThread()`方法。
* 使用继承`Thread`类的方式创建多线程时:
  * 优势是：编写简单，如果需要访问当前线程，则无需使用`Thread.currentThread()`方法，直接使用`this`即可获得当前线程。
  * 劣势是：线程类已经继承了`Thread`类，所以不能再继承其他父类。
  
  
### `run()`和`start()`方法区别?
* `start()`方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码：通过调用Thread类的start()方法来启动一个线程， 这时此线程是处于就绪状态，并没有运行。然后通过此Thread类调用方法run()来完成其运行操作的，这里方法run()称为线程体，它包含了要执行的个线程的内容， Run方法运行结束，此线程终止，而CPU再运行其它线程。

* `run()`方法当作普通方法的方式调用，程序还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码：而如果直接用Run方法，这只是调用一个方法而已程序中依然只有主线程--这一个线程，其程序执行路径还是只有一条，这样就没有达到写线程的目的。 
* 


## 【补充】Android中特有的启动线程的方式
1. [AsyncTask](https://www.jianshu.com/p/3b839d7a3fcf) ： Android中轻量级的异步类，不过不同版本的Android实现机制不一样，使用时需要注意
2. [IntentService](http://blog.csdn.net/matrix_xu/article/details/7974393) ：继承自Service，拥有Service的全部特性，同时又能在新开的线程中执行
3. [HandlerThread](https://www.jianshu.com/p/4a57de01c8f5)：本质是Handler和Thread的结合，创建新线程并通过Handler进行线程间通信



