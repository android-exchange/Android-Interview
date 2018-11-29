####  必知：
可以将dictionary.db文件复制到Eclipse Android工程中的res\raw目录中。

所有在res\raw目录中的文件不会被压缩，这样可以直接提取该目录中的文件。

使用openDatabase方法来打开数据库文件，
如果该文件不存在，系统会自动创建/sdcard/dictionary目录，
并将res\raw目录中的 dictionary.db文件复制到/sdcard/dictionary目录中。
####  必会：
+ 所有在res\raw目录中的文件不会被压缩
+ 使用openDatabase方法来打开数据库文件

##### 参考资料
[如何将SQLite的数据库（dictionary.db文件）与APK文件一起发布](http://blog.csdn.net/itchenlin/article/details/24703849)