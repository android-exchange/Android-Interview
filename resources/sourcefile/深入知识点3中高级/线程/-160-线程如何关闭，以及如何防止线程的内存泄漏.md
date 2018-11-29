####  必知：
####  必会：
### 1. 线程终止相关:
#### 取消任务的方式

Java中没有提供任何机制来**安全**地终止线程,但是提供了中断(Interruption)协作机制,能够使一个线程终止另一个线程的当前工作. 一般取消或停止某个任务,很少采用立即停止,因为立即停止会使得共享数据结构出于不一致的状态.这也是`Thread.stop()`,`Thread.suspend()`以及`Thread.resume()`不安全的原因而废弃.

Java中有三种方式可以终止当前运行的线程:

* 设置某个＂已请求取消（`Cancellation Requested`)＂标记,而任务将定期查看该标记的协作机制来中断线程.
* 使用`Thread.stop()`强制终止线程,但是因为这个方法"解锁"导致共享数据结构处于不一致而不安全被废弃.
* 使用`Interruption`中断机制.



#### 使用中断标记来中断线程.

```java

	public class PrimeGenerator implements Runnable {
	    private static ExecutorService exec = Executors.newCachedThreadPool();
	
	    @GuardedBy("this") private final List<BigInteger> primes
	            = new ArrayList<BigInteger>();
	    private volatile boolean cancelled;
	
	    public void run() {
	        BigInteger p = BigInteger.ONE;
	        while (!cancelled) {
	            p = p.nextProbablePrime();
	            synchronized (this) {
	                primes.add(p);
	            }
	        }
	    }
	
	    public void cancel() {
	        cancelled = true;
	    }
	
	    public synchronized List<BigInteger> get() {
	        return new ArrayList<BigInteger>(primes);
	    }
	
	    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
	        PrimeGenerator generator = new PrimeGenerator();
	        exec.execute(generator);
	        try {
	            SECONDS.sleep(1);
	        } finally {
	            generator.cancel();
	        }
	        return generator.get();
	    }
	}

```

设置标记的中断策略: `PrimeGenerator`使用一种简单取消策略,客户端代码通过调研`cancel`来请求取消,`PrimeGenerator`在每次搜索素数时前先检查是否存在取消请求,如果不存在就退出.

但是使用设置标记的中断策略有一问题: 

**如果任务调用调用阻塞的方法,比如`BlockingQueue.put`,那么可能任务永远不会检查取消标记而不会结束.**

```java

	class BrokenPrimeProducer extends Thread {
	    private final BlockingQueue<BigInteger> queue;
	    private volatile boolean cancelled = false;
	
	    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
	        this.queue = queue;
	    }
	
	    public void run() {
	        try {
	            BigInteger p = BigInteger.ONE;
	            while (!cancelled)
					//此处阻塞,可能永远无法检测到结束的标记
	                queue.put(p = p.nextProbablePrime());
	        } catch (InterruptedException consumed) {
	        }
	    }
	
	    public void cancel() {
	        cancelled = true;
	    }
	}

```

解决办法也很简单: **使用中断而不是使用boolean标记来请求取消**

#### 使用中断(Interruption)请求取消

* Thread类中的中断方法:
 	* `public void interrupt()` 
 	
		请求中断,设置中断标记,而并不是真正中断一个正在运行的线程,只是发出了一个请求中断的请求,由线程在合适的时候中断自己.
	* `public static native boolean interrupted()`;

		判断线程是否中断,会擦除中断标记(判断的是当前运行的线程),另外若调用Thread.interrupted()返回为true时,必须要处理,可以抛出中断异常或者再次调用interrupt()来恢复中断. 
	* `public native boolean isInterrupted()`;
	
		判断线程是否中断,不会擦除中断标记

故而上面问题的解决方案如下:

```java

	public class PrimeProducer extends Thread {
	    private final BlockingQueue<BigInteger> queue;
	
	    PrimeProducer(BlockingQueue<BigInteger> queue) {
	        this.queue = queue;
	    }
	
	    public void run() {
	        try {
	            BigInteger p = BigInteger.ONE;
	            while (!Thread.currentThread().isInterrupted())
	                queue.put(p = p.nextProbablePrime());
	        } catch (InterruptedException consumed) {
	            /* Allow thread to exit */
	        }
	    }
	
	    public void cancel() {
	        interrupt();
	    }
	}

```

* 那么`thread.interrupt()`调用后到底意味着什么? 
	
	首先一个线程不应该由其他线程来强制中断或者停止,而应该由线程自己停止,所以`Thread.stop()`,`Thread.suspend()`,`Thread.resume()`都已被废弃.而`{@link Thread#interrupt}`作用其实不是中断线程,而请求线程中断.具体来说,当调用`interrupt()`方法时:
	
	* 如果线程处于阻塞状态时(例如处于`sleep`,`wait`,`join`等状态时)那么线程将立即退出阻塞状态而抛出`InterruptedException`异常.
	* 如果 线程处于正常活动状态,那么会将线程的中断标记设置为true,仅此而已.被设置中断标记的线程将继续运行而不受影响.
	
	`interrupt()`并不能真正的中断线程,需要被调用的线程自己进行配合才行:

	* 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
 	* 在调用阻塞方法时正确处理`InterruptedException`异常。
 	* 
 	
###2. 线程的内存泄露
