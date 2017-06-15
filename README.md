# Demo
本项目为学习新的技术 增加demo 例子


redis 客户端连接 redis 和 zookeeper 实现分布式锁
netty 实现 时间服务器
mq 分布式消息推送
webSocket 客户端通信消息聊天
dubbo demon

#storm
(1)一个Storm的实例，入门级HelloWorld，简单的Storm应用程序。
(2)进阶级实例：DataOptTopology，加入了复杂的数据接入方式、处理以及落地。

WordCount实例过程：
(1)Spout中内置系列英文语句，随机发送;
(2)使用一个Bolt接收Spout发送的消息，进行归一化处理，即拆词，发射；
(3)按字段分组，接收上一个Bolt发送的单词，进行词频累加，并且排序，发射；
(4)实时输出词频结果。

DataOptTopology实例过程：
(1)从metaq中消费数据；
(2)对数据进行正则、范围普通字符串等复杂过滤；
(3)数据有不同的落地形式，包括mysql、metaq回写等；

项目使用方法：直接从github克隆一份，打包上传到Storm集群，即可。

#thrift 集成spring、zookeeper代码示例

对于Thrift服务化的改造，主要是客户端，可以从如下几个方面进行：

1.服务端的服务注册，客户端自动发现，无需手工修改配置，这里我们使用zookeeper，但由于zookeeper本身提供的客户端使用较为复杂，因此采用curator-recipes工具类进行处理服务的注册与发现。

2.客户端使用连接池对服务调用进行管理，提升性能，这里我们使用Apache Commons项目commons-pool，可以大大减少代码的复杂度。

3.关于Failover/LoadBalance，由于zookeeper的watcher，当服务端不可用是及时通知客户端，并移除不可用的服务节点，而LoadBalance有很多算法，这里我们采用随机加权方式，也是常有的负载算法，至于其他的算法介绍参考:常见的负载均衡的基本算法。

4.使thrift服务的注册和发现可以基于spring配置,可以提供很多的便利。

5.其他的改造如：

1）通过动态代理实现client和server端的交互细节透明化，让用户只需通过服务方提供的接口进行访问

2）Thrift通过两种方式调用服务Client和Iface

// *) Client API 调用

(EchoService.Client)client.echo("hello lilei"); ---(1)

// *) Service 接口 调用

(EchoService.Iface)service.echo("hello lilei"); ---(2)

Client API的方式, 不推荐, 我们推荐Service接口的方式(服务化)。