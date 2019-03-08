####  必知：
####  必会：
### `Parcelable`和`Serializable`有什么用途及差异?

* 用途:

  `Parcelable`和`Serializable`接口都可以完成对象的序列化过程, 使对象变为二进制流在内存中传输数据. 当我们需要通过`Intent`或者`Binder`传输数据时就需要使用`Parcelable`和`Serializable`, 及需要把对象持久化到存储设备上或者通过网络传输给其他客户端,需要使用`Serializable`来完成对象的持久化.

* 差异:

  从来源上看, `Parcelable`是`Android`独有的序列化接口, 只能用于`Android`环境.而`Serializable`是`Java`提供的序列化接口, 可以任何`Java`环境中使用.

  从使用上看, `Parcelable`使用比较麻烦, 序列化过程需要实现`Parcelable`的`writeToParcel(Parcel dest, int flags)和describeContents()`, 为了反序列化还提供了一个非空的`CREATOR`的静态字段, 其内部标明如何创建序列化对象和数组,并通过一系列的`read`方法来完成反序列化过程.

   而`Serailizable`比较简单, 直接实现`Serializable`接口, 序列化机制依赖一个`long`类型的`serialVersionUID`, 如果没有指定, 在序列化过程中会基于当前类结构生成一个`hash`值. 如果类结构发生改变会导致自动生成的`hash`值不同导致`serialVersionUID`不同而导致反序列化失败. 注意的是, 静态字段和`transient`关键字标记的字段不会被序列化.如果显式指定`serialVersionUID`, 只要类的结构不发生非常规性变化, 反序列都可以成功. 

  `Parcelable`主要用于内存上, 而`Serializable`用于存储设备上或者网络传输.

  从效率上看, `serializable`的序列化和反序列化都需要使用`IO`操作, 而`Parcelable`不需要`IO`操作, `Parcelable`效率高于`Serializable`.

  ​

### 自定义一个类实现`Parcelable`流程是什么?

`Parcelable`接口中提供`writeToParcel()`方法类实现对象序列化功能; 反序列化功能由`CREATOR`来完成, 内部标明如何创建序列化对象和数组, 并通过`Parcel`的一系列`read`方法类完成反序列过程, 内容描述功能由`describeContents()`方法完成, 几乎所有情况下都返回0, 仅当对象中存在文件描述符时,返回1.

```java
public class User implements Parcelable {
    public int userId;
    public String userName;
    public boolean male;
    public static String hobby; //静态字段不序列化
    public transient String position; //transient标记字段不序列化

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        male = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            //创建序列化对象
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            //创建数组
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (male ? 1 : 0));
    }
}
```

