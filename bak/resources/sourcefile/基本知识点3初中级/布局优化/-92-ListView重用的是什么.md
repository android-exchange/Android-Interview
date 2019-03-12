####  必知：
1. convertView重用
2. ViewHolder的子View复用
3. 缓存数据复用
#### convertView重用
###### 先讲下ListView的原理：ListView中的每一个Item显示都需要Adapter调用一次getView()的方法，这个方法会传入一个convertView的参数，这个方法返回的View就是这个Item显示的View。如果当Item的数量足够大，再为每一个Item都创建一个View对象，必将占用很多内存空间，即创建View对象（mInflater.inflate(R.layout.lv_item, null);从xml中生成View，这是属于IO操作）是耗时操作，所以必将影响性能。Android提供了一个叫做Recycler(反复循环)的构件，就是当ListView的Item从滚出屏幕视角之外，对应Item的View会被缓存到Recycler中，相应的会从生成一个Item，而此时调用的getView中的convertView参数就是滚出屏幕的缓存Item的View，所以说如果能重用这个convertView，就会大大改善性能。
#### 使用ViewHolder重用
######  我们都知道在getView()方法中的操作是这样的：先从xml中创建view对象（inflate操作，我们采用了重用convertView方法优化），然后在这个view去findViewById，找到每一个item的子View的控件对象，如：ImageView、TextView等。这里的findViewById操作是一个树查找过程，也是一个耗时的操作，所以这里也需要优化，就是使用ViewHolder，把每一个item的子View控件对象都放在Holder中，当第一次创建convertView对象时，便把这些item的子View控件对象findViewById实例化出来并保存到ViewHolder对象中。然后用convertView的setTag将viewHolder对象设置到Tag中， 当以后加载ListView的item时便可以直接从Tag中取出复用ViewHolder对象中的，不需要再findViewById找item的子控件对象了。这样便大大提高了性能。
####  必会：
+ convertView重用
+ 使用ViewHolder重用
#### [Android性能优化之Listview（ViewHolder重用机制）](https://blog.csdn.net/u010687392/article/details/45620541)