# 使用说明

## 项目安装与运行

### 1. 环境准备

#### 安装Java 17+
确保系统已安装Java 17或更高版本：

```bash
java -version
```

#### 获取项目代码
```bash
git clone <your-repo-url>
cd AI-Bot
```

### 2. 配置API密钥

在使用前需要配置各个大模型的API密钥：

#### 方式一：环境变量设置
```bash
# Linux/Mac
export DASHSCOPE_API_KEY="your-dashscope-api-key-here"
export OPENAI_API_KEY="your-openai-api-key-here"

# Windows
set DASHSCOPE_API_KEY=your-dashscope-api-key-here
set OPENAI_API_KEY=your-openai-api-key-here
```

#### 方式二：修改配置文件
编辑 `src/main/resources/application.yml` 文件：

```yaml
spring:
  ai:
    dashscope:
      api-key: "your-dashscope-api-key-here"  # 替换为实际API密钥
      model: qwen-max
    openai:
      api-key: "your-openai-api-key-here"  # 替换为实际API密钥
      chat:
        options:
          model: gpt-3.5-turbo
```

### 3. 运行项目

#### 方式一：使用Maven
```bash
mvn spring-boot:run
```

#### 方式二：使用启动脚本
```bash
# Linux/Mac
chmod +x run.sh
./run.sh

# Windows
run.bat
```

## 项目构建

### 打包为可执行JAR
```bash
mvn clean package
```

生成的JAR文件位于 `target/ai-bot-0.0.1-SNAPSHOT.jar`，可以通过以下命令运行：

```bash
java -jar target/ai-bot-0.0.1-SNAPSHOT.jar
```

## API使用示例

### 1. 简单聊天接口

发送POST请求到 `/api/chat/simple`，可选择模型：

使用DashScope模型：
```bash
curl -X POST http://localhost:8080/api/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message":"你好，今天天气怎么样？","modelType":"DASHSCOPE"}'
```

使用OpenAI模型：
```bash
curl -X POST http://localhost:8080/api/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message":"你好，今天天气怎么样？","modelType":"OPENAI"}'
```

### 2. 流式聊天接口

发送GET请求到 `/api/chat/stream`，可选择模型：

使用DashScope模型：
```bash
curl "http://localhost:8080/api/chat/stream?message=你好，介绍一下你自己&modelType=DASHSCOPE"
```

使用OpenAI模型：
```bash
curl "http://localhost:8080/api/chat/stream?message=你好，介绍一下你自己&modelType=OPENAI"
```

### 3. 获取支持的模型列表

```bash
curl "http://localhost:8080/api/models/list"
```

### 3. 健康检查

检查服务状态：

```bash
curl http://localhost:8080/health
```

## 模型配置

### 支持的模型类型

在 `application.yml` 中可以配置不同的模型：

```yaml
spring:
  ai:
    dashscope:
      model: qwen-max      # 选择以下任一模型: qwen-max, qwen-plus, qwen-turbo
    openai:
      chat:
        options:
          model: gpt-3.5-turbo  # 选择以下任一模型: gpt-3.5-turbo, gpt-4, gpt-4-turbo
```

可用模型：
- **DashScope (通义千问)**:
  - `qwen-max` - 最强性能，适合复杂任务
  - `qwen-plus` - 平衡性能与成本
  - `qwen-turbo` - 快速响应，适合简单任务
- **OpenAI (GPT系列)**:
  - `gpt-3.5-turbo` - 快速经济的模型
  - `gpt-4` - 更强大的推理能力
  - `gpt-4-turbo` - 最新的GPT-4 Turbo模型

### 其他参数配置

```yaml
spring:
  ai:
    dashscope:
      options:
        temperature: 0.7   # 控制输出随机性，范围0.0-1.0
        top-p: 0.8         # 控制输出多样性
    openai:
      chat:
        options:
          temperature: 0.7  # 控制输出随机性
          max-tokens: 1000    # 最大生成token数
```

## 故障排除

### 1. API密钥错误
如果收到认证错误，请检查：
- API密钥是否正确
- API密钥是否过期
- 是否有足够的调用额度
- 是否为正确的模型设置了对应的API密钥

### 2. 连接超时
如果遇到连接问题：
- 检查网络连接
- 确认API端点是否正确
- 检查防火墙设置
- 确保能够访问对应模型提供商的服务

### 3. 依赖下载失败
如果Maven依赖下载失败：
- 检查网络连接
- 确认Maven仓库配置
- 尝试使用国内镜像源

### 4. 模型选择错误
如果遇到模型选择问题：
- 检查请求中modelType参数是否正确
- 确认对应的API密钥已正确配置
- 验证模型名称是否被当前提供商支持

## 开发扩展

### 添加新功能
1. 在 `controller` 包中创建新的控制器
2. 在 `service` 包中创建相应的服务类
3. 如需AI功能，在 `config` 中添加相应配置

### 配置优化
可根据需要调整AI模型参数以获得更好的性能和效果。