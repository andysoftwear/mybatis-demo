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

