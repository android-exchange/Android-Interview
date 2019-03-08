# Android

## 概述

## Java

### 基础

- [什么是面向对象（OOP）？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_1)
- [什么是多态？实现多态的机制是什么？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_2)
- [接口（Interface）与抽象类（Abstract Class）的区别？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_3)
- [重写（Override）与重载（Overload）的区别?](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_4)
- 父类的静态方法能否被子类重写？
- 静态属性和静态方法是否可以被继承？是否可以被重写？为什么？
- 什么是内部类？内部类、静态内部类、局部内部类和匿名内部类的区别及作用？
- == 和 equals() 和 hashCode() 的区别？
- Integer 和 int 之间的区别？
- String 转换成 Integer 的方式及原理？
- 自动装箱实现原理？类型转换实现原理？
- 对 String 的了解？
- String 为什么要设计成不可变的？
- [final、finally 和 finalize 的区别？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_14)
- [static 关键字有什么作用？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_15)
- 列举 Java 的集合以及集合之间的继承关系?
- List、Set、Map 的区别？
- [ArrayList、LinkedList 的区别？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_18)
- HashMap，HashTable，ConcurrentHashMap 实现原理以及区别？
- HashSet 与 HashMap 怎么判断集合元素重复？
- String、StringBuffer、StringBuilder 之间的区别？
- [什么是序列化？怎么实现？有哪些方式？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_22)
- 对反射的了解？
- 对注解的了解？
- 对依赖注入的了解？
- 对泛型的了解？
- 泛型中 extends 和 super 的区别？
- 对 Java 的异常体系的了解？
- 对解析与分派的了解？
- 静态代理和动态代理的区别？有什么场景使用？
- 谈谈对 Java 状态机理解？

### 线程与并发

- 线程和进程的区别？
- 开启线程的三种方式
- 如何正确的结束一个Thread?
- Thread 与 Runnable 的区别？
- run() 与 start() 方法的区别？
- sleep() 与 wait() 方法的区别？
- wait 与 notify 关键字的区别？
- synchronized 关键字的用法、作用及实现原理？
- volatile 关键字的用法、作用及实现原理？
- transient 关键字的用法、作用及实现原理？
- ReentrantLock、synchronized、volatile 之间的区别？
- 什么是线程池，如何使用?
- 多线程断点续传的实现原理？
- 什么是深拷贝和浅拷贝？
- Java 中对象的生命周期？
- 对并发编程的了解？

###  JVM

- [简述 JVM 内存模型和内存区域？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#jvm_1)
- 简述垃圾回收器的工作原理？
- 如何判断对象的生死？垃圾回收算法？新生代，老生代？
- 哪些情况下的对象会被垃圾回收机制处理掉？
- 垃圾回收机制与调用 System.gc() 的区别？
- [强引用、软引用、弱引用、虚引用之间的区别？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_53)
- 强引用设置为 null，会不会被回收？
- 简述 ClassLoader 类加载机制？
- 对双亲委派模型的了解？
- [String a = "a"+"b"+"c" 在内存中创建几个对象？](https://github.com/jeanboydev/Android-Interview/blob/master/Java.md#java_57)
- 对 Dalvik、ART 虚拟机的了解？
- 对动态加载（OSGI）的了解？
- 常见编码方式有哪些？
- utf-8 编码中的中文占几个字节？int 型占几个字节？

## Android

### 基础

- 四大组件是什么？
- Activity 的生命周期？
- Activity 之间的通信方式？
- Activity 各种情况下的生命周期？
- 横竖屏切换时 Activity 的生命周期
- 前台切换到后台，然后再回到前台时 Activity 的生命周期
- 弹出 Dialog 的时候按 Home 键时 Activity 的生命周期
- 两个 Activity 之间跳转时的生命周期
- 下拉状态栏时 Activity 的生命周期
- Activity 与 Fragment 之间生命周期比较？
- Activity 的四种 LaunchMode（启动模式）的区别？
- Activity 状态保存与恢复？
- Fragment 各种情况下的生命周期？
-  [Activity 和 Fragment 之间怎么通信， Fragment 和 Fragment 怎么通信？](https://github.com/jeanboydev/Android-Interview/blob/master/Android.md#android_base_14)
- Service 的生命周期？
- Service 的启动方式？
- Service 与 IntentService 的区别?
- [Service 和 Activity 之间的通信方式？](https://github.com/jeanboydev/Android-Interview/blob/master/Android.md#android_base_18)
- 对 ContentProvider 的理解？
- ContentProvider、ContentResolver、ContentObserver 之间的关系？
- 对 BroadcastReceiver 的了解？
- 广播的分类？使用方式和场景？
- [动态广播和静态广播有什么区别？](https://github.com/jeanboydev/Android-Interview/blob/master/Android.md#android_base_23)
- AlertDialog、popupWindow、Activity 之间的区别？
- Application 和 Activity 的 Context 之间的区别？
- Android 属性动画特性？
- 请列举 Android 中常见的布局（Layout）类型，并简述其用法，以及排版效率。【猎豹移动】
    LinearLayout、RelativeLayout、FrameLayout 的特性对比及使用场景？
- 对 SurfaceView 的了解？
- Serializable 和 Parcelable 的区别？
- Android 中数据存储方式有哪些？
- 屏幕适配的处理技巧都有哪些?
- Android 各个版本 API 的区别？
- 动态权限适配方案，权限组的概念？
- 为什么不能在子线程更新 UI？
- ListView 图片加载错乱的原理和解决方案？
- 对 RecycleView 的了解？
- Recycleview 和 ListView 的区别？
- RecycleView 实现原理？
- Android Manifest 的作用与理解？
- 多线程在 Android 中的使用？
- 区别 Animation 和 Animator 的用法，概述实现原理？【猎豹移动】

### 高级

- [画出 Android 的大体架构图](https://github.com/jeanboydev/Android-Interview/blob/master/Android.md#android_advance_1)
- 低版本 SDK 如何使用高版本 API？
- AsyncTask 如何使用?
- AsyncTask 机制、原理及不足？
- 如果在 onStop() 的时候做了网络请求，onResume() 的时候怎么恢复？
- Handler 机制和底层实现？
- Handler、Thread、HandlerThread 区别？
   Thread、Looper、MessageQueue、Handler、Message，每个类的功能是什么，这些类之间是什么关系？【猎豹移动】
- ThreadLocal 原理、实现及如何保证 Local 属性？
- 自定义 View 的流程？如何机型适配？
- 自定义 View 的时怎么获取 View 的大小？
- View 的绘制流程？
- View 的事件传递分发机制？
- requestLayout()，onLayout()，onDraw()，drawChild() 区别与联系？
- invalidate() 和 postInvalidate() 的区别？
- 如何计算一个 View 的嵌套层级？
- Android 动画框架及实现原理？
- 进程和 Application 的生命周期的关系？
- SpareArray 的实现原理？
- SharedPreferences 的实现眼里？是否进程同步？如何做到同步？
- ContentProvider 是如何实现数据共享的？
- ContentProvider 的权限管理？
-. Android 系统为什么会设计 ContentProvider？
- Android 线程有没有上限？
- 怎么去除重复代码？
- Android 中开启摄像头的主要流程？
- 对 Bitmap 对象的了解？
- 图片加载原理？
- 图片压缩原理？
- 图片框架实现原理？LRUCache 原理？
- EventBus 实现原理？
- ButterKnife 实现原理？
- Volley 实现原理？
- okhttp 实现原理？
- 服务器只提供数据接收接口，在多线程或多进程条件下，如何保证数据的有序到达？
- SQLite 数据库升级，数据迁移问题？
- 数据库框架对比和源码分析？
- CAS介绍，OAuth 授权机制？
- 谈谈你对安卓签名的理解
- App 是如何沙箱化，为什么要这么做？

### 混合开发

- 混合开发的方式？各自优缺点和使用场景？
- Hybird
- React Native
- Weex
- Flutter
- Dart
- 快应用

### Framework

- 请介绍一下 NDK？
- 如何加载 ndk 库？如何在 jni 中注册 native 函数，有几种注册方式?【猎豹移动】
- Android 进程分类？
- 谈谈对进程共享和线程安全的认识？
- 谈谈对多进程开发的理解以及多进程应用场景？
- 什么是协程？
- 逻辑地址与物理地址，为什么使用逻辑地址？
- Android 为每个应用程序分配的内存大小是多少？
- 进程保活的方式？
- 系统启动流程是什么？
- 一个应用程序安装到手机上的过程发生了什么？
- App 启动流程，从点击桌面开始（Activity 启动流程）？
- 什么是 AIDL？解决了什么问题？如何使用？
- Binder 机制及工作原理？
- App 中唤醒其他进程的实现方式？
- Activity、Window、View 三者的关系与区别？
- ApplicationContext 和 ActivityContext 的区别？
- ActivityThread，ActivityManagerService，WindowManagerService 的工作原理？
- PackageManagerService 的工作原理？
- PowerManagerService 的工作原理？
- 权限管理系统（底层的权限是如何进行 grant 的）？
- 操作系统中进程和线程有什么区别？系统在什么情况下会在用户态和内核态中切换？【猎豹移动】
- 如果一个 App 里面有多个进程存在，请列举你所知道的全部 IPC 方法。

### 性能优化

- 如何对 Android 应用进行性能分析以及优化?
- ANR 产生的原因是什么？怎么定位？
- OOM 是什么？怎么解决？是否可以 try catch？
- 内存泄露的解决方法？
- ddms 和 traceView 的使用？
- 性能优化如何分析 systrace？
- 用 IDE 如何分析内存泄漏？
- Java 多线程引发的性能问题，怎么解决？
- 启动页白屏、黑屏、太慢怎么解决？
- App 启动崩溃异常怎么捕捉？
    对于 Android App 闪退，可能有哪些原因？请针对每种情况简述分析过程。【猎豹移动】
- 如何保持应用的稳定性？
- RecyclerView 和 ListView 的性能对比？
- Bitmap 如何处理大图？如何预防 OOM？
- 如何缩小 Apk 的体积?
- 如何统计启动时长？

###  Gradle

- Gradle 源码解析
- 对热修复和插件化的理解？
- 插件化原理分析
- 模块化实现（好处，原因）
- 项目组件化的理解
- 描述清点击 Android Studio 的 build 按钮后发生了什么？

### Kotlin

- 谈谈对 Kotlin 的理解
- 闭包和局部内部类的区别?

## 网络技术

## 数据结构与算法

## 设计模式与架构

## 人事相关

## 成员

## 声明