### Android性能优化之布局优化

#### 1.去除不必要的嵌套和View节点
*  首次不需要使用的节点设置为GONE或使用viewstub
*  使用RelativeLayout代替LinearLayout,因为RelativeLayout可以让视图树的层级少，但是LinearLayout的测量效率要高。如果使用RelativeLayout，需要尽量避免嵌套；如果使用LinearLayout，保证层级不能太深。

> 大约在Android4.0之前，新建工程的默认main.xml中顶节点是LinearLayout，而在之后已经改为RelativeLayout，因为RelativeLayout性能更优，且可以简单实现LinearLayout嵌套才能实现的布局。


#### 2. 抽象布局标签
* (1) <include>标签

   *  include标签常用于将布局中的公共部分提取出来供其他layout共用，以实现布局模块化，这在布局编写方便提供了大大的便利。<include>标签并不能减少布局层级，只能增加代码可读性和布局的复用性。

    * <include>标签唯一需要的属性是layout属性，指定需要包含的布局文件。可以定义android:id和android:layout_*属性来覆盖被引入布局根节点的对应属性值。注意重新定义android:id后，子布局的顶结点i就变化了。
 
* (2) <viewstub>标签
    * viewstub标签同include标签一样可以用来引入一个外部布局，不同的是，viewstub引入的布局默认不会扩张，即既不会占用显示也不会占用位置，从而在解析layout时节省cpu和内存。
    * viewstub常用来引入那些默认不会显示，只在特殊情况下显示的布局，如进度布局、网络失败显示的刷新布局、信息出错出现的提示布局等。

 
* (3) <merge>标签
    * 在使用了include后可能导致布局嵌套过多，多余不必要的layout节点，从而导致解析变慢，不必要的节点和嵌套可通过hierarchy viewer(或设置->开发者选项->显示布局边界查看。
 
    * merge标签可用于两种典型情况：
        * a.  布局顶结点是FrameLayout且不需要设置background或padding等属性，可以用merge代替，因为Activity内容试图的parent view就是个FrameLayout，所以可以用merge消除只剩一个。
        * b.  某布局作为子布局被其他布局include时，使用merge当作该布局的顶节点，这样在被引入时顶结点会自动被忽略，而将其子节点全部合并到主布局中。

#### 3、减少不必要的infalte
* (1) 对于inflate的布局可以直接缓存，用全部变量代替局部变量，避免下次需再次inflate
* (2) ListView提供了item缓存，adapter getView的标准写法，如下：

    ```java
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
    	if (convertView == null) {
    		convertView = inflater.inflate(R.layout.list_item, null);
    		holder = new ViewHolder();
    		……
    		convertView.setTag(holder);
    	} else {
    		holder = (ViewHolder)convertView.getTag();
    	}
    }
    
    /**
     * ViewHolder
     * 
     * @author trinea@trinea.cn 2013-08-01
     */
    private static class ViewHolder {
    
    	ImageView appIcon;
    	TextView  appName;
    	TextView  appInfo;
    }
    
    ```

#### 4. 其他点
* (1) 用SurfaceView或TextureView代替普通View

    SurfaceView或TextureView可以通过将绘图操作移动到另一个单独线程上提高性能。普通View的绘制过程都是在主线程(UI线程)中完成，如果某些绘图操作影响性能就不好优化了，这时我们可以考虑使用SurfaceView和TextureView，他们的绘图操作发生在UI线程之外的另一个线程上。因为SurfaceView在常规视图系统之外，所以无法像常规试图一样移动、缩放或旋转一个SurfaceView。TextureView是Android4.0引入的，除了与SurfaceView一样在单独线程绘制外，还可以像常规视图一样被改变。
 
* (2) 使用RenderJavascript

    RenderScript是Adnroid3.0引进的用来在Android上写高性能代码的一种语言，语法给予C语言的C99标准，他的结构是独立的，所以不需要为不同的CPU或者GPU定制代码代码。
 
* (3) 使用OpenGL绘图

    Android支持使用OpenGLAPI的高性能绘图，这是Android可用的最高级的绘图机制，在游戏类对性能要求较高的应用中得到广泛使用。Android4.3最大的改变，就是支持OpenGL ES3.0。相比2.0，3.0有更多的缓冲区对象、增加了新的着色语言、增加多纹理支持等等，将为Android游戏带来更出色的视觉体验。
 
* (4) 尽量为所有分辨率创建资源

    减少不必要的硬件缩放，这会降低UI的绘制速度，可借助Android asset studio
    
    
参考资料:

[性能优化之布局优化](http://www.trinea.cn/android/layout-performance/)

整理人: showdy