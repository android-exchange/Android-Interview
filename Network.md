# 网络基础

- 描述一次网络请求的流程?
## <span id="quest_network_technological_process">HTTP网络请求的流程</span>
  1. 首先会通过 DNS 解析域名得到 IP 地址。
  2. 发送 HTTP 请求与头信息发送（HTTP 生成请求报文，TCP 把 HTTP 的报文按序号分割为多个报文段，进行3次握手）。
  3. 服务器进行应答，返回响应头内容和所需内容。
  4. 关闭 TCP 连接（4次挥手），看情况，还需要连接的话可以保持连接。
  
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


