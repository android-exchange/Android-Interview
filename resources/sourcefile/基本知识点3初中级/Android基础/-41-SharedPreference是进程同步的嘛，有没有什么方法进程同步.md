SharedPreference不是进程同步的

* 在使用SharedPreference时，有很多模式可以设置，一般为MODE_PRIVATE，不是进程同步的。
* 我们可以设置模式MODE_MULTI_PROCESS做到进程同步，但因为该模式有很多坑，已经被Google弃用。
* 官方建议使用ContentProvider来达到进程同步的目的

> 具体原理分析请见参考资料

## 参考资料
[Android SharedPreference 支持多进程](https://www.jianshu.com/p/875d13458538)