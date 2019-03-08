####  必知：
* 在主线程使用`Hander`发送消息无需创建`Looper`对象, 因为在`App`启动时,即`ActivityTread`的`main()`方法中已经初始化了`Looper`对象了, 那么在子线程中发送消息,则需要我们自己初始化`Looper`对象;

* `Looper`是可以退出的, 通过`quit`或`quitSafely()`方法可以退出`Looper`循环.二者的区别: `quit`直接退出, 而`quitSafely`则是设定一个退出标记, 然后把消息队列中已有的消息处理完后会退出. 如果手动创建`Looper`, 我们需要在所有事件都完成后调用`quit`方法来终止消息循环.否则线程会一直处于等待状态, 而如果退出后,线程会立即终止.建议不需要的时候终止`Looper`.
####  必会：

使用`Handler`发送消息给子线程, `Looper`怎么启动? 很简单, 手动初始化`Looper`既可(可以参考`HandlerThread`的源码),如:

```java
        new Thread("#LooperThread"){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Handler handler= new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
                handler.sendEmptyMessage(0);
            }
        }.start();

```