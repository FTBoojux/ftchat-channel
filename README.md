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