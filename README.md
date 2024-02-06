## FTchat

这是一个用于个人学习使用的聊天应用，当前正在逐步设计和开发中。

本项目目前共分为三个模块：

- FTChat-channel(当前模块)：聊天通道模块，用于实现实时聊天通道的建立和维护
- [FTChat](https://github.com/FTBoojux/ftchat): 聊天客户端模块，用于提供聊天客户端的功能，基于Next.js和Tauri搭建
- [FTChat-backend](https://github.com/FTBoojux/ftchat_backend): 聊天服务端模块，用于提供聊天服务端的功能，基于Django搭建(用于学习Django框架和Python语言)

## 项目开发记录

### 2023-06-22

初步完成项目整体架构搭建，初步实现实时通信功能验证

### 2023-06-23

引入Cassandra依赖，并实现好友请求保存到Cassandra数据库中

### 2023-06-24

计划将所有设计mysql和csdr的操作全部转移到ftchat中进行，使用消息队列进行通信

完成RabbitMq的引入，好友请求现在改用消息队列进行通信

### 2023-06-28

修改了好友请求的逻辑

### 2023-09-12

增加从ftchat-backend获取身份token

### 2023-11-22

为netty server增加了destory生命周期，关闭时一起关闭netty进场

### 2023-12-06

将消息发送到会话中

### 2023-12-15

调整管理连接和用户id的方式，以支持同个用户的多设备登录

### 2024-02-06
消息的增加左右侧区分