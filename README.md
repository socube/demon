# Demo
本项目为学习新的技术 增加demo 例子


redis 客户端连接 redis 和 zookeeper 实现分布式锁
netty 实现 时间服务器
mq 分布式消息推送
webSocket 客户端通信消息聊天
dubbo demon

storm
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