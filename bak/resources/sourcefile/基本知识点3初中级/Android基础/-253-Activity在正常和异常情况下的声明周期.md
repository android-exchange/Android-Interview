###  一：典型情况下的生命周期分析

Android系统根据生命周期的不同阶段唤起对应的回调函数来执行代码。系统存在启动与销毁一个activity的一套有序的回调函数。下面就简要分析一下各个过程的回调函数。

* `onCreate`方法，Activity被启动时的第一个方法，表示Activity正在被创建，在这个方法中一般做一些初始化的工作，比如调用`setContentView`方法加载界面布局，并且可以做一些初始化数据的工作：声明UI元素，定义成员变量，配置UI等。 

  > `onCreate`尽量少做一些不必要的操作，避免Activity启动太久，使用户半天看不到Activity

* `onStart`方法，表示Activity正在被启动，其实这时Activity已经可见了，正如上图所表示的Visible,但并不能被用户所见，我们可以这样理解，Activity现在运行到了后台，还没有准备好到前台和用户交互。

* onResume方法，此时Activity来到前台和用户进行交互，用户真正的看到了Activity. 

  > 往往程序在`onCreate`执行完成之后会迅速调用`onStart`方法和`onResume`方法.

* `onPause`方法,表示Activity正在停止，该状态下，activity的部分被另外一个activity所遮盖：另外的activity来到前台，但是半透明的，不会覆盖整个屏幕。被暂停的activity不再接受用户的输入且不再执行任何代码。

* `onStop`方法，该状态下, activity完全被隐藏，对用户不可见。可以认为是在后台。当stopped, activity实例与它的所有状态信息（如成员变量等）都会被保留，但activity不能执行任何代码。 

  > 整个生命周期方法中，只有`onResume`,`onPause`,`onStop`方法是“静态”的，既可以存在较长的时间的。其它状态 (Created与Started)都是短暂的，系统快速的执行那些回调函数并通过执行下一阶段的回调函数移动到下一个状态。也就是说，在系统调用`onCreate()`, 之后会迅速调用`onStart()`, 之后再迅速执行`onResume()`

* `onRestart`方法，Activity从不可见又变为可见时会调用。此时调用顺序为:`onRestart`-> `onStart `-> `onResume`

* `onDestroy`方法:表示Activity即将被销毁，当收到需要将该activity彻底移除的信号时，系统会调用这个方法。 

> 大多数 `app`并不需要实现这个方法，因为局部类的references会随着activity的销毁而销毁，并且我们的activity应该在`onPause()`与`onStop()`中执行清除activity资源的操作。然而，如果activity含有在`onCreate`调用时创建的后台线程，或者是其他有可能导致内存泄漏的资源，则应该在`onDestroy()`时进行资源清理，杀死后台线程。

> 除非程序在`onCreate()`方法里面就调用了finish()方法，系统通常是在执行了`onPause()`与`onStop()` 之后再调用`onDestroy()` 。在某些情况下，例如我们的activity只是做了一个临时的逻辑跳转的功能，它只是用来决定跳转到哪一个activity，这样的话，需要在`onCreate`里面调用finish方法，这样系统会直接调用`onDestory`，跳过生命周期中的其他方法。

> 至于为什么当我们的activity只是做了一个临时的逻辑跳转的功能，决定跳转到哪一个activity，需要在`onCreate`里面调用finish方法，我的理解是，因为当从Activity a跳转到Activity b时，a的`onPause`方法执行过后，b的`onResume`方法才会执行，如果不在a的`onCreate`方法中finish(),启动b可能会很费时，所以我们宁可在`onStop`做一些操作也不要在`onPause`中做，并且完全没必要不finish().,并且此时注意在a的`onDestroy`做资源回收工作，避免造成内存泄露.

## 二：异常情况下的生命周期分析

##### (1)情况一：资源相关的系统配置发生改变导致Activity被杀死并重新创建 

当系统配置发生改变后，如从横屏手机切换到了竖屏，Activity会被销毁，其`onPause`,`onStop`,`onDestroy`方法均会被调用，同时由于是在异常情况下被终止的，系统会调用`onSavedInstanceState`来保存当前Activity的状态(正常情况下不会调用此方法)，这个方法的调用时机是在`onStop`之前，当Activity重新被创建后，系统调用会调用

```JAVA
  	@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
```

方法，并且把Activity销毁时`onSavedInstanceState`方法所保存的Bundle对象作为参数同时传递给`onRestoreInstanceState`和`onCreate`方法，`onRestoreInstanceState`是在`onStart`之后调用。 

> 我们知道，当Activity被异常终止后被恢复时，系统会自动的帮我们恢复数据和一些状态，如文本框用户输入的数据，`Listview`的滚动位置等，关于保存和恢复View的层次结构和数据，系统的工作流程是这样的，首先Activity被意外终止时，Activity会调用`onSavedInstanceState`去保存数据，然后Activity会委托Window去保存数据，接着Window再委托它上面的顶级容器去保存数据，顶层容器是一个`ViewGroup`，一般来说它很可能是一个`DecorView`.最后顶层容器再去一一通知它的子元素来保存数据，这样整个数据保存过程就完成了，可以发现，这是一种典型的委托思想，上层委托下层，父容器委托子容器去处理一些事情。

##### (2)情况二：资源内存不足导致低优先级的Activity被杀死 

Activity按照优先级我们可以分为以下的三种： 

* a.前台Activity—正在和用户交互的Activity，优先级最高。 
* b.可见但非前台Activity,如处于`onPause`状态的Activity，Activity中弹出了一个对话框，导致Activity可见但是位于后台无法和用户直接交互。 
* c.后台Activity—已经被暂停的Activity,比如执行了`onStop`方法，优先级最低。 

当系统内存不足时，系统就会按照上述的优先级顺序选择杀死Activity所在的进程，并在后续通过`onSaveInstanceState`缓存数据和`onRestoreInstanceState`恢复数据。 

> 如果一个进程中没有四大组件在执行，那么这个进程将很快被杀死，因此，一些后台工作不适合脱离了四大组件工作，比较好的方法是将后台工作放入Service中从而保证进程有一定的优先级，这样就不会轻易的被系统杀死。系统只恢复那些被开发者指定过id的控件，如果没有为控件指定id,则系统就无法恢复了.