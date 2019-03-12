####  必知：
Android系统采用分层架构，从下往上依次为：
1. Linux内核
2. 系统库和Android运行时
3. 框架层
4. 应用程序层
####  必会：
#####  Linux内核
1. Android是基于Linux内核开发
2. Linux提供了安全、内存管理、进程管理等服务。 
##### 系统库和Android运行时
1. 系统库是一个C/C++库的集合，包含OpenGL，SQlite等，在开发过程中，开发者通过框架层来调用这些库

2. Android虚拟机位于Android运行时

##### 框架层
1. 框架成提供了日常开发所用的API
2. 包管理器、内容提供者等位于此层

##### 应用程序层
包含了一些原生应用程序，如日历、短信等

#### 结构层次图：
![image](https://upload-images.jianshu.io/upload_images/312781-53f5ca75598fd6fa.jpeg?imageMogr2/auto-orient/)