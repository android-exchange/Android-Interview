## 1. 视图动画（View Animation）
视图动画，也叫Tween（补间）动画，可以进行一系列简单变换（如位置、大小、旋转、透明度等）
> 注意：视图动画并不改变视图的真实属性，如一个Button从A位置平移到了B位置，点击B位置并不能触发Button的点击事件。

## 2. 属性动画（Property Animation）
属性动画是3.0之后引入的，通过改变View的属性来达到动画的目的，如通过改变x，y来改变视图的位置
## 3. 帧动画 （Drawable Animation）
帧动画是将一系列图片进行播放，来达到动画的目的，类似于动画片。

## 4. 物理动画 （DynamicAnimation）
物理动画实在Android8.0中引入，目前有FlingAnimation和SpringAnimation两种

## 参考资料
[Android 三种动画详解](https://www.cnblogs.com/ldq2016/p/5407061.html)
[Android8.0 新SupportLibrary26、27功能及变化介绍](https://juejin.im/post/5a3b9de2f265da43085e336b)