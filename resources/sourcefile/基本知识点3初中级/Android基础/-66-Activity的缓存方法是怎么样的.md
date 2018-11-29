####  必知：
####  必会：
`Activity`的 `nSaveInstanceState()` 和 `onRestoreInstanceState()`并不是生命周期方法，它们不同于 `onCreate()`、`onPause()`等生命周期方法，它们并不一定会被触发。当应用遇到意外情况（如：内存不足、用户直接按Home键）由系统销毁一个`Activity`，`onSaveInstanceState()` 会被调用。但是当用户主动去销毁一个Activity时，例如在应用中按返回键，`onSaveInstanceState()`就不会被调用。除非该activity是被用户主动销毁的，通常onSaveInstanceState()只适合用于保存一些临时性的状态，而onPause()适合用于数据的持久化保存。


异常情况的出现分为两种可能：

（1）资源相关的系统配置发生改变导致`Activity`被杀死并重新创建

这就是我们所说的横竖屏幕切换的情况，在横竖屏切换时由于系统配置发生了改变，Activity会被销毁，其`onPause`,`onStop`,`onDestroy`均被调用，同时`onSaveInstanceState`会被调用，它的调用时机发生在`onStop`之前，和`onPause`没有时间上的先后关系。

（2）内存不足导致低优先级的Activity被杀死

这种情况`onSaveInstanceState`会起作用，但是调用的时机不是被杀死时，因为被杀死时怎么会有机会调用呢？注意点2介绍了这个情况。



有两点需要注意：

（1）使用`onRestoreInstanceState`来进行之前状态的获取优于`onCreate`，因为`onRestoreInstanceState`只要被调用一定说明是有值的（之前已经调用过onSaveInstanceState），但是`onCreate`无论是否有值一定会调用，所以没有使用`onRestoreInstanceState`好。

（2）系统正常销毁时，`onSaveInstanceState`不会被调用，如用户按back键。但是如果Activity有机会重新显示出来，那么`onSaveInstanceState`一定会调用，因为系统也不知道它是否一定会被杀死。系统不知道你按下HOME后要运行多少其他的程序，自然也不知道`activity` A是否会被销毁，因此系统都会调用`onSaveInstanceState(`)，让用户有机会保存某些非永久性的数据。以下几种情况的分析都遵循该原则

* 当用户按下HOME键时
* 长按HOME键，选择运行其他的程序时
* 锁屏时
* 从activity A中启动一个新的activity时
* 屏幕方向切换时

