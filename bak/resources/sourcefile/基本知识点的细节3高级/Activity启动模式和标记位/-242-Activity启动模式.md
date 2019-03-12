####  必知：
####  必会：
### 一. Activity启动模式有哪几种,分别用于什么场景?

* `standard`标准模式:

  系统的默认模式. 一种多实例模式. 每次启动一个`Activity`都会重新创建一个新的实例. 被启动的`Activity`会被放入启动者的栈中, 如果启动者是除了`Activity`外的`Context`(比如`Service`), 没有任务栈而保持.此时需要指定`FLAG_ACTIVITY_NEW_TASK`标记位,创建一个新栈. 

  没有特殊要求,默认为此模式.

* `singleTop`栈顶复用模式:

  如果一个`Activity`的实例已经在栈顶存在, 启动这个`activity`时不会创建新的`Activity`,而会回调`onNewIntent()`,如果不是在栈顶存在则会创建一个新的实例. 

  适合接收通知启动的内容显示页面. 当收到多条新闻推送时,用于展示新闻的`Activity`设置为此模式., 根据传来的intent数据显示不同的新闻信息, 不会启动多个`Activity`.

* `singleTask`栈内复用模式:
  一种单例模式. 只要Activity的实例存在栈内, 再次启动Activity都不会创建新的实例, 只会回调`onNewIntent()`,并从栈内弹出该实例上面的所有实例.

  适合作为程序入口点, 例如浏览器的主界面. 不管从多少个应用启动浏览器都只会启动一次, 其余情况都会走`onNewIntent()`并会清空主界面上面的其他界面.

* `singleInstance`单实例模式:

  加强版的`singleTask`具有`singleTask`所有特性. 设置此模式的`Activity`单独存在一个任务栈内.

  闹钟的响铃界面. 因为设置闹钟后, 如果手机显示在其他界面, 此时闹钟响铃弹出`AlarmAlertActivity`的响铃界面,此`Activity`应为`singleInstance`模式. 如果是`singleTask`打开`AlarmAlertActivity`那么响铃后按返回键会进入闹钟设置界面. 

### 清晰描述`onNewIntent()`和`onConfigurationChanged()`两个生命周期方法的场景?

* `onNewIntent`

  在`singleTop`,`singleTask`,`singleInstance`启动模式下,当再次启动相同的`Activity`,如果期望只有一个实例存在, 再次启动就会调用`onNewIntent()`, 在该方法中可以调用`setIntent(intent)`刷新`intent`数据.

* `onConfigurationChanged`

  当`Android`设备正在运行`App`时,如果设备的语言,屏幕方向,键盘参数改变了.默认会销毁当前`Activity`并重新创建一次来加载新的配置信息. 为了防止系统销毁, 可以在清单文件中对应的`<Activity>`标签下添加`android:configChanges`,可选`location`,`keyboardHidden`,`orientation`等值, 声明的配置发生改变时, 不会重启`Activity`而是回调`onConfigurationChanged()`. 如果设置`orientation`, 当屏幕旋转会阻止系统销毁`Activity`重新创建,而是保持运行,继而回调`onConfigurationChanged()`.`Configuration`对象通过读取对象中最新的配置来自行适配新的UI界面. 从`Android3.2(API13)`开始,当设备旋转时,`screen size`也会发生改变,因此需要设置`android:configChanges="orientation|screenSize"`.
  
 > 答案来着刚哥小密圈