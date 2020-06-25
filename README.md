# 我的RPC框架

## 1. Zookeeper实现注册中心
- 服务的服务端服务启动的同时，将服务提供者信息（主机IP地址、服务端口、服务接口类路径）组成的znode路径写入Zookeeper中，注意写入的叶子节点为临时节点。这样就完成了服务的注册动作；
- 服务的消费端在发起服务调用之前，会先连接到Zookeeper，对服务提供者节点路径注册监听器，同时获取服务提供者信息到本地缓存，发起调用的时候，调用者会从服务提供者本地缓存列表中运用某种负载均衡策略选取某一个服务提供者，对该服务提供者发起调用，最终完成本次服务调用。这样就完成了服务发现动作；
- 若服务提供者集群中某台机器下线，该机器与注册中心zookeeper的连接会断掉，因为服务注册写入信息的叶子节点写入的znode是临时节点。同时出触发服务消费端对服务提供者路径的监听器，服务消费端收到被删除服务提供者节点信息之后，刷新本地服务提供者信息缓存，从缓存中删除已下线的服务提供者信息。若有新服务提供者上线，原理类似；

## 2. 关于neety半包/粘包问题。
### 产生原因：
- 应用程序写入的数据大于套接字缓冲区大小，这将会导致半包现象；
- 应用程序写入数据大小套接字缓冲区大小，网卡将应用多次写入的数据发送到网络上，这将会发生粘包现象；
- 当TPC报文长度减去TCP头部长度大于MSS（最大报文长度）的时候将发生半包；
- 接收方法未能及时读取套接字缓冲区数据，将发生粘包；
- 接收方法未能及时读取套接字缓冲区数据，将发生粘包。
### 解决方法：
  解决粘包/半包问题的本质能够区分完整的业务应用数据边界，能够按照边界完整地接受Netty传输的数据
- DelimiterBasedFrameDecoder+StringDecoder利用特殊分隔符作为消息的结束标志；eg: "@#"
- LineBasedFrameDecoder+StringDecoder组合以换行符作为消息的结束标志； eg: "\n"，"\r\n"
- FixedLengthFrameDecoder+StringDecoder按照固定长度获取消息。
### 自定义编解码器解决Netty粘包半包问题
  这里，我使用自定义编解码器来解决粘包半包问题，也就是使用Netty中的MessageToByteEncoder与ByteToMessageDecoder来自定义编解码器，大致原理：
  使用int数据类型来记录整个消息的字节数组长度，将该int数据作为消息的消息头一起传输，在服务端接收消息数据的时候，先接收4个字节的int数据类型数据，这个数据即为整个消息字节数组的长度，再接收剩余字节，直到接收的字节数组长度等于最先接收的int数据类型大小。

## 3. 负载均衡策略
  我主要通过在消费者端实现软负载均衡，主要实现了随机、加权随机、轮询、加权轮询、源地址hash
- 服务消费端在应用启动之初从服务注册中心获取服务提供者列表，缓存到服务调用端本地缓存；
- 服务消费端发起服务调用之前，先通过某种策略或者算法从服务提供者列表本地缓存中选择本次调用的目标机器，再发起服务调用，从而完成负载均衡的功能。

## Netty客户端发起调用，重点需要解决的问题是：
（1） 选择合适的序列化协议，解决Netty传输过程中出现的半包/粘包问题。
（2） 发挥长连接的优势，对Netty的Channel通道进行复用。通常通过连接池技术来达到复用效果。
（3） Netty是异步框架，客户端发起服务调用后同步等待获取调用结果。可以通过为每个请求新建一个阻塞队列，返回结果的时候，存入该阻塞队列，若再超时时间内返回结果值，则调用端将该返回结果从阻塞队列中取出返回给调用方，否则超时，返回null。
