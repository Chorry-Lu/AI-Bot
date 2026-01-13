# 项目结构说明

## 项目概述
AI-Bot 是一个基于 Spring Boot 3.5 和 Spring AI Alibaba 的个人AI助手项目，集成了阿里云通义千问（Qwen）大语言模型。

## 目录结构

```
AI-Bot/
├── pom.xml                 # Maven 项目配置文件
├── README.md               # 项目说明文档
├── APIS.md                 # API 接口文档
├── PROJECT_STRUCTURE.md    # 本项目结构说明
├── run.sh                  # Linux/Mac 启动脚本
├── run.bat                 # Windows 启动脚本
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/aibot/
│   │   │       ├── AiBotApplication.java          # 主应用入口
│   │   │       ├── controller/
│   │   │       │   ├── ChatController.java        # 聊天接口控制器
│   │   │       │   └── HomeController.java        # 主页控制器
│   │   │       ├── service/
│   │   │       │   └── ChatService.java           # 聊天服务类
│   │   │       └── config/
│   │   │           └── AiConfig.java              # AI 配置类
│   │   └── resources/
│   │       └── application.yml                     # 应用配置文件
│   └── test/
│       └── java/
│           └── com/example/aibot/
│               └── AiBotApplicationTests.java     # 应用测试类
└── mvnw/mvnw.cmd           # Maven 包装器脚本
```

## 核心组件说明

### 1. 主应用入口 (AiBotApplication.java)
- Spring Boot 应用程序的主类
- 使用 @SpringBootApplication 注解启用自动配置

### 2. 控制器层 (controller/)
- **ChatController.java**: 处理聊天相关API请求
  - POST /api/chat/simple: 简单聊天接口
  - GET /api/chat/stream: 流式聊天接口（同步方式）
- **HomeController.java**: 处理主页和健康检查请求

### 3. 服务层 (service/)
- **ChatService.java**: 封装聊天业务逻辑
  - 提供 sendMessage 方法处理用户消息

### 4. 配置层 (config/)
- **AiConfig.java**: 配置 Spring AI Alibaba 相关 Bean
  - 初始化 DashScopeChatModel
  - 创建 ChatClient 实例

### 5. 配置文件 (application.yml)
- 配置 Spring AI Alibaba (DashScope) 参数
- 设置 API 密钥和模型名称

## 技术栈

- **Spring Boot 3.5.0**: 应用框架
- **Java 17**: 开发语言
- **Spring AI Alibaba**: AI 集成框架
- **Maven**: 项目构建工具
- **DashScope**: 阿里云大模型服务平台

## 依赖说明

### 核心依赖
- `org.springframework.boot:spring-boot-starter-web`: Web 应用支持
- `com.alibaba.cloud.ai:spring-ai-alibaba-starter-dashscope`: Spring AI Alibaba 集成

### 测试依赖
- `org.springframework.boot:spring-boot-starter-test`: 测试支持

## 运行说明

### 环境要求
- Java 17 或更高版本
- Maven 3.6.0 或更高版本

### 运行步骤
1. 设置环境变量 `DASHSCOPE_API_KEY` 为你的阿里云API密钥
2. 执行 `mvn spring-boot:run` 启动应用
3. 或使用提供的脚本 `./run.sh` (Linux/Mac) 或 `run.bat` (Windows)

### API 访问
- 访问 `http://localhost:8080` 查看主页
- 访问 `http://localhost:8080/health` 检查服务状态
- 使用 `/api/chat/simple` 和 `/api/chat/stream` 进行聊天交互