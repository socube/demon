# Demo
本项目为学习新的技术 增加demo 例子


redis 客户端连接 redis 和 zookeeper 实现分布式锁
####ZooKeeper简介 ZooKeeper的目的，为分布式应用提供分布式的协同服务。

zk提供了一组原语，分布式系统可以根据这组原语构建更高级别的服务：比如同步、配置维护、组和命名。

#####文件系统和通知机制 ZooKeeper的ZNode类似于文件系统，只不过每个节点还可以额外存放数据。

当节点发生变化时（创建、删除、数据变更），可以通知各个客户端。

因此ZooKeeper可以应用的场景有：

统一配置：把配置放在ZooKeeper的节点中维护，当配置变更时，客户端可以收到变更的通知，并应用最新的配置。
集群管理：集群中的节点，创建ephemeral的节点，一旦断开连接，ephemeral的节点会消失，其它的集群机器可以收到消息。
分布式锁：多个客户端发起节点创建操作，只有一个客户端创建成功，从而获得锁。
构建正确的协同服务非常的困难，特别是那些资源竞争、死锁等情况，通过zk，分布式系统不需要从新开始构建协同服务。

zk提供的原语包含：

create
delete
exists
get data
set data
get chiledren
sync
####设计目标

足够简单：结构类似文件系统的（结点可以带数据，但是不能太大）
冗余：保证可靠性
顺序
快：基于内存的操作
####保证

ZooKeeper保证了：

顺序一致性：客户端的操作会被按照顺序执行
原子性：操作要不失败要不成功
可靠性：一旦写入成功，数据就会被保持，直到下次覆盖。
实时性
单一系统镜像(single system image)：不管连接到zk集群的那台机器，客户端看到的视图都是一致的
####实现 ZooKeeper的组件包含：(无图)

其中，Replicated Database是个内存数据库，保存了所有数据。

更新会被写到磁盘，以便恢复。写也会被先序列化到磁盘后，在应用到内存数据库中。

读的时候，会从各自server的内存数据库中读数据，写则是通过一致性协议完成（leader/follwer）的。

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

对于Thrift服务化的改造，主要是客户端，可以从如下几个方面进行(http://www.cnblogs.com/wxd0108/p/7026522.html)：

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

#spring Cloud