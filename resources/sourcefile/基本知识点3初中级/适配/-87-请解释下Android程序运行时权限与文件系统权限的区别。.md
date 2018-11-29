####  必知：
##### 要区分apk运行时的拥有的权限与在文件系统上被访问（读写执行）的权限两个概念。  
> apk程序是运行在虚拟机上的,对应的是Android独特的权限机制，只有体现到文件系统上时才使用linux的权限设置。
+ （一）linux文件系统上的权限 

```
-rwxr-x--x system system 4156 2010-04-30 16:13 test.apk
```

代表的是相应的用户/用户组及其他人对此文件的访问权限，与此文件运行起来具有的权限完全不相关。 比如上面的例子只能说明system用户拥有对此文件的读写执行权限；system组的用户对此文件拥有读、执行权限；其他人对此文件只具有执行权限。 而test.apk运行起来后可以干哪些事情，跟这个就不相关了。 千万不要看apk文件系统上属于system/system用户及用户组，或者root/root用户及用户组，就认为apk具有system或root权限
+ （二）Android的权限规则 
>+ （1)Android中的apk必须签名 这种签名不是基于权威证书的，不会决定某个应用允不允许安装，而是一种自签名证书。 重要的是，android系统有的权限是基于签名的。比如：system等级的权限有专门对应的签名，签名不对，权限也就获取不到。
默认生成的APK文件是debug签名的。
获取system权限时用到的签名，见：如何使Android应用程序获取系统权限  
>+ （2）基于UserID的进程级别的安全机制
大家都知道，进程有独立的地址空间，进程与进程间默认是不能互相访问的，是一种很可靠的保护机制。
Android通过为每一个安装在设备上的包（apk）分配唯一的linux userID来实现，名称为"app_"加一个数字，比如app_43
不同的UserID，运行在不同的进程，所以apk之间默认便不能相互访问。
Android提供了如下的一种机制，可以使两个apk打破前面讲的这种壁垒。
在AndroidManifest.xml中利用sharedUserId属性给不同的package分配相同的userID，通过这样做，两个package可以被当做同一个程序，
系统会分配给两个程序相同的UserID。当然，基于安全考虑，两个package需要有相同的签名，否则没有验证也就没有意义了。  
（这里补充一点:并不是说分配了同样的UserID，两程序就运行在同一进程, 下面为PS指令摘取的，
显然，system、app_2分别对应的两个进程的PID都不同，不知Android到底是怎样实现它的机制的）

```
User PID PPID
system 953 883 187340 55052 ffffffff afe0cbcc S system_server
app_2 1072 883 100264 19564 ffffffff afe0dcc4 S com.android.inputmethod.
system 1083 883 111808 23192 ffffffff afe0dcc4 S android.process.omsservi
app_2 1088 883 156464 45720 ffffffff afe0dcc4 S android.process.acore
```

>+ （3）默认apk生成的数据对外是不可见的
实现方法是：Android会为程序存储的数据分配该程序的UserID。
借助于Linux严格的文件系统访问权限，便实现了apk之间不能相互访问似有数据的机制。
例：我的应用创建的一个文件，默认权限如下，可以看到只有UserID为app_21的程序才能读写该文件。
-rw------- app_21 app_21 87650 2000-01-01 09:48 test.txt

> 如何对外开放？

```
<1> 使用MODE_WORLD_READABLE and/or MODE_WORLD_WRITEABLE 标记。
When creating a new file with getSharedPreferences(String, int), openFileOutput(String, int), or openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory), you can use the MODE_WORLD_READABLE and/or MODE_WORLD_WRITEABLE flags to allow any other package to read/write the file. When setting these flags, the file is still owned by your application, but its global read and/or write permissions have been set appropriately so any other application can see it.
```

>+ （4）AndroidManifest.xml中的显式权限声明
Android默认应用是没有任何权限去操作其他应用或系统相关特性的，应用在进行某些操作时都需要显式地去申请相应的权限。
一般以下动作时都需要申请相应的权限：

```
A particular permission may be enforced at a number of places during your program's operation:

At the time of a call into the system, to prevent an application from executing certain functions.
When starting an activity, to prevent applications from launching activities of other applications.
Both sending and receiving broadcasts, to control who can receive your broadcast or who can send a broadcast to you.
When accessing and operating on a content provider.
Binding or starting a service.
```
> 在应用安装的时候，package installer会检测该应用请求的权限，根据该应用的签名或者提示用户来分配相应的权限。
在程序运行期间是不检测权限的。如果安装时权限获取失败，那执行就会出错，不会提示用户权限不够。
大多数情况下，权限不足导致的失败会引发一个 SecurityException， 会在系统log（system log）中有相关记录。

>+ （5）权限继承/UserID继承
当我们遇到apk权限不足时，我们有时会考虑写一个linux程序，然后由apk调用它去完成某个它没有权限完成的事情，很遗憾，这种方法是行不通的。
前面讲过，android权限是经营在进程层面的，也就是说一个apk应用启动的子进程的权限不可能超越其父进程的权限（即apk的权限），
即使单独运行某个应用有权限做某事，但如果它是由一个apk调用的，那权限就会被限制。
实际上，android是通过给子进程分配父进程的UserID实现这一机制的。

+ （三）常见权限不足问题分析

首先要知道，普通apk程序是运行在非root、非system层级的，也就是说看要访问的文件的权限时，看的是最后三位。
另外，通过system/app安装的apk的权限一般比直接安装或adb install安装的apk的权限要高一些。

言归正传，运行一个android应用程序过程中遇到权限不足，一般分为两种情况:
>+ （1）Log中可明显看到权限不足的提示。
此种情况一般是AndroidManifest.xml中缺少相应的权限设置，好好查找一番权限列表，应该就可解决，是最易处理的情况。  
有时权限都加上了，但还是报权限不足，是什么情况呢？
Android系统有一些API及权限是需要apk具有一定的等级才能运行的。
比如 SystemClock.setCurrentTimeMillis()修改系统时间，WRITE_SECURE_SETTINGS权限 好像都是需要有system级的权限才行。
也就是说UserID是system.

>+ （2）Log里没有报权限不足，而是一些其他Exception的提示,这也有可能是权限不足造成的。
比如：我们常会想读/写一个配置文件或其他一些不是自己创建的文件，常会报java.io.FileNotFoundException错误。
系统认为比较重要的文件一般权限设置的也会比较严格，特别是一些很重要的(配置)文件或目录。
如

```

-r--r----- bluetooth bluetooth 935 2010-07-09 20:21 dbus.conf
drwxrwx--x system system 2010-07-07 02:05 data

dbus.conf好像是蓝牙的配置文件，从权限上来看，根本就不可能改动，非bluetooth用户连读的权利都没有。
/data目录下存的是所有程序的私有数据，默认情况下android是不允许普通apk访问/data目录下内容的，通过data目录的权限设置可知，其他用户没有读的权限。
所以adb普通权限下在data目录下敲ls命令，会得到opendir failed, Permission denied的错误，通过代码file.listfiles()也无法获得data目录下的内容。
```


上面两种情况，一般都需要提升apk的权限，目前我所知的apk能提升到的权限就是system（具体方法见：如何使Android应用程序获取系统权限）,
####  必会：
+ Android程序运行 是虚拟机Dalvik( android授权) 
+ 文件系统 是 linux 内核 授权


#### 参考资料
+ [Android程序运行时权限与文件系统权限的区别](http://blog.csdn.net/u010142437/article/details/11991975)
