####  必知：
####  必会：

### `Android`中为什么不能在子线程中更新UI

在`Android`开发中如果在子线程中更新`UI`基本上会出现 `ViewRootImpl$CalledFromWrongThreadException`，上网一查，得到结果基本都是只能在主线程中更新`UI`. 其实这句话并不是很严谨. 

`Android`规定访问`UI`必须在主线程中进行, 如果子线程访问`UI`就出抛出异常, 这是因为`ViewRootImpl`对`UI`操作进行了验证:

```java
void checkThread() {
        if (mThread != Thread.currentThread()) {
            throw new CalledFromWrongThreadException(
                    "Only the original thread that created a view hierarchy can touch its views.");
        }
    }
```
从上面的方法也可以看出如果`mThread != Thread.currentThread()`这个条件成立的话, 就会抛出异常. 那么`mThread`变量到底指的是哪个线程? 为什么是`original thread`而不是`Main Thread`,这点值得我们思考, 也是为什么说**更新UI只能在主线程**不严谨了.

`Activity`的创建需要新建一个 `ViewRootImpl` 对象，看看 ViewRootImpl 的构造函数：

```java
 public ViewRootImpl(Context context, Display display) {
        //...
        mThread = Thread.currentThread();
        //...
 }
```
而ViewRootImpl的建立是在`ActivityThread.Java`的`final void handleResumeActivity(IBinder token, boolean clearHide, boolean isForward)`里创建的。
```java
final void handleResumeActivity(IBinder token,
            boolean clearHide, boolean isForward, boolean reallyResume, int seq, String reason) {
        ······      
        if (r != null) {
            final Activity a = r.activity;

            if (localLOGV) Slog.v(
                TAG, "Resume " + r + " started activity: " +
                a.mStartedActivity + ", hideForNow: " + r.hideForNow
                + ", finished: " + a.mFinished);

            final int forwardBit = isForward ?
                    WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION : 0;

           
            boolean willBeVisible = !a.mStartedActivity;
            if (!willBeVisible) {
                try {
                    willBeVisible = ActivityManagerNative.getDefault().willActivityBeVisible(
                            a.getActivityToken());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            if (r.window == null && !a.mFinished && willBeVisible) {
                r.window = r.activity.getWindow();
                View decor = r.window.getDecorView();
                decor.setVisibility(View.INVISIBLE);
                ViewManager wm = a.getWindowManager();
                WindowManager.LayoutParams l = r.window.getAttributes();
                a.mDecor = decor;
                l.type = WindowManager.LayoutParams.TYPE_BASE_APPLICATION;
                l.softInputMode |= forwardBit;
                if (r.mPreserveWindow) {
                    a.mWindowAdded = true;
                    r.mPreserveWindow = false;
                    ViewRootImpl impl = decor.getViewRootImpl();
                    if (impl != null) {
                        impl.notifyChildRebuilt();
                    }
                }
             }
               // ······
 }
```
在初始化一个 ViewRootImpl 函数的时候，会调用 native 方法，获取到该线程对象 mThread，接着 setText 函数会调用到 requestLayout 方法(TextView 绘制出来之后，调用 setText 才会去调用 requestLayout 方法，没有绘制出来之前，在子线程中调用 setText 是不会抛出 Exception)：
```java
public void requestLayout() {
    .....
    checkThread();
    .....
}
```
所以现在 **不能在子线程中更新 ui**的问题已经很清楚了，不管 startActivity 函数调用在什么线程，ActivityThread 的内部函数执行是在主线程中的：
```java
/**
 * This manages the execution of the main thread in an
 * application process, scheduling and executing activities,
 * broadcasts, and other operations on it as the activity
 * manager requests.
 */
public final class ActivityThread {
....
}
```
所以 ViewRootImpl 对象的创建也是在主线程中，这就是说一个 activity 的对应 **ViewRootImpl 对象中的 mThread 一定是代表主线程**.

总结: **因为`ViewRootImpl`对象的创建在主线程, `mThread`为主线程, 所以才有只能在主线程更新UI的说法. 那么`original thread`也就指向`main thread`.为什么说不严谨? 如果`ViewRootImpl`对象的创建在子线程,其实也是可以在子线程中取更新UI的.**

参考资料:

[android 不能在子线程中更新ui的讨论和分析](http://blog.csdn.net/self_study/article/details/50548894)

[Android 真的不能在子线程更新UI吗？](https://www.jianshu.com/p/5d1cb4548630)