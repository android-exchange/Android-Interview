####  必知：== 和 equals 都是比较，弄清楚 == 和 equals 分别比较的是什么（内存地址，还是内存地址中的内容），自然就知道了==和equals有什么区别。

####  必会：java对象都存在于内存中，要清楚我们要比较的到底是内存的地址，还是内存中的值。


先看几段代码：
```
String s1 = "AAAAA";
String s2 = "AAAAA";
System.out.println(s1 == s2);               // true
System.out.println(s1.equals(s2));          // true
```
```
String s3 = new String("BBBBB");
String s4 = new String("BBBBB");
System.out.println(s3 == s4);               // false
System.out.println(s3.equals(s4));          // true
```
```
Integer A = new Integer(20);
Integer B = new Integer(20);
System.out.println(A == B);                 // false
System.out.println(A.equals(B));            // true
```

我们再分别看看String类和Integer类中equals方法的源码：
```
public boolean equals(Object anObject) {
	if (this == anObject) {
		return true;
	}
	if (anObject instanceof String) {
		String anotherString = (String)anObject;
		int n = value.length;
		if (n == anotherString.value.length) {
			char v1[] = value;
			char v2[] = anotherString.value;
			int i = 0;
			while (n-- != 0) {
				if (v1[i] != v2[i])
					return false;
				i++;
			}
			return true;
		}
	}
	return false;
}
```
```
public boolean equals(Object obj) {
	if (obj instanceof Integer) {
		return value == ((Integer)obj).intValue();
	}
	return false;
}
```

从String类和Integer类的equals方法中，可以看到，equals的比较，最后还是运用!=或者==运算符来进行比较。**而且比较的，都是对象中的值。**

---

Java中所有的类，都是从Object继承而来，也就是说，Object是所有Java类的基类。

Object类中的函数并不多，equals方法就是其中的一个，看看equals在Object类中的源码：
```
public boolean equals(Object obj) {
    return (this == obj);
}
```
从Object源码中可以看到，equals方法最终调用的是\==进行比较的，那这里的\==比较的是什么呢？

**答：比较的是这个东西——java.lang.Object@15db9742。**

这是什么？

**答：这是Object类中toString方法的返回值。**

那再来看看Object类中toString方法的源码：
```
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

Object类toString方法返回的这一串东西java.lang.Object@15db9742是一个对象的类名和hash值，**这个hash值可以理解为内存地址**。

所以Objcet类中equals方法，比较的是两个内存地址，而不是内存地址中的值。

---

**结论：**

**equals和==都是进行比较操作，但是一个对象可比较的东西有很多，比如内存地址，内存地址中的值，但具体比较的是内存地址的值，还是内存地址中的值，就自己去看具体类的eaquals方法了。**
