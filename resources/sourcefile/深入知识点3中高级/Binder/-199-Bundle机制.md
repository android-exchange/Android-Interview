####  必知：
* `Bundle`的使用
* `Bundle`传输数据比较小
* `Bundle`底层实现是`ArrayMap`
####  必会：

*  在`Android`中，`Bundle`主要用于传递数据，它是以键值对的形式保存数据。我们经常使用`Bundle`在`Activity`之间传递数据，数据类型可以是基本类型或它们对应的数组，也可以是对象或对象数组。当Bundle传递的是对象或对象数组时，必须实现`Serializable`或`Parcelable`接口。

*  根据Android的设计，同一应用的Activity可能会运行在不同进程中，所以处理数据传递时可能就会出现跨进程的情况。那么如何在进程间传递对象呢？在Android中是通过Binder来实现进程间通信的。Parcel是一个存放读取数据的容器，Android系统中的Binder通信就使用了Parcel类来进行客户端与服务端数据的交互，而且AIDL的数据也是通过Parcel来交互的。在Java空间和`C++`都实现了`Parcel`，由于它在`C/C+`+中，直接使用了内存来读取数据，因此它更有效率。

* `ArrayMap`内部是使用两个数组进行数据存储，一个数组记录`key`的`hash`值，另一个数组记录value值，内部使用二分法对key进行排序，并使用二分法进行添加、删除、查找数据，因此它只适合于小数据量操作，在数据量较大的情况下它的性能将会退化。而`HashMap`内部则是数组+链表的结构，在数据量较少的情况下，`HashMap`的`Entry Array`比`ArrayMap`占用更多的内存。由于使用`Bundle`的场景大多数为小数据量，所以相比之下，使用ArrayMap保存数据在操作速度和内存占用上都具有优势，因此使用Bundle来传递数据，可以保证更快的速度和更少的内存占用。


#### 参考资料
[你不知道的Bunble](http://blog.csdn.net/goodlixueyong/article/details/51187578)

#### 整理人
showdy