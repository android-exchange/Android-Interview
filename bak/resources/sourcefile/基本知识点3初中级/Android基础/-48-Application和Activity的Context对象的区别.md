> Application和Activity都继承自Context，具体来说，Application继承自ContextWraper，Activity继承自ContextThemeWrapper（ContextThemeWrapper是ContextWraper的子类）。

## 区别

1. 生命周期不同，Application的生命周期即App的生命周期，通常比Activity的生命周期长的多，所以对于生命周期长的对象，一般使用application作为context，避免不恰当的持有Activity造成内存泄漏。
2. Application 不能showDialog
3. Application startActivity 时，必须new一个Task
4. Application在layoutInflate 时，直接使用默认主题，可能与当前主题不一样

### 对比图
![](https://user-gold-cdn.xitu.io/2018/2/27/161d6659a9ecfce5?w=500&h=273&f=png&s=55376)

 大家注意看到有一些NO上添加了一些数字，其实这些从能力上来说是YES，但是为什么说是NO呢？下面一个一个解释：

     数字1：启动Activity在这些类中是可以的，但是需要创建一个新的task。一般情况不推荐。

     数字2：在这些类中去layout inflate是合法的，但是会使用系统默认的主题样式，如果你自定义了某些样式可能不会被使用。

     数字3：在receiver为null时允许，在4.2或以上的版本中，用于获取黏性广播的当前值。（可以无视）

     注：ContentProvider、BroadcastReceiver之所以在上述表格中，是因为在其内部方法中都有一个context用于使用。

##  参考资料

[什么时候用Application的Context，什么时候用Activity的Context](https://www.cnblogs.com/tianzhijiexian/p/4514867.html)
[Android Application中的Context和Activity中的Context的异同](https://www.cnblogs.com/ganchuanpu/p/6445251.html)