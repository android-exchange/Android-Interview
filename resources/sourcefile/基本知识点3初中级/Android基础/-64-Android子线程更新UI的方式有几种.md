UI更新的动作必须在主线程中完成，在子线程中更新UI的方式主要有三种

1. activity.runOnUiThread(runnable)
2. 通过主线程中的Handler进行更新
3. 通过View的post（）或者postDelayed方法进行更新

## 参考资料
[Android在子线程中更新UI的方法汇总(共七种)](http://blog.csdn.net/qq_26287435/article/details/65448951)