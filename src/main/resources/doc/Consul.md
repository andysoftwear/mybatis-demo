### 1，安装包获取：

```
 wget https://releases.hashicorp.com/consul/1.10.1/consul_1.10.1_linux_amd64.zip
```

### 2，解压包

```
unzip consul_1.7.2_linux_amd64.zip
```

### 3，拷贝到usr目录下

```
cd /usr
mkdir service
cd /home
mv consul /usr/service
```

### 4，查看 安装是否成功

```
./consul
```

### 5,  启动

```
-server 表示是server模式
　　-bootstrap-expect=3 表示是集群中有3台服务器 bootstrap该模式node可以指定自己作为leader ，如果是非leader可不加该参数
　　-data-dir=/tmp/consul 目录
　　-node=n2 该服务器节点名
　　-bind=127.0.0.1 节点绑定的ip
　　-ui 非必须 webui的路径 用web来管理consul
```

```
./consul agent -server -bootstrap-expect 1 -data-dir=/tmp/consul -node=n1 -bind=127.0.0.1 -client=0.0.0.0 -ui
```

```
# 查看集群成员
consul members

# 查看集群状态
consul info

# 帮助
consul agent -h
```

# zookper:

ZooKeeper是一个分布式的、开源的分布式应用程序协调服务，可以在分布 式环境中实现应用配置管理、统一命名服务、状态同步服务等功能。
ZooKeeper是一种为分布式应用所设计的高可用、高性能的开源协调服务，它提供了一项基本服务：分布式锁 服务。由于ZooKeeper开源的特性，在其分布式锁实现的基础上，又被摸索出了其它的功用，譬如：配置维 护、组服务、分布式消息队列等等。 ZooKeeper维护了一个类似文件系统的数据结构，其内部每个子目录都被 称作znode（目录节点），与文件系统一样，我们可以自由的增删改查znode。ZooKeeper集群适合搭建在奇数 台机器上。只要集群中半数以上主机处于存活，那么服务就是可用的。 ZooKeeper在配置文件中并没有指定 master和slave，但是，ZooKeeper在工作时，只有一个节点为leader，其余节点为follower，leader是通过内部 的选举机制临时产生的。

> ** ZooKeeper特点 **
> 1、顺序一致性：以zxid来保证事务的顺序性。
> 2、原子性：以zab保证原子操作，要么成功，要么失败。
> 3、单一视图：客户获取到的数据始终是一致的。
> 4、可靠：以版本实现"写入校验"，保证了数据写入的正确性。

**ZooKeeper有三种安装方式：单机模式 & 伪集群模式 & 集群模式 **
**单机模式：**ZooKeeper以单实例的形式运 行在一台服务器上，适合测试环境。
**伪集群模式：**在一台服务器上跑多个ZooKeeper实例。
**集群模式：** ZooKeeper运行在多台服务器上，适合生产环境。
所需[软件包](https://pan.baidu.com/s/1Yq_57_eizfTe2cAMIf85oQ)（提取码：mqtp ）

# **一、开始部署**

## **1、单机安装Zookeeper**

```html
#安装JDK环境
[root@zookeeper ~]# tar zxf jdk-8u211-linux-x64.tar.gz -C /usr/local/
[root@zookeeper ~]# vim /etc/profile            # 编辑Java变量
..........................
export JAVA_HOME=/usr/local/jdk1.8.0_211
export JRE_HOME=/usr/local/jdk1.8.0_211/jre
export CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
[root@zookeeper ~]# source /etc/profile           # 执行使配置生效
[root@zookeeper ~]# java -version                # 查看是否安装成功
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
1.2.3.4.5.6.7.8.9.10.11.12.13.
#安装zookeeper
[root@zookeeper ~]# tar zxf zookeeper-3.4.14.tar.gz -C /usr/local/
[root@zookeeper ~]# vim /etc/profile
export ZOOKEEPER_HOME=/usr/local/zookeeper-3.4.14
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$ZOOKEEPER_HOME/bin:$PATH                          # 加入Java配置的PATH中
[root@zookeeper ~]# source /etc/profile
[root@zookeeper ~]# cd /usr/local/zookeeper-3.4.14/conf/
[root@zookeeper conf]# cp zoo_sample.cfg zoo.cfg
[root@zookeeper conf]# mkdir -p /usr/local/zookeeper-3.4.14/data                           # 创建数据目录
[root@zookeeper conf]# sed -i "s/dataDir=\/tmp\/zookeeper/dataDir=\/usr\/local\/zookeeper-3.4.14\/data/g" zoo.cfg 
[root@zookeeper conf]# zkServer.sh start             # 启动服务
[root@zookeeper conf]# netstat -anput | grep 2181           # 确定端口在监听
tcp6       0      0 :::2181                 :::*                    LISTEN      4903/java   
```

**1）客户端命令操作**

```html
[root@zookeeper ~]# zkCli.sh                # 后面不加任何参数默认连接localhost本机的2181端口
Connecting to localhost:2181
[zk: localhost:2181(CONNECTED) 0] help         # 显示客户端支持的命令
[zk: localhost:2181(CONNECTED) 1] ls /          # 查看当前zk中所包含的内容
[zookeeper]
[zk: localhost:2181(CONNECTED) 2] ls2 /        # 查看当前zk中的内容及详情
[zookeeper]
cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x0
cversion = -1
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 1
[zk: localhost:2181(CONNECTED) 3] create /test1 neirong             # 创建一个节点
Created /test1
[zk: localhost:2181(CONNECTED) 4] ls /          # 可以看到已经多了一个test1
[zookeeper, test1]
[zk: localhost:2181(CONNECTED) 5] get /test1        # huo获取节点信息，需写绝对路径
neirong                            # 节点数据信息 
cZxid = 0x2                      # 节点创建时额zxid 
ctime = Sat Apr 04 16:15:30 CST 2020               # 节点创建的时间 
mZxid = 0x2
mtime = Sat Apr 04 16:15:30 CST 2020                    # 节点最近一次更新的时间 
pZxid = 0x2
cversion = 0                   # 子结点数据更新次数 
dataVersion = 0                 # 本节点数据更新次数 
aclVersion = 0                 # 节点ACL的更新次数 
ephemeralOwner = 0x0
dataLength = 7               # 节点数据长度 
numChildren = 0                # 子结点的数量 
[zk: localhost:2181(CONNECTED) 6] set /test1 "gengxin"         # 更新节点数据
[zk: localhost:2181(CONNECTED) 7] get /test1           # 可以看到已经更改为新的数据
gengxin
[zk: localhost:2181(CONNECTED) 8] history         # 列出zui最近所使用的命令
0 - help
1 - ls /
2 - ls2 /
3 - create /test1 neirong
4 - ls /
5 - get /test1
6 - set /test1 "gengxin"
7 - get /test1
8 - history
[zk: localhost:2181(CONNECTED) 9] delete /test1         # 删除节点，但是无法删除拥有子节点的 节点
[zk: localhost:2181(CONNECTED) 11] rmr /test1           # rmrk可以删除带有子节点的节点
```

**关于zoo.cfg配置参数说明可参考[官方文档](http://zookeeper.apache.org/doc/r3.4.13/zookeeperAdmin.html)**

## **2、zookeeper单机伪集群部署**

在一台主机上跑多个zk实例，每个zk实例对应一个独立的配置文件；但是每个配置文件的clientPort & dataDir & dataLogDir绝对不能相同，还需要在dataDir中创建myid文件来指定该dataDir对应的zk实例。
**环境如下：**
这里在一台物理服务器上，部署3个zk实例
![img](https://s4.51cto.com/images/blog/202004/04/2911e1655b4fa93eff6b4eda63353d82.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)
1)安装zookeeper

```html
#安装好JDK，可参考之前单机安装
[root@zookeeper ~]# java -version 
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
1.2.3.4.5.
#安装zookeeper
[root@zookeeper ~]# tar zxf zookeeper-3.4.14.tar.gz -C /usr/local/
[root@zookeeper ~]# vim /etc/profile
export ZOOKEEPER_HOME=/usr/local/zookeeper-3.4.14
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$ZOOKEEPER_HOME/bin:$PATH                          
[root@zookeeper ~]# source /etc/profile
1.2.3.4.5.6.
#创建数据目录
[root@zookeeper ~]# mkdir -p /usr/local/zookeeper-3.4.14/{data_0,data_1,data_2}
#创建myid文件，并填入ID值
[root@zookeeper ~]# echo 0 > /usr/local/zookeeper-3.4.14/data_0/myid
[root@zookeeper ~]# echo 1 > /usr/local/zookeeper-3.4.14/data_1/myid
[root@zookeeper ~]# echo 2 > /usr/local/zookeeper-3.4.14/data_2/myid
#创建事务日志目录，官方建立尽量给事务日志作单独的磁盘或挂载点，这会极大的提高zk性能
[root@zookeeper ~]# mkdir -p /usr/local/zookeeper-3.4.14/{logs_0,logs_1,logs_2}
#配置server0
[root@zookeeper ~]# cd /usr/local/zookeeper-3.4.14/conf/
[root@zookeeper conf]# cp zoo_sample.cfg zoo_0.cfg
[root@zookeeper conf]# egrep -v "^$|^#" zoo_0.cfg          # 修改配置文件为如下
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/usr/local/zookeeper-3.4.14/data_0/
clientPort=2180
dataLogDir=/usr/local/zookeeper-3.4.14/logs_0/
server.0=127.0.0.1:2287:3387 
server.1=127.0.0.1:2288:3388 
server.2=127.0.0.1:2289:3389 
#配置server1
[root@zookeeper conf]# cp zoo_0.cfg zoo_1.cfg           # 复制之前的配置文件，修改个别参数
[root@zookeeper conf]# vim zoo_1.cfg
dataDir=/usr/local/zookeeper-3.4.14/data_1/
clientPort=2181
dataLogDir=/usr/local/zookeeper-3.4.14/logs_1/
#配置server2
[root@zookeeper conf]# cp zoo_0.cfg zoo_2.cfg
[root@zookeeper conf]# vim zoo_2.cfg
dataDir=/usr/local/zookeeper-3.4.14/data_2/
clientPort=2182
dataLogDir=/usr/local/zookeeper-3.4.14/logs_2/
[root@zookeeper conf]# zkServer.sh start zoo_0.cfg             # 我这里是在conf目录下，所以后面直接接着配置文件，如果不在conf下，则需写全路径
#启动各实例
[root@zookeeper conf]# zkServer.sh start zoo_1.cfg 
[root@zookeeper conf]# zkServer.sh start zoo_2.cfg 
[root@zookeeper conf]# netstat -anput | grep java 
tcp6       0      0 :::2180                 :::*                    LISTEN      9251/java           
tcp6       0      0 :::2181                 :::*                    LISTEN      9291/java           
tcp6       0      0 :::2182                 :::*                    LISTEN      9334/java    
#列出JVM
[root@zookeeper conf]# jps
9377 Jps
9251 QuorumPeerMain
9334 QuorumPeerMain
9291 QuorumPeerMain
#各实例都启动之后就可以使用客户端进行连接了
[root@zookeeper conf]# zkCli.sh -server 127.0.0.1:2180     # 例
```

**关于多个server的配置说明： 这些server表单服务器的条目。列出组成ZooKeeper服务的服务器。当服务器启动 时，它通过在数据目录中查找文件myid来知道它是哪个服务器。该文件包含服务器号。 最后，注意每个服务器 名后面的两个端口号:“2287”和“3387”。对等点使用前一个端口连接到其他对等点。这样的连接是必要的，以便 对等点可以通信，例如，就更新的顺序达成一致。更具体地说，ZooKeeper服务器使用这个端口将追随者连接 到leader。当一个新的领导者出现时，追随者使用这个端口打开一个TCP连接到领导者。由于默认的领导人选举 也使用TCP，我们目前需要另一个端口的领导人选举。这是服务器条目中的第二个端口。
**

## **3、 ZooKeeper多机集群部署 **

为了获得可靠的zk服务，应该在多台服务器上部署多个zk，只要集群中大多数的zk服务启动了，那么总的zk服 务将是可用的。 在多台主机上搭建ZooKeeper集群的方式，与伪集群几乎是差不多的。
**环境如下：**
![img](https://s4.51cto.com/images/blog/202004/04/40ada9fccc93c41fd6b69d66d13e495a.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)
**三台服务器上都需要执行如下操作**

```html
#安装好JDK
[root@zookeeper01 ~]# java -version 
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
#安装好zookeeper
[root@zookeeper01 ~]# tar zxf zookeeper-3.4.14.tar.gz -C /usr/local/
[root@zookeeper01 ~]# vim /etc/profile
export ZOOKEEPER_HOME=/usr/local/zookeeper-3.4.14
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$ZOOKEEPER_HOME/bin:$PATH                          # 加入Java配置的PATH中
[root@zookeeper01 ~]# source /etc/profile
```

**server0配置**

```html
[root@zookeeper01 ~]# mkdir -p /usr/local/zookeeper-3.4.14/{data,logs}
[root@zookeeper01 ~]# echo 0 > /usr/local/zookeeper-3.4.14/data/myid
[root@zookeeper01 ~]# cd /usr/local/zookeeper-3.4.14/conf/
[root@zookeeper01 conf]# cp zoo_sample.cfg zoo.cfg
[root@zookeeper01 conf]# egrep -v "^$|^#" zoo.cfg          # 修改配置文件为如下
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/usr/local/zookeeper-3.4.14/data/
clientPort=2181
dataLogDir=/usr/local/zookeeper-3.4.14/logs
server.0=192.168.171.134:2288:3388
server.1=192.168.171.135:2288:3388
server.2=192.168.171.140:2288:3388
[root@zookeeper01 conf]# zkServer.sh start          # 启动实例
[root@zookeeper01 conf]# netstat -anput | grep java     # 确定端口在监听
tcp6       0      0 :::43542                :::*                    LISTEN      40355/java          
tcp6       0      0 192.168.171.134:3388    :::*                    LISTEN      40355/java          
tcp6       0      0 :::2181                 :::*                    LISTEN      40355/java        
```

**server1配置**

```html
[root@zookeeper02 ~]# mkdir -p /usr/local/zookeeper-3.4.14/{data,logs}
[root@zookeeper02 ~]# echo 1 > /usr/local/zookeeper-3.4.14/data/myid
[root@zookeeper02 ~]# cd /usr/local/zookeeper-3.4.14/conf/
[root@zookeeper02 conf]# scp root@192.168.171.134:/usr/local/zookeeper-3.4.14/conf/zoo.cfg ./
[root@zookeeper02 conf]# zkServer.sh start
[root@zookeeper02 conf]# netstat -anput | grep java  
tcp6       0      0 192.168.171.135:3388    :::*                    LISTEN      40608/java          
tcp6       0      0 :::2181                 :::*                    LISTEN      40608/java 
```

**server2配置**

```html
[root@zookeeper03 ~]# mkdir -p /usr/local/zookeeper-3.4.14/{data,logs}
[root@zookeeper03 ~]# echo 2 > /usr/local/zookeeper-3.4.14/data/myid
[root@zookeeper03 ~]# cd /usr/local/zookeeper-3.4.14/conf/
[root@zookeeper03 conf]# scp root@192.168.171.134:/usr/local/zookeeper-3.4.14/conf/zoo.cfg ./
[root@zookeeper03 conf]# zkServer.sh start
[root@zookeeper03 conf]# netstat -anput | grep java
tcp6       0      0 192.168.171.140:3388    :::*                    LISTEN      12769/java          
tcp6       0      0 :::2181                 :::*                    LISTEN      12769/java   
```

**查看各zk节点的状态**

```html
[root@zookeeper01 /]# zkServer.sh status 
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.14/bin/../conf/zoo.cfg
Mode: follower
[root@zookeeper02 /]# zkServer.sh status           # 02服务器为leader
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.14/bin/../conf/zoo.cfg
Mode: leader
[root@zookeeper03 /]# zkServer.sh status 
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.14/bin/../conf/zoo.cfg
Mode: follower
```

# Kafka:

https://blog.51cto.com/u_15265637/2894812

## Kafka是什么

Kafka最初是LinkedIn的内部内部基础设施系统。它被认为是一个流平台，在Kafka上可以发布和订阅流数据，并把它们保存起来、进行处理。但是我们在使用Kafka中，最多的就是将它作为一个消息系统使用，类似于ActiveMQ、RabbitMQ等。但是Kafka与这些传统的消息系统又有着许多的不同点，这些差异使它又不同于消息系统。

- Kafka是一个分布式系统，以集群(支持自由伸缩)的方式运行。(所以我们总称为分布式消息队列)
- Kafka可以用来存储数据，数据存储的时间长短由你自己定义(以容错持久化的方式存储)。并且只要数据还存储在Kafka中，你可以重复读取。
- 流式处理将数据处理的层次提升到了新高度。

而传统的消息系统，只会用来传递消息。 Kafka也可以看成是实时版的Hadoop(这也是设计Kafka的初衷之一)。Hadoop可以存储和定期处理大量的数据文件，而Kafka而可以存储和持续性的处理大型的数据流。Hadoop主要应用于数据分析上，而Kafka因其低延迟的特点更合适应用于核心业务上，业务事件发生时，Kafka能够针对这些事件及时做出响应。同时kafka也可以和ETL进行比较，因为它们擅长移动数据。

Kafka属于消息系统中的发布-订阅模式消息系统。消息发送者不会将消息直接发送到消息接受者里，而是将消息首先进行分类(topic)，然后将消息发布到消息系统中。消息接受者选择需要订阅的消息类型(topic)，然后就能够从消息系统中接收所订阅的消息了。

## Kafka中的消息和批次

Kafka中的数据单元称为消息，消息可以看成关系型数据库中的“数据行”或“记录”。消息是由键值对组成，其中键称之为元数据，是可选的。消息中的键值对是由字节数组组成，所以消息里的数据没有特别格式或含义(schema)。键主要用来分区写，比如通过键生成一个一致性散列值，然后使用散列值对分区取模，为消息选取分区，保证了相同类型间的消息都写入到了相同分区内。

为了提高消息写入效率，消息被分批次写入Kafka中。其次就是一组消息，这些消息属于同一topic下的同一分区。这样减少了网络开销，但是这需要在时间延迟和吞吐量之间作出平衡。批次的数据会被压缩，这样提升了数据的传输和存储能力，但同样做了更多的计算(这也是Kafka对CPU性能的要求点)。

## Kafka中的主题和分区

Kafka的消息通过主题(topic)进行分类，主题类似关系型数据库中的表，或者文件系统中的文件夹。一个主题可以被分为若干个分区(partition)，一个分区就是一个提交日志。消息以追加的方式写入分区，然后以先进先出的顺序读取。因为一个topic一般由多个partition组成，所以Kafka不能保证主题范围内的消息顺序，但是能够保证单个分区的消息顺序。如果要保证整个主题的有序性，就只能一个主题只有一个分区。Kafka通过分区来完成消息的冗余和伸缩性，分区可以分布在不同的服务器上，这样比单个服务器具有更高的性能。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/038ad101eedd48ff808748cbebb3fd1b.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

topic

每个分区都是一个有序、不可变的记录序列，新提交的记录会不断的追加到分区中。分区中的每条记录都会被分配一个连续的序列号叫做offset(偏移)，用于唯一标识分区中的每个记录。 在一个可配置的保留周期内(保存时间或保存大小)，Kafka集群会持久化所有发布的记录，无论这个记录是否被消费过。比如，我们将保存周期设置为2天，则记录在发布的两天内都可以重复被使用，当过了两天后，这条记录就会被丢弃以释放空间。Kafka的性能是与数据大小无关的常量，所以数据存储多长时间都没有问题。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/d93c4876bf369c1edb4ca588f574b4bc.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

partition

## 为什么要进行日志分区

- 使得每个topic日志不会太大，以便单个服务能够保存。
- 每个分区能够单独发布和消费，为并发消费topic提供一种可能。(也是最重要的)

## Kafka客户端

Kafka的客户端就是Kafka的系统用户，它们被分为两种基本类型：生产者和消费者。除了这些基础API之外，Kafka还提供了一些高级API，比如用于数据集成的Kafka Connect API，用于流式处理的Kafka Streams和用于管理Kafka的AdminClient。

- Producer API(http://kafka.apache.org/documentation.html#producerapi)：用于应用程序将数据流发布到一个或多个topic上。
- Consumer API(http://kafka.apache.org/documentation.html#consumerapi)：用于应用程序订阅一个或多个topic，并处理这些流记录。
- Streams API(http://kafka.apache.org/documentation/streams)：用于流式处理，消费来自一个或多个topic的输入流，并生成一个输出流到一个或多个topic上，输入输出都是kafka。
- Connector API(http://kafka.apache.org/documentation.html#connect)：用于Kafka topic与现有的应用程序或数据系统集成的API。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/3b1dfa2e3a76772958fbfb86e133275a.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

client

### Kafka生产者

Kafka中的生产者是用于写入消息的，一般一个消息会被写入到一个指定的主题内。生产者默认会将消息均衡地分布到主题的所有分区上。但是我们可以通过消息键或者分区器来将消息分类，将同类数据写入到同一个分区内。

### Kafka消费者

Kafka中的消费者是用于读取消息的，消费者会订阅一个或多个主题，并且按照消息的生成顺序读取它们。消费者会通过消息的“偏移量”来记录已经读取的位置，偏移量是一种元数据，它是一个不断自增的整数值。在消息写入到分区内时，Kafka会为该条消息生成所在分区内的唯一数值。消费者会把最后读取消息所在的偏移量保存到Zookeeper或Kafka中，如果消费者关闭或重启，则会重新读取该偏移量。 在Kafka中消费者是消费者群组的一部分，即一个群组可能有多个消费者共同读取一个主题。但是群组能够保证每个分区内的消息只能被消费者群组中的一个消费者消费。 消费者与分区之间的关系称为消费者对分区的所有权。当一个消费者挂掉后，同一群组的消费者可以接管失效消费者的工作。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/938fbef50fb880cbf087168d3810edb3.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

consumer group

### 消息的有序性

相比传统的消息系统，Kafka可以很好的保证有序性。 传统消息队列在服务器上保存有序消息，但是当多个Consumer消费队列中的数据时，由于消息被异步发送到不同的Consumer上，所以消息到达时可能已经失去了原来的顺序。通常这种情况如果需要强顺序读取，则只能有一个Consumer消费消息。这样也就失去了并发性。 Kafka由于使用了分区概念，可以在多个Consumer组并发的情况下提供较好的有序性和负载均衡。将每个分区只发给一个Consumer，这样一个分区就只被一个Consumer消费了，就可以顺序消费这个分区的消息了，由于一个topic有多个partition，所以可以使用多个Consumer消费，来实现负载均衡。但是Kafka只能保证一个分区的消息是有序的，如果需要topic所有消息都有序，那只能一个topic只有一个分区，也就只能有一个Consumer消费。

## Kafka集群

在多台机器上分别部署Kafka，就会组成一个Kafka集群。每台机器运行的Kafka服务称为broker，broker用于接收生产者消息，为消息设置偏移量，并且将消息保存到磁盘中。broker还为消费者提供读取消息服务，向消费者返回已经提交到磁盘中的消息。单个broker可以轻松处理数千分区以及每秒百万级消息量(依赖于具体机器性能)。

在broker集群中，会选举出一个leader，作为集群控制器的角色。leader控制器负责管理工作，比如将分区分配给broker和监控broker。在broker集群中，一个分区隶属于一个broker，这个broker称为分区的leader。一个分区可以分配到多个broker上，而这些其它broker上的分区数据是分区leader的复制数据，当分区leader挂掉后，其它broker可以接管领导权，但是这时候相关的消费者和生产者会连接到新的分区leader上。这种分区复制的机制为kafka提供消息冗余，保证了kafka的容错性和负载均衡。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/e8afedbf3442d1603ca5e37d687f6050.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

cluster

broker集群中的消息会持久化到磁盘上，这是kafka的一个重要特性。Kafka broker默认的消息保留策略有两种：保留到指定的时间和保留到消息到达一定的字节数。当达到上限时，就消息就会被删除。

Kafka集群搭建

对于Kafka集群来说，单个节点broker和多个节点的broker并没有任何区别。多broker节点只是在集群启动过程中，每个broker节点都需要启动。

## Kafka安装包下载(2.0.0版本)

下载路径： https://www.apache.org/dyn/closer.cgi?path=/kafka/2.0.0/kafka_2.11-2.0.0.tgz

```
tar -zxvf kafka_2.11-2.0.0.tgz
cd kafka_2.11-2.0.0
```

## 安装Zookeeper

Kafka是使用Zookeeper来保存集群元数据信息和消费者信息。虽然Kafka发行版已经自带了Zookeeper，可以通过脚本直接启动，但仍然建议安装一个完整版的Zookeeper。

![Kafka学习——Kafka介绍和搭建Kafka集群](https://s4.51cto.com/images/blog/202106/10/2e3d695768327799edc4a9bb2974a948.jpeg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

 

zk使用

Zookeeper部署：http://zookeeper.apache.org/doc/r3.3.3/zookeeperAdmin.html 注意： 

1)、在部署Zookeeper时，应该使用Linux监督(supervision)。因为Zookeeper遇到任何失败情况，都会快速失败并且退出线程，查看：http://zookeeper.apache.org/doc/r3.3.3/zookeeperAdmin.html#sc_supervision。 

2)、部署完Zookeeper应该配置一个cron来定时压缩zk的数据和日志，因为zk并不会做这些事。如果我们不设置cron，系统磁盘有可能会被zk打满。 https://www.cnblogs.com/fesh/p/3900253.htmlhttps://blog.csdn.net/qq_37716485/article/details/71786894

## Kafka配置

Kafka的配置文件在${KAFKA_HOME}/config/server.properties目录，我们只需要简单进行配置下：

```
broker.id=1 #当前broker在集群中的唯一标识，类似zk中的myid
log.dir=/opt/yangjianzhang/kafka/log #消息日志输出目录
zookeeper.connect=192.168.0.1:2181,192.168.0.2:2181,192.168.0.3:2281 #使用的zk集群
```

## 分发安装并启动

将Kafka安装包分发到其它机器上：

```
scp -r kafka_2.11-2.0.0 root@192.168.0.1:/opt/yangjianzhang/kafka/
#启动集群中每台机器的Kafka服务
bin/kafka-server-start.sh -daemon config/server.properties #需要指定启动配置文件
```

## 创建topic

```
#创建test topic，只有一个分区、三个副本
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic test

#查看topic
bin/kafka-topics.sh --list --zookeeper localhost:2181
test
```

## 查看创建的topic信息

```
[root@yjz01 kafka_2.11-2.0.0]# bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic demo
Topic:demo    PartitionCount:1    ReplicationFactor:3    Configs:
    Topic: demo    Partition: 0    Leader: 3    Replicas: 3,1,2    Isr: 3,1,2
```

第一行输出是对所有分区的一个描述，然后每个分区会有一行输出。 leader：当前分区所在的leader节点，负责处理消息的读和写，leader是从所有分区所在broker中随机选择出来的。 replicas：列出了所有副本节点(包含了leader节点)，无论该节点当前是否存活。 isr：分区副本所在节点，并且该节点正常运行服务。 当前分区leader是broker 3，我们kill 掉broker 3中的kafka服务，然后再看分区信息：

```
[root@yjz01 kafka_2.11-2.0.0]# bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic demo
Topic:demo    PartitionCount:1    ReplicationFactor:3    Configs:
    Topic: demo    Partition: 0    Leader: 1    Replicas: 3,1,2    Isr: 1,2
```

leader重新进行了选举，并且当前服务节点isr中已经把3剔除。

## 使用kafka命令行发送和消费消息

Kafka附带了一个命令行客户端，允许读取文件或标准输入发送到Kafka集群中，默认情况下每行作为一条消息发送。

```
[root@yjz01 kafka_2.11-2.0.0]# bin/kafka-console-producer.sh -broker-list localhost:2181 --topic demo
>hello world
hello kafka
```

使用命令行consumer读数据:

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:2181 --topic demo --from-beginning
>hello world
hello kafka
```
