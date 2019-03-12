####  必知：
Android数据的四种存储方式
+ SQLite
>+ SQLite是一个轻量级的数据库，支持基本的SQL语法，是常被采用的一种数据存储方式。
+ SharePreferences
>+ 除SQLite数据库外，另一种常用的数据存储方式，其本质就是一个xml文件，常用于存储较简单的参数设置。  
1.根据Context获取SharedPreferences对象  
2.利用edit()方法获取Editor对象。  
3.通过Editor对象存储key-value键值对数据。  
4.通过commit()方法提交数据。
+ Contert Provider
>+ Android系统中能实现所有应用程序共享的一种数据存储方式，由于数据通常在各应用间的是互相私密的，所以此存储方式较少使用，但是其又是必不可少的一种存储方式。例如音频，视频，图片和通讯录，一般都可以采用此种方式进行存储。每个Content Provider都会对外提供一个公共的URI（包装成Uri对象），如果应用程序有数据需要共享时，就需要使用Content Provider为这些数据定义一个URI，然后其他的应用程序就通过Content Provider传入这个URI来对数据进行操作。  
1.在当前应用程序中定义一个ContentProvider。  
2.在当前应用程序的AndroidManifest.xml中注册此ContentProvider  
3.其他应用程序通过ContentResolver和Uri来获取此ContentProvider的数据。  
>+ ContentResolver提供了诸如insert(), delete(), query()和update()之类的方法。用于实现对ContentProvider中数据的存取操作。
>+ Uri是一个通用资源标志符，将其分为A，B，C，D 4个部分：  
1.无法改变的标准前缀，包括；"content://"、"tel://"等。当前缀是"content://"时，说明通过一个Content Provider控制这些数据  
2.URI的标识，它通过authorities属性声明，用于定义了是哪个ContentProvider提供这些数据。对于第三方应用程序，为了保证URI标识的唯一性，它必须是一个完整的、小写的   类名。例如;"content://com.test.data.myprovider"  
3.路径，可以近似的理解为需要操作的数据库中表的名字，如："content://hx.android.text.myprovider/name"中的name
4.如果URI中包含表示需要获取的记录的ID；则就返回该id对应的数据，如果没有ID，就表示返回全部；
+ File
>+ 即常说的文件（I/O）存储方法，常用语存储大数量的数据，但是缺点是更新数据将是一件困难的事情。
+ 网络存储  
>+ 通过网络来实现数据的存储和获取。
我们可以调用WebService返回的数据或是解析HTTP协议实现网络数据交互。
具体需要熟悉java.net.*，Android.net.*这两个包的内容
####  必会：
+ Android数据的四种存储方式：  
1. SQLite  
>+ SQLite是轻量级嵌入式数据库引擎，它支持 SQL 语言，并且只利用很少的内存就有很好的性能。此外它还是开源的，任何人都可以使用它。许多开源项目（(Mozilla, PHP, Python）都使用了 SQLite.SQLite 由以下几个组件组成：SQL 编译器、内核、后端以及附件。SQLite 通过利用虚拟机和虚拟数据库引擎（VDBE），使调试、修改和扩展 SQLite 的内核变得更加方便。
2. SharePreferences  
>+ SharedPreferences是Android平台上一个轻量级的存储类，主要是保存一些常用的配置比如窗口状态，一般在Activity中 重载窗口状态onSaveInstanceState保存一般使用SharedPreferences完成，它提供了Android平台常规的Long长 整形、Int整形、String字符串型的保存。 
3. Contert Provider  
>+ Content Provider类实现了一组标准的方法接口，从而能够让其他的应用保存或读取此Content Provider的各种数据类型。也就是说，一个程序可以通过实现一个Content Provider的抽象接口将自己的数据暴露出去。外界根本看不到，也不用看到这个应用暴露的数据在应用当中是如何存储的，或者是用数据库存储还是用文件存储，还是通过网上获得，这些一切都不重要，重要的是外界可以通过这一套标准及统一的接口和程序里的数据打交道，可以读取程序的数据，也可以删除程序的数据，当然，中间也会涉及一些权限的问题。 
4. File
>+ Activity提供了openFileOutput()方法可以用于把数据输出到文件中，具体的实现过程与在J2SE环境中保存数据到文件中是一样的。

[Android实现数据存储技术](http://www.cnblogs.com/hanyonglu/archive/2012/03/01/2374894.html)