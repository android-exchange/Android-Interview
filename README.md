# Android-Interview

## Java 基础

- 什么是面向对象(OOP)？
- 接口（Interface）与抽象类（Abstract Class）的区别？
- 覆盖（Overriding）与重载（OverLoading）的区别?
- 谈谈对 java 多态的理解？
- 序列化是什么？有哪些方式？
- 什么是匿名内部类？内部类的作用?
- 父类的静态方法能否被子类重写？
- 静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
- 成员内部类、静态内部类、局部内部类和匿名内部类的理解，以及项目中的应用
- Java 中 == 和 equals 和 hashCode 的区别？
- Integer 和 int 之间的区别？
- String 转换成 Integer 的方式及原理
- Java 中的 final, finally 和 finalize 的区别？
- Java 中 static 关键字的作用？
- 泛型中 extends 和 super 的区别？
- 讲一下常见编码方式？
- utf-8 编码中的中文占几个字节？int 型几个字节？
- 静态代理和动态代理的区别，什么场景使用？
- Java 的异常体系
- 谈谈你对解析与分派的认识
- Java 中实现多态的机制是什么？
- 说说你对 Java 反射的理解
- 说说你对 Java 注解的理解
- 说说你对依赖注入的理解
- 说一下泛型原理，并举例说明
- Java 中 String 的了解
- String 为什么要设计成不可变的？

##  数据结构与算法

- 常用数据结构简介
- 并发集合了解哪些？
- 列举 java 的集合以及集合之间的继承关系
- 集合类以及集合框架
- 容器类介绍以及之间的区别
- List、Set、Map 的区别？
- HashMap 的实现原理
- ArrayMap 和 HashMap 的对比
- HashTable 实现原理
- HashMap 与 HashTable 的区别
- HashMap 与 HashSet 的区别
- HashSet 与 HashMap 怎么判断集合元素重复？
- ArrayList 和 LinkedList 的区别，以及应用场景
- 数组和链表的区别
- 二叉树的深度优先遍历和广度优先遍历的具体实现
- 堆的结构
- 堆和树的区别
- 什么是深拷贝和浅拷贝
- 讲一下对树，B+ 树的理解
- 讲一下对图的理解
- 排序算法有哪些？
- 最快的排序算法是哪个？
- 手写一个冒泡排序
- 手写快速排序代码
- 快速排序的过程、时间复杂度、空间复杂度
- 手写堆排序
- 给阿里2万多名员工按年龄排序应该选择哪个算法？
- GC算法(各种算法的优缺点以及应用场景)
- 蚁群算法与蒙特卡洛算法
- 子串包含问题(KMP 算法)写代码实现
- 一个无序，不重复数组，输出N个元素，使得N个元素的和相加为M，给出时间复杂度、空间复杂度。手写算法
- 万亿级别的两个URL文件A和B，如何求出A和B的差集C(提示：Bit映射->hash分组->多文件读写效率->磁盘寻址以及应用层面对寻址的优化)
- 两个不重复的数组集合中，求共同的元素。
- 两个不重复的数组集合中，这两个集合都是海量数据，内存中放不下，怎么求共同的元素？
- 一个文件中有100万个整数，由空格分开，在程序中判断用户输入的整数是否在此文件中。说出最优的方法
- 一张Bitmap所占内存以及内存占用的计算
- 2000万个整数，找出第五十大的数字？
- 求1000以内的水仙花数以及40亿以内的水仙花数
- 烧一根不均匀的绳，从头烧到尾总共需要1个小时。现在有若干条材质相同的绳子，问如何用烧绳的方法来计时一个小时十五分钟呢？
- 5枚硬币，2正3反如何划分为两堆然后通过翻转让两堆中正面向上的硬8币和反面向上的硬币个数相同
- 时针走一圈，时针分针重合几次


##  线程、多线程和线程池

- 开启线程的三种方式？
- 线程和进程的区别？
- 为什么要有线程，而不是仅仅用进程？
- run() 和 start() 方法区别？
- Thread，Runnable 区别？
- 线程中 sleep() 和 wait() 有什么区别，各有什么含义？
- 谈谈 wait、notify 关键字的理解
- 线程间操作 List
- Java 中什么是强引用、软引用、弱引用以及虚引用？
- HashMap 和 HashTable 之间的区别？
- String、StringBuffer、StringBuilder 的区别？
- Java 中对象的生命周期
- synchronized 关键字的用法、作用、原理
- volatile 关键字的用法、作用、原理
- transient 关键字的用法、作用、原理
- ReentrantLock 、synchronized 和 volatile 比较
- 什么是线程池，如何使用?
- 谈谈你对并发编程的理解并举例说明
- 多线程断点续传原理及实现

## Java 虚拟机

- 简述垃圾回收器的工作原理
- 哪些情况下的对象会被垃圾回收机制处理掉？
- String a = "a"+"b"+"c" 在内存中创建几个对象？
- Java 类的加载过程？
- ClassLoader 类加载机制
- 如何判断对象的生死？垃圾回收算法？新生代，老生代？
- 对Dalvik、ART虚拟机有什么了解？
- 谈谈你对双亲委派模型理解
- 谈谈对动态加载（OSGI）的理解
- JVM内存模型，内存区域
- 垃圾回收机制与调用System.gc()区别

## Kotlin

- 谈谈对 Kotlin 的理解
- 闭包和局部内部类的区别?

## Android 基础

- 四大组件是什么？
- 四大组件的生命周期和简单用法
- Activity 之间的通信方式
- Activity 各种情况下的生命周期
- 横竖屏切换的时候，Activity 各种情况下的生命周期
- Activity 与 Fragment 之间生命周期比较
- Activity 上有 Dialog 的时候按 Home 键时的生命周期
- 两个Activity 之间跳转时必然会执行的是哪几个方法？
- 前台切换到后台，然后再回到前台，Activity 生命周期回调方法。弹出Dialog，生命值周期回调方法。
- Activity 的四种启动模式对比
- Activity 状态保存于恢复
- Fragment各种情况下的生命周期
- Fragment 状态保存 startActivityForResult 是哪个类的方法，在什么情况下使用？
- Fragment 之间传递数据的方式？
- Activity 怎么和 Service 绑定？
- 怎么在Activity 中启动自己对应的Service？
- Service 和 Activity 怎么进行数据交互？
- Service 的开启方式
- 请描述一下 Service 的生命周期
- 谈谈你对 ContentProvider 的理解
- 说说 ContentProvider、ContentResolver、ContentObserver 之间的关系
- 请描述一下广播 BroadcastReceiver 的理解
- 广播的分类
- 广播使用的方式和场景
- 在 manifest 和代码中如何注册和使用 BroadcastReceiver?
- 本地广播和全局广播有什么差别？
- AlertDialog、popupWindow、Activity 区别
- Application 和 Activity 的 Context 对象的区别
- Android 属性动画特性
- LinearLayout、RelativeLayout、FrameLayout 的特性及对比，并介绍使用场景
- 介绍下 SurfaceView
- RecycleView 的使用
- Serializable 和 Parcelable 的区别？
- Android中数据存储方式

## Android 进阶

- Android 动画框架实现原理
- Android 各个版本API的区别
- Requestlayout，onlayout，onDraw，DrawChild 区别与联系
- invalidate 和 postInvalidate 的区别及使用
- Activity、Window、View 三者的差别
- 谈谈对Volley的理解
- 如何优化自定义View
- 低版本SDK如何实现高版本api？
- Bitmap 对象的理解
- ActivityThread，AMS，WMS 的工作原理
- 自定义View如何考虑机型适配
- 自定义View的事件
- LaunchMode应用场景
- AsyncTask 如何使用?
- SpareArray原理
- 请介绍下ContentProvider 是如何实现数据共享的？
- Service与Activity之间通信的几种方式
- IntentService原理及作用是什么？
- 说说 Activity、Intent、Service 是什么关系
- ApplicationContext 和 ActivityContext 的区别
- SP 是进程同步的吗?有什么方法做到同步？
- 谈谈多线程在 Android 中的使用
- 进程和 Application 的生命周期
- 封装View的时候怎么知道view的大小
- RecycleView 原理
- AndroidManifest 的作用与理解
- Handler 机制和底层实现
- Handler、Thread和HandlerThread的差别
- ThreadLocal原理，实现及如何保证Local属性？
- 请描述一下View事件传递分发机制
- View刷新机制
- View绘制流程
- 自定义控件原理
- AsyncTask机制、原理及不足
- 为什么不能在子线程更新UI？
- ANR产生的原因是什么？怎么定位？
- OOM 是什么？怎么解决？
- OOM 是否可以 try catch？为什么？
- 内存泄露场的解决方法
- LruCache默认缓存大小
- ContentProvider的权限管理
- 计算一个view的嵌套层级
- Activity栈
- Android线程有没有上限？
- 知道哪些混合开发的方式？说出它们的优缺点和各自使用场景？
- 屏幕适配的处理技巧都有哪些?
- 服务器只提供数据接收接口，在多线程或多进程条件下，如何保证数据的有序到达？
- 动态布局的理解
- 怎么去除重复代码？
- 画出 Android 的大体架构图
- Recycleview和ListView的区别
- ListView 中图片错位的问题是如何产生的?
- ListView图片加载错乱的原理和解决方案
- 动态权限适配方案，权限组的概念
- Android系统为什么会设计ContentProvider？
- 下拉状态栏是不是影响activity的生命周期
- 如果在onStop的时候做了网络请求，onResume的时候怎么恢复？
- Android中开启摄像头的主要步骤
- CAS介绍
- Hybird
- React Native
- Weex
- Flutter
- Dart
- 快应用
- 图片库源码分析及对比
- 图片框架缓存实现
- LRUCache原理
- 图片加载原理
- 网络框架对比和源码分析
- okhttp源码
- 从网络加载一个10M的图片，说下注意事项
- sqlite升级，增加字段的语句
- 数据库框架对比和源码分析
- 数据库数据迁移问题

##  Gradle

- Glide源码解析
- 对热修复和插件化的理解
- 插件化原理分析
- 模块化实现（好处，原因）
- 热修复,插件化
- 项目组件化的理解
- 描述清点击 Android Studio 的 build 按钮后发生了什么

## 设计模式与架构

- 谈谈你对Android设计模式的理解
- MVC、MVP、MVVM 原理和区别
- 你所知道的设计模式有哪些？
- 项目中常用的设计模式
- 手写生产者/消费者模式
- 写出观察者模式的代码
- 适配器模式，装饰者模式，外观模式的异同？
- 用到的一些开源框架，介绍一个看过源码的，内部实现过程。
- 谈谈对RxJava的理解，功能与原理，优缺点
- 说说EventBus作用，实现方式，代替EventBus的方式
- 从0设计一款App整体架构，如何去做？
- 谈谈对java状态机理解
- Fragment如果在Adapter中使用应该如何解耦？
- 对于应用更新这块是如何做的？(解答：灰度，强制更新，分区域更新)？
- 实现一个Json解析器(可以通过正则提高速度)
- 统计启动时长,标准

## 性能优化

- 如何对Android 应用进行性能分析以及优化?
- ddms 和 traceView
- 性能优化如何分析systrace？
- 用IDE如何分析内存泄漏？
- Java多线程引发的性能问题，怎么解决？
- 启动页白屏及黑屏解决？
- 启动太慢怎么解决？
- App启动崩溃异常捕捉
- 如何保持应用的稳定性
- RecyclerView和ListView的性能对比
- Bitmap如何处理大图，如一张30M的大图，如何预防OOM
- java中的四种引用的区别以及使用场景
- 强引用置为null，会不会被回收？

## Framework

- 请介绍一下NDK
- 如何在jni中注册native函数，有几种注册方式?
- Java如何调用c、c++语言？
- 什么是AIDL？解决了什么问题？如何使用？
- Android进程分类？
- 进程和 Application 的生命周期？
- 进程调度
- 谈谈对进程共享和线程安全的认识
- 谈谈对多进程开发的理解以及多进程应用场景
- 什么是协程？
- 逻辑地址与物理地址，为什么使用逻辑地址？
- 系统启动流程是什么
- 一个应用程序安装到手机上时发生了什么
- App启动流程，从点击桌面开始
- 简述Activity启动全部过程
- Android为每个应用程序分配的内存大小是多少？
- 进程保活的方式
- App中唤醒其他进程的实现方式



## 网络基础

- 描述一次网络请求的流程
- TCP的3次握手和四次挥手
- TCP与UDP的区别及应用
- HTTP协议
- HTTP1.0与2.0的区别
- HTTP报文结构
- HTTP与HTTPS的区别以及如何实现安全性
- HTTPS原理
- 谈谈你对WebSocket的理解
- WebSocket与socket的区别
- 谈谈你对安卓签名的理解
- 视频加密传输
- App 是如何沙箱化，为什么要这么做？
- 权限管理系统（底层的权限是如何进行 grant 的）？

## 其他

- 介绍你做过的哪些项目
- 都使用过哪些框架、平台？
- 都使用过哪些自定义控件？
- 研究比较深入的领域有哪些？
- 对业内信息的关注渠道有哪些？
- 最近都读哪些书？
- 有没有什么开源项目？
- 自己最擅长的技术点，最感兴趣的技术领域和技术点
- 项目中用了哪些开源库，如何避免因为引入开源库而导致的安全性和稳定性问题
- 实习过程中做了什么，有什么产出？
- 您在前一家公司的离职原因是什么？
- 讲一件你印象最深的一件事情
- 介绍一个你影响最深的项目
- 介绍你最热爱最擅长的专业领域
- 公司实习最大的收获是什么？
- 与上级意见不一致时，你将怎么办？
- 自己的优点和缺点是什么？并举例说明？
- 你的学习方法是什么样的？实习过程中如何学习？实习项目中遇到的最大困难是什么以及如何解决的？
- 说一件最能证明你能力的事情
- 针对你你申请的这个职位，你认为你还欠缺什么
- 如果通过这次面试我们单位录用了你，但工作一段时间却发现你根本不适合这个职位，你怎么办？
- 项目中遇到最大的困难是什么？如何解决的？
- 你的职业规划以及个人目标、未来发展路线及求职定位
- 如果你在这次面试中没有被录用，你怎么打算？
- 评价下自己，评价下自己的技术水平，个人代码量如何？
- 通过哪些渠道了解的招聘信息，其他同学都投了哪些公司？
- 业余都有哪些爱好？
- 你做过的哪件事最令自己感到骄傲？
- 假如你晚上要去送一个出国的同学去机场，可单位临时有事非你办不可，你怎么办？
- 就你申请的这个职位，你认为你还欠缺什么？
- 当前的offer状况；如果BATH都给了offer该如何选？
- 你对一份工作更看重哪些方面？平台，技术，氛围，城市，还是money？
- 理想薪资范围；杭州岗和北京岗选哪个？
- 理想中的工作环境是什么？
- 谈谈你对跳槽的看法
- 说说你对行业、技术发展趋势的看法
- 实习过程中周围同事/同学有哪些值得学习的地方？
- 家人对你的工作期望及自己的工作期望
- 如果你的工作出现失误，给本公司造成经济损失，你认为该怎么办？
- 若上司在公开会议上误会你了，该如何解决？
- 是否可以实习，可以实习多久？
- 在五年的时间内，你的职业规划
- 你看中公司的什么？或者公司的那些方面最吸引你？
