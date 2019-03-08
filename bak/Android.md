# Android

## 基础

1. 四大组件是什么？
2. Activity 的生命周期？
3. Activity 之间的通信方式？
4. Activity 各种情况下的生命周期？
5. 横竖屏切换时 Activity 的生命周期
6. 前台切换到后台，然后再回到前台时 Activity 的生命周期
7. 弹出 Dialog 的时候按 Home 键时 Activity 的生命周期
8. 两个 Activity 之间跳转时的生命周期
9. 下拉状态栏时 Activity 的生命周期
10. Activity 与 Fragment 之间生命周期比较？
11. Activity 的四种 LaunchMode（启动模式）的区别？
12. Activity 状态保存与恢复？
13. Fragment 各种情况下的生命周期？
## <span id="android_base_14">14. Activity 和 Fragment 之间怎么通信， Fragment 和 Fragment 怎么通信？</span>

Activity 传值给 Fragment：通过 Bundle 对象来传递，Activity 中构造 bundle 数据包，调用 Fragment 对象的 `setArguments(Bundle b)` 方法，Fragment 中使用 `getArguments()` 方法获取 Activity 传递过来的数据包取值。

Fragment 传值给 Activity：在 Fragment 中定义一个内部回调接口，Activity 实现该回调接口， Fragment 中获取 Activity 的引用，调用 Activity 实现的业务方法。接口回调机制式 Java 不同对象之间数据交互的通用方法。

Fragment 传值给 Fragment：一个 Fragment 通过 Activity 获取到另外一个 Fragment 直接调用方法传值。

15. Service 的生命周期？
16. Service 的启动方式？
17. Service 与 IntentService 的区别?
## <span id="android_base_18">18. Service 和 Activity 之间的通信方式？</span>

- 通过 Binder 对象
- 通过 Broadcast（广播）的形式

19. 对 ContentProvider 的理解？
20. ContentProvider、ContentResolver、ContentObserver 之间的关系？
21. 对 BroadcastReceiver 的了解？
22. 广播的分类？使用方式和场景？
## <span id="android_base_23">23. 动态广播和静态广播有什么区别？</span>

- 动态的比静态的安全
- 静态在 App 启动的时候就初始化了，动态使用代码初始化
- 静态需要配置，动态不需要
- 生存期，静态广播的生存期可以比动态广播的长很多
- 优先级动态广播的优先级比静态广播高

24. AlertDialog、popupWindow、Activity 之间的区别？
25. Application 和 Activity 的 Context 之间的区别？
26. Android 属性动画特性？
27. LinearLayout、RelativeLayout、FrameLayout 的特性对比及使用场景？
28. 对 SurfaceView 的了解？
29. Serializable 和 Parcelable 的区别？
30. Android 中数据存储方式有哪些？
31. 屏幕适配的处理技巧都有哪些?
32. Android 各个版本 API 的区别？
33. 动态权限适配方案，权限组的概念？
34. 为什么不能在子线程更新 UI？
35. ListView 图片加载错乱的原理和解决方案？
36. 对 RecycleView 的了解？
37. Recycleview 和 ListView 的区别？
38. RecycleView 实现原理？
39. Android Manifest 的作用与理解？
40. 多线程在 Android 中的使用？

## 进阶

1. <span id="android_advance_1">画出 Android 的大体架构图</span>

   ![](https://i.loli.net/2018/11/04/5bde5e25b0aa9.png)

2. 低版本 SDK 如何使用高版本 API？

3. AsyncTask 如何使用?

4. AsyncTask 机制、原理及不足？

5. 如果在 onStop() 的时候做了网络请求，onResume() 的时候怎么恢复？

6. Handler 机制和底层实现？

7. Handler、Thread、HandlerThread 区别？

8. ThreadLocal 原理、实现及如何保证 Local 属性？

9. 自定义 View 的流程？如何机型适配？

10. 自定义 View 的时怎么获取 View 的大小？

11. View 的绘制流程？

12. View 的事件传递分发机制？

13. requestLayout()，onLayout()，onDraw()，drawChild() 区别与联系？

14. invalidate() 和 postInvalidate() 的区别？

15. 如何计算一个 View 的嵌套层级？

16. Android 动画框架及实现原理？

17. 进程和 Application 的生命周期的关系？

18. SpareArray 的实现原理？

19. SharedPreferences 的实现眼里？是否进程同步？如何做到同步？

20. ContentProvider 是如何实现数据共享的？

21. ContentProvider 的权限管理？

22. Android 系统为什么会设计 ContentProvider？

23. Android 线程有没有上限？

24. 怎么去除重复代码？

25. Android 中开启摄像头的主要流程？

26. 对 Bitmap 对象的了解？

27. 图片加载原理？

28. 图片压缩原理？

29. 图片框架实现原理？LRUCache 原理？

30. EventBus 实现原理？

31. ButterKnife 实现原理？

32. Volley 实现原理？

33. okhttp 实现原理？

34. 服务器只提供数据接收接口，在多线程或多进程条件下，如何保证数据的有序到达？

35. SQLite 数据库升级，数据迁移问题？

36. 数据库框架对比和源码分析？

37. CAS介绍，OAuth 授权机制？

38. 谈谈你对安卓签名的理解

39. App 是如何沙箱化，为什么要这么做？

## 混合开发

1. 混合开发的方式？各自优缺点和使用场景？
2. Hybird
3. React Native
4. Weex
5. Flutter
6. Dart
7. 快应用

## Framework

1. 请介绍一下 NDK？
2. 如何在 jni 中注册 native 函数，有几种注册方式?
3. Android 进程分类？
4. 谈谈对进程共享和线程安全的认识？
5. 谈谈对多进程开发的理解以及多进程应用场景？
6. 什么是协程？
7. 逻辑地址与物理地址，为什么使用逻辑地址？
8. Android 为每个应用程序分配的内存大小是多少？
9. 进程保活的方式？
10. 系统启动流程是什么？
11. 一个应用程序安装到手机上的过程发生了什么？
12. App 启动流程，从点击桌面开始（Activity 启动流程）？
13. 什么是 AIDL？解决了什么问题？如何使用？
14. Binder 机制及工作原理？
15. App 中唤醒其他进程的实现方式？
16. Activity、Window、View 三者的关系与区别？
17. ApplicationContext 和 ActivityContext 的区别？
18. ActivityThread，ActivityManagerService，WindowManagerService 的工作原理？
19. PackageManagerService 的工作原理？
20. PowerManagerService 的工作原理？
21. 权限管理系统（底层的权限是如何进行 grant 的）？

## 性能优化

1. 如何对 Android 应用进行性能分析以及优化?
2. ANR 产生的原因是什么？怎么定位？
3. OOM 是什么？怎么解决？是否可以 try catch？
4. 内存泄露的解决方法？
5. ddms 和 traceView 的使用？
6. 性能优化如何分析 systrace？
7. 用 IDE 如何分析内存泄漏？
8. Java 多线程引发的性能问题，怎么解决？
9. 启动页白屏、黑屏、太慢怎么解决？
10. App 启动崩溃异常怎么捕捉？
11. 如何保持应用的稳定性？
12. RecyclerView 和 ListView 的性能对比？
13. Bitmap 如何处理大图？如何预防 OOM？
14. 如何缩小 Apk 的体积?
15. 如何统计启动时长？

##  Gradle

1. Glide 源码解析
2. 对热修复和插件化的理解？
3. 插件化原理分析
4. 模块化实现（好处，原因）
5. 项目组件化的理解
6. 描述清点击 Android Studio 的 build 按钮后发生了什么？

## Kotlin

1. 谈谈对 Kotlin 的理解
2. 闭包和局部内部类的区别?