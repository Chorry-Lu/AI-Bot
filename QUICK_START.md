# 快速启动指南

## 前置要求

- JDK 17 或更高版本
- Maven 3.6+ 
- OpenAI API Key（可选）
- DashScope API Key（可选，至少需要一个）

## 快速开始

### 1. 配置 API 密钥

#### 方式一：环境变量（推荐）

```bash
export DASHSCOPE_API_KEY=your-dashscope-api-key
export OPENAI_API_KEY=your-openai-api-key
```

#### 方式二：修改配置文件

编辑 `src/main/resources/application.yml`，替换 API 密钥：

```yaml
spring:
  ai:
    dashscope:
      api-key: your-dashscope-api-key
    openai:
      api-key: your-openai-api-key
```

### 2. 编译项目

```bash
mvn clean install
```

### 3. 运行项目

#### Linux/Mac:

```bash
./run.sh
```

或者：

```bash
mvn spring-boot:run
```

#### Windows:

```bash
run.bat
```

或者：

```bash
mvn spring-boot:run
```

### 4. 测试 API

#### 使用 DashScope 模型：

```bash
curl -X POST http://localhost:8080/api/chat/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "你好，介绍一下你自己",
    "modelType": "DASHSCOPE"
  }'
```

#### 使用 OpenAI 模型：

```bash
curl -X POST http://localhost:8080/api/chat/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "Hello, introduce yourself",
    "modelType": "OPENAI"
  }'
```

#### 获取支持的模型列表：

```bash
curl http://localhost:8080/api/models/list
```

## 常见问题

### 1. 依赖下载失败

如果遇到依赖下载问题，可以配置 Maven 镜像：

编辑 `~/.m2/settings.xml`：

```xml
<mirrors>
  <mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

### 2. API 密钥错误

确保：
- API 密钥正确
- API 密钥有足够的额度
- 网络可以访问对应的 API 服务

### 3. 端口被占用

修改 `application.yml` 中的端口：

```yaml
server:
  port: 8081
```

## 下一步

- 查看 [OPTIMIZATION.md](./OPTIMIZATION.md) 了解优化详情
- 查看 [USAGE.md](./USAGE.md) 了解详细使用说明
- 查看 [APIS.md](./APIS.md) 了解完整 API 文档
