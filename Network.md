# 网络基础

- 描述一次网络请求的流程?
## <span id="quest_network_technological_process">HTTP网络请求的流程</span>
  1.客户端请求url，首先会通过DNS解析域名得到ip地址。
  2.发送HTTP请求与头信息发送（HTTP生成请求报文，TCP把HTTP的报文按序号分割为多个报文段，进行3次握手）
  3.服务器进行应答，返回响应头内容和所需内容。
  4.关闭TCP连接（4次挥手），看情况，还需要连接的话可以保持连接。
  
- TCP 中 3 次握手和 4 次挥手的过程?
- TCP 与 UDP 的区别及应用?
- HTTP 协议
- HTTP 1.0 与 2.0 的区别
- HTTP 报文结构
- HTTP 与 HTTPS 的区别以及如何实现安全性
- HTTPS 原理
- 谈谈你对 WebSocket 的理解
- WebSocket 与 socket 的区别
- 视频加密传输


