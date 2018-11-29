### 必知：
- hashing的概念
- HashMap中解决碰撞的方法
- equals()和hashCode()的应用，以及它们在HashMap中的重要性
- 不可变对象的好处
- HashMap多线程的条件竞争
- 重新调整HashMap的大小

- HashSet和HashMap的区别

*HashMap* |	*HashSet*
-|-
HashMap实现了Map接口 |	HashSet实现了Set接口
HashMap储存键值对 |	HashSet仅仅存储对象
使用put()方法将元素放入map中 |	使用add()方法将元素放入set中
HashMap中使用键对象来计算hashcode值 | 	HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false 
HashMap比较快，因为是使用唯一的键来获取对象	| HashSet较HashMap来说比较慢
### 必会：

### 参考资料
[HashMap和HashSet的区别料](http://www.importnew.com/6931.html)

[HashMap的工作原理](http://www.importnew.com/7099.html)