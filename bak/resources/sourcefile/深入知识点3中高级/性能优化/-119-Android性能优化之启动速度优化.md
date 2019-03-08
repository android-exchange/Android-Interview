####  必知：
####  必会：

### Android APP优化启动速度?

#### 1、异步初始化

Application是程序的主入口，特别是很多第三方SDK都会需要在Application的onCreate里面做很多初始化操作.

一般来说我们可以将这些初始化放在一个单独的线程中处理, 你可以直接new Thread()，当然，你也可以通过公共的线程池来进行异步的初始化工作，这个是最能够压缩启动时间的方式。

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                //初始化第三方SDK
                
            }
        }.start();
    }
}
```

#### 2、后台Service进行初始化

思路：在Application中启动一个IntentService，在IntentService中启动自己，执行一些初始化操作。
App的onCreate代码如下：
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.start(this);
    }
}
```
IntentService代码如下：
```java
public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.xxxx";
    public InitializeService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }
    private void performInit() {
        //初始化第三方SDK
    }
    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }
}
```
#### 3、界面预加载

当系统加载一个Activity的时候，onCreate()是一个耗时过程，那么在这个过程中，系统为了让用户能有一个比较好的体验，实际上会先绘制一些初始界面，类似于PlaceHolder。

系统首先会读取当前Activity的Theme，然后根据Theme中的配置来绘制，当Activity加载完毕后，才会替换为真正的界面。

所以，Google官方提供的解决方案，就是通过android:windowBackground属性，来进行加载前的配置，同时，这里不仅可以配置颜色，还能配置图片，例如，我们可以使用一个layer-list来作为android:windowBackground要显示的图：

在drawable文件夹下面 , 做一个logo_splash的背景:
start_window.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 底层白色 -->
    <item android:drawable="@color/white" />

    <!-- 顶层Logo居中 -->
    <item>
        <bitmap
            android:gravity="center"
            android:src="@drawable/ic_github" />
    </item>
</layer-list>
```
在style里面弄一个主题:
```xml
<style name="StartStyle" parent="AppTheme">
        <item name="android:windowBackground">@drawable/start_window</item>
</style>
```
在清单文件里面给Activity指定需要预加载的Style：

```xml
        <activity android:name=".MainActivity"                                         android:theme="@style/StartStyle">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
```
Activity代码如下：

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
```

参考资料:

[Android优化四：App启动速度优化](https://www.jianshu.com/p/da647e23d9a3)

整理人: showdy