####  必知：
[秒懂，Java 注解 （Annotation）你可以这样学](http://blog.csdn.net/briblue/article/details/73824058)
####  必会：
元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种

@Target 有下面的取值

* ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
* ElementType.CONSTRUCTOR 可以给构造方法进行注解
* ElementType.FIELD 可以给属性进行注解
* ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
* ElementType.METHOD 可以给方法进行注解
* ElementType.PACKAGE 可以给一个包进行注解
* ElementType.PARAMETER 可以给一个方法内的参数进行注解
* ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举