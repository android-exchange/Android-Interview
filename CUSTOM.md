

# 常见规范

## 排版规范

### 空格

- 英文、中文、数字之间保留一个空格

  例如：大家好，我是一名 Android 开发者，今年 18 岁。

- 英文标点之后保留一个空格

  例如：You are who you are. I am what I am.

  > You are who you are.一个空格I am what I am.一个空格

### 标点

- 中文使用全角符号，英文使用半角符号

  例如：逗号全角【，】逗号半角【,】

- 引号推荐使用弯引号「」『』

### 代码

- 代码必须使用 MD 语法添加代码样式

  例如：

  ```java
  class Test {
      public static void main(String[] args) {
          System.out.println("Hello World!");
      }
  }
  ```

- 代码尽量对齐，保证阅读的友好性

- 避免大段代码，建议分块说明

### 图片

- 图片命名方式：全部使用英文小写 + `_`

  例如：`android_binder.jpg`

- 图片使用方式 

  例如：

  1. image 标签

     ```html
     <img src="url" alt="图片描述"/>
     ```

  2. MD 语法

     ```
     ![图片描述][url]
     ```

- 图片下面尽量添加图片描述

  例如：

  <img src="https://github.com/jeanboydev/Android-ReadTheFuckingSourceCode/blob/master/resources/images/wechat/qrcode_for_gh_26eef6f9e7c1_258.jpg?raw=true" alt="公众号二维码" style="height:128px;width:128px;" />

  <center>图01 公众号二维码</center>

### 其他

### 其他

- 英文名词首字母尽量大写

  例如：Google、Android、Facebook

- 专有名词使用正确的大小写

  例如：GitHub、iOS、macOS、iPhone 6S Plus、MacBook Pro

- 首行无需缩进

- 自然段与自然段之间空一行

- 段落中的强调部分或者小部分代码使用 ``

  例如：我们可以在微信客户端 `发现`中都可以找到小程序，小程序可以使用 `javascript` 编写。

- URL 不要直接放链接。

  例如：这是百度的链接：[打开百度](http:www.xxx.com)

## GIT

- 提交描述尽可能描述修改/添加内容，可以英文或中文（不要中英文混合）

