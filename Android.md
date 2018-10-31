# Android

## 基础

1. 四大组件是什么？
2. Activity 的生命周期？
3. Activity 之间的通信方式？
4. Activity 各种情况下的生命周期？
1. 横竖屏切换时 Activity 的生命周期
2. 前台切换到后台，然后再回到前台时 Activity 的生命周期
3. 弹出 Dialog 的时候按 Home 键时 Activity 的生命周期
4. 两个 Activity 之间跳转时的生命周期
5. 下拉状态栏时 Activity 的生命周期
5. Activity 与 Fragment 之间生命周期比较？
6. Activity 的四种 LaunchMode（启动模式）的区别？
7. Activity 状态保存与恢复？
8. Fragment 各种情况下的生命周期？
9. Activity 和 Fragment 之间怎么通信， Fragment 和 Fragment 怎么通信？
10. Service 的生命周期？
11. Service 的启动方式？
12. Service 与 IntentService 的区别?
13. Service 和 Activity 之间的通信方式？
14. 对 ContentProvider 的理解？
15. ContentProvider、ContentResolver、ContentObserver 之间的关系？
16. 对 BroadcastReceiver 的了解？
17. 广播的分类？使用方式和场景？
18. 动态广播和静态广播有什么区别？
19. AlertDialog、popupWindow、Activity 之间的区别？
20. Application 和 Activity 的 Context 之间的区别？
21. Android 属性动画特性？
22. LinearLayout、RelativeLayout、FrameLayout 的特性对比及使用场景？
23. 对 SurfaceView 的了解？
24. Serializable 和 Parcelable 的区别？
25. Android 中数据存储方式有哪些？
26. 屏幕适配的处理技巧都有哪些?
27. Android 各个版本 API 的区别？
28. 动态权限适配方案，权限组的概念？
29. 为什么不能在子线程更新 UI？
30. ListView 图片加载错乱的原理和解决方案？
31. 对 RecycleView 的了解？
32. Recycleview 和 ListView 的区别？
33. RecycleView 实现原理？
34. Android Manifest 的作用与理解？
35. 多线程在 Android 中的使用？

## 进阶

36. 画出 Android 的大体架构图
37. 低版本 SDK 如何使用高版本 API？
38. AsyncTask 如何使用?
39. AsyncTask 机制、原理及不足？
40. 如果在 onStop() 的时候做了网络请求，onResume() 的时候怎么恢复？
41. Handler 机制和底层实现？
42. Handler、Thread、HandlerThread 区别？
43. ThreadLocal 原理、实现及如何保证 Local 属性？
44. 自定义 View 的流程？如何机型适配？
45. 自定义 View 的时怎么获取 View 的大小？
46. View 的绘制流程？
47. View 的事件传递分发机制？
48. requestLayout()，onLayout()，onDraw()，drawChild() 区别与联系？
49. invalidate() 和 postInvalidate() 的区别？
50. 如何计算一个 View 的嵌套层级？
51. Android 动画框架及实现原理？
52. 进程和 Application 的生命周期的关系？
53. SpareArray 的实现原理？
54. SharedPreferences 的实现眼里？是否进程同步？如何做到同步？
55. ContentProvider 是如何实现数据共享的？
56. ContentProvider 的权限管理？
57. Android 系统为什么会设计 ContentProvider？
58. Android 线程有没有上限？
59. 怎么去除重复代码？
60. Android 中开启摄像头的主要流程？
61. 对 Bitmap 对象的了解？
62. 图片加载原理？
63. 图片压缩原理？
64. 图片框架实现原理？LRUCache 原理？
65. EventBus 实现原理？
66. ButterKnife 实现原理？
67. Volley 实现原理？
68. okhttp 实现原理？
69. 服务器只提供数据接收接口，在多线程或多进程条件下，如何保证数据的有序到达？
70. SQLite 数据库升级，数据迁移问题？
71. 数据库框架对比和源码分析？
72. CAS介绍，OAuth 授权机制？
73. 谈谈你对安卓签名的理解
74. App 是如何沙箱化，为什么要这么做？

## 混合开发

75. 混合开发的方式？各自优缺点和使用场景？
76. Hybird
77. React Native
78. Weex
79. Flutter
80. Dart
81. 快应用

## Framework

82. 请介绍一下 NDK？
83. 如何在 jni 中注册 native 函数，有几种注册方式?
84. Android 进程分类？
85. 谈谈对进程共享和线程安全的认识？
86. 谈谈对多进程开发的理解以及多进程应用场景？
87. 什么是协程？
88. 逻辑地址与物理地址，为什么使用逻辑地址？
89. Android 为每个应用程序分配的内存大小是多少？
90. 进程保活的方式？
91. 系统启动流程是什么？
92. 一个应用程序安装到手机上的过程发生了什么？
93. App 启动流程，从点击桌面开始（Activity 启动流程）？
94. 什么是 AIDL？解决了什么问题？如何使用？
95. Binder 机制及工作原理？
96. App 中唤醒其他进程的实现方式？
97. Activity、Window、View 三者的关系与区别？
98. ApplicationContext 和 ActivityContext 的区别？
99. ActivityThread，ActivityManagerService，WindowManagerService 的工作原理？
100. PackageManagerService 的工作原理？
101. PowerManagerService 的工作原理？
102. 权限管理系统（底层的权限是如何进行 grant 的）？

## 性能优化

103. 如何对 Android 应用进行性能分析以及优化?
104. ANR 产生的原因是什么？怎么定位？
105. OOM 是什么？怎么解决？是否可以 try catch？
106. 内存泄露的解决方法？
107. ddms 和 traceView 的使用？
108. 性能优化如何分析 systrace？
109. 用 IDE 如何分析内存泄漏？
110. Java 多线程引发的性能问题，怎么解决？
111. 启动页白屏、黑屏、太慢怎么解决？
112. App 启动崩溃异常怎么捕捉？
113. 如何保持应用的稳定性？
114. RecyclerView 和 ListView 的性能对比？
115. Bitmap 如何处理大图？如何预防 OOM？
116. 如何缩小 Apk 的体积?
117. 如何统计启动时长？

##  Gradle

118. Glide 源码解析
119. 对热修复和插件化的理解？
120. 插件化原理分析
121. 模块化实现（好处，原因）
122. 项目组件化的理解
123. 描述清点击 Android Studio 的 build 按钮后发生了什么？

## Kotlin

123. 谈谈对 Kotlin 的理解
125. 闭包和局部内部类的区别?