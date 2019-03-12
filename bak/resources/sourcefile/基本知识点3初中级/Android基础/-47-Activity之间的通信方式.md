####  必知：
+ Intent(系统推荐)
+ SharedPreference(俩Activity持有同一个对象，都可以进行传值，不推荐)
+ BroadcastReiceive(广播，只要程序还在设备里，那里传不到？)
+ server(与SharedPreference同理)  
+ Eventbus(BroadcastReiceive同理)
+ contentProvider(与SharedPreference同理)  
+ 通过static静态变量
#####  基本只要两个Activity持有同一对象即可交互
####  必会：
+ Android一套标准的传递流程:  
>+ A：创建Intent，通过setClass方法设置要跳转的B.class,通过putExtra方法设置参数。然后调用startActivity  
>+ B：通过getIntent获取上个界面传入的Intent，通过get类型Extra方法获取传入的参数  
+ 返回流程：
>+ B：调用setResult方法返回resultCode及返回的Intent
>+ A：重写onActivityResult方法，通过resultCode判断返回位置，通过intent参数获取传递数据  
注：若需要返回数据时，在启动一个界面需要调用startActivityForResult方法

+ 参考资料：  
[Android之Activity间通信总结](http://blog.csdn.net/io_field/article/details/50427936)  
[ Android之Activity之间的数据通信方式大全（二）](http://blog.csdn.net/a_running_wolf/article/details/48826495)
