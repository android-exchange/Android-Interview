####  必知：
####  必会：

生产者消费者问题是多线程的一个经典问题，它描述是有一块缓冲区作为仓库，生产者可以将产品放入仓库，消费者则可以从仓库中取走产品。

解决生产者/消费者问题的方法可分为两类：
* 采用某种机制保护生产者和消费者之间的同步；
* 在生产者和消费者之间建立一个管道。

第一种方式有较高的效率，并且易于实现，代码的可控制性较好，属于常用的模式。第二种管道缓冲区不易控制，被传输数据对象不易于封装等，实用性不强。

在Java中有四种方法支持同步，其中前三个是同步方法，一个是管道方法。

* `wait() / notify()`方法
* `await() / signal()`方法
* `BlockingQueue`阻塞队列方法
* `PipedInputStream / PipedOutputStream`管道流

### wait() / notify()方法

wait() / nofity()方法是基类Object的两个方法：

* `wait()`方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
* `notify()`方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
```java
public class Storage
{
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    // 生产产品
    public void produce(String producer)
    {
        synchronized (list)
        {
            // 如果仓库已满
            while (list.size() == MAX_SIZE)
            {
                System.out.println("仓库已满，【"+producer+"】： 暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 生产产品            
            list.add(new Object());            

            System.out.println("【"+producer+"】：生产了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // 消费产品
    public void consume(String consumer)
    {
        synchronized (list)
        {
            //如果仓库存储量不足
            while (list.size()==0)
            {
                System.out.println("仓库已空，【"+consumer+"】： 暂时不能执行消费任务!");
                try
                {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
            list.remove();
            System.out.println("【"+consumer+"】：消费了一个产品\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    public LinkedList<Object> getList()
    {
        return list;
    }

    public void setList(LinkedList<Object> list)
    {
        this.list = list;
    }

    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }
}
public class Producer extends Thread
{
    private String producer;
    private Storage storage;

    public Producer(Storage storage)
    {
        this.storage = storage;
    }

    @Override
    public void run()
    {
        produce(producer);
    }

    public void produce(String producer)
    {
        storage.produce(producer);
    }

    public String getProducer()
    {
        return producer;
    }

    public void setProducer(String producer)
    {
        this.producer = producer;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }
}

public class Consumer extends Thread
{
    private String consumer;
    private Storage storage;

    public Consumer(Storage storage)
    {
        this.storage = storage;
    }

    @Override
    public void run()
    {
        consume(consumer);
    }

    public void consume(String consumer)
    {
        storage.consume(consumer);
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
```

### await() / signal()方法

`await()`和`signal(`)的功能基本上和 `wait()` / `nofity()`相同，完全可以取代它们，但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。通过在`Lock`对象上调用`newCondition()`方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。

```java
public class Storage {
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();
    // 锁
    private final Lock lock = new ReentrantLock();

    // 仓库满的条件变量
    private final Condition full = lock.newCondition();

    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    // 生产产品
    public void produce(String producer) {
        lock.lock();
        // 如果仓库已满
        while (list.size() == MAX_SIZE) {
            System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
            try {
                // 由于条件不满足，生产阻塞
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 生产产品
        list.add(new Object());

        System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());

        empty.signalAll();

        // 释放锁
        lock.unlock();

    }

    // 消费产品
    public void consume(String consumer) {
        // 获得锁
        lock.lock();

        // 如果仓库存储量不足
        while (list.size() == 0) {
            System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
            try {
                // 由于条件不满足，消费阻塞
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.remove();
        System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());
        full.signalAll();
        
        // 释放锁
        lock.unlock();

    }

    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
```
### BlockingQueue阻塞队列

它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种`await()` / `signal()`方法。它可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法：

* put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。

* take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。


```java

public class Storage {
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);  

    // 生产产品
    public void produce(String producer) {
        // 如果仓库已满
        if (list.size() == MAX_SIZE) {
            System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");            
        }

        // 生产产品
        try {
            list.put(new Object());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());
    }

    // 消费产品
    public void consume(String consumer) {
        // 如果仓库存储量不足
        if (list.size() == 0) {
            System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");            
        }

        try {
            list.take();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());        

    }

    public LinkedBlockingQueue<Object> getList() {
        return list;
    }

    public void setList(LinkedBlockingQueue<Object> list) {
        this.list = list;
    }
    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
```

参考资料

[生产者-消费者Java实现](https://www.cnblogs.com/Ming8006/p/7243858.html)

[Java并行编程-lock中使用多条件condition（生产者消费者模式实例）](http://blog.csdn.net/chenchaofuck1/article/details/51592429)