# 项目优化说明

## 优化概述

本次优化将项目升级为使用最新最成熟的 Spring AI 框架，同时支持 OpenAI 和阿里云 DashScope（通义千问）大模型。

## 主要改进

### 1. 依赖管理优化

- **使用 Spring AI BOM**：统一管理 Spring AI 相关依赖版本
- **更新到最新稳定版本**：Spring AI 1.0.0
- **添加 Validation 支持**：`spring-boot-starter-validation` 用于请求参数验证

### 2. 配置优化

- **使用 Spring Boot 自动配置**：减少手动配置代码，利用 Spring AI 的自动配置能力
- **标准化配置格式**：遵循 Spring AI 标准配置结构
- **环境变量支持**：支持通过环境变量配置 API 密钥
- **灵活的模型配置**：支持配置不同的模型和参数

### 3. 代码架构优化

#### 3.1 配置类 (`AiConfig`)
- 简化配置逻辑，使用 Spring Boot 自动配置
- 通过 `@Qualifier` 注入不同的 `ChatModel`
- 使用 `ChatClient.builder()` 创建更灵活的 ChatClient

#### 3.2 Service 层优化
- **统一的错误处理**：添加异常处理和日志记录
- **参数验证**：在 Service 层进行输入验证
- **日志记录**：添加详细的调试和错误日志
- **代码注释**：添加 JavaDoc 注释

#### 3.3 Controller 层优化
- **DTO 模式**：引入 `ChatRequest` 和 `ChatResponse` DTO 类
- **统一异常处理**：添加 `GlobalExceptionHandler` 全局异常处理器
- **参数验证**：使用 `@Valid` 注解进行请求验证
- **RESTful API 设计**：改进 API 设计，提供更清晰的接口
- **响应格式统一**：统一响应格式，包含时间戳和模型类型信息

### 4. 新增功能

- **全局异常处理**：统一的错误响应格式
- **请求/响应 DTO**：标准化的数据传输对象
- **参数验证**：自动验证请求参数
- **日志系统**：完整的日志记录
- **模型信息查询**：改进的模型列表接口

## 技术栈

- **Spring Boot**: 3.5.0
- **Spring AI**: 1.0.0-M6 (Milestone版本，稳定可用)
- **Spring AI OpenAI**: 1.0.0-M6
- **Spring AI Alibaba DashScope**: 1.0.0.2
- **Java**: 17

## API 接口

### 1. 标准聊天接口（推荐）

```bash
POST /api/chat/chat
Content-Type: application/json

{
  "message": "你好，介绍一下你自己",
  "modelType": "DASHSCOPE"
}
```

响应：
```json
{
  "content": "你好！我是...",
  "modelType": "DASHSCOPE",
  "timestamp": 1234567890123
}
```

### 2. 简单聊天接口

```bash
GET /api/chat/simple?message=你好&modelType=DASHSCOPE
```

### 3. 默认模型聊天接口

```bash
POST /api/chat/default
Content-Type: application/json

{
  "message": "你好"
}
```

### 4. 获取支持的模型列表

```bash
GET /api/models/list
```

响应：
```json
{
  "models": ["DASHSCOPE", "OPENAI"],
  "count": 2,
  "default": "DASHSCOPE"
}
```

## 配置说明

### application.yml 配置

```yaml
spring:
  ai:
    # DashScope (阿里云通义千问) 配置
    dashscope:
      api-key: ${DASHSCOPE_API_KEY:your-dashscope-api-key-here}
      chat:
        options:
          model: qwen-max
          temperature: 0.7
          top-p: 0.8
          max-tokens: 2000
    
    # OpenAI 配置
    openai:
      api-key: ${OPENAI_API_KEY:your-openai-api-key-here}
      base-url: ${OPENAI_BASE_URL:https://api.openai.com}
      chat:
        options:
          model: ${OPENAI_MODEL:gpt-3.5-turbo}
          temperature: 0.7
          max-tokens: 2000
```

### 环境变量配置

推荐使用环境变量配置 API 密钥：

```bash
export DASHSCOPE_API_KEY=your-dashscope-api-key
export OPENAI_API_KEY=your-openai-api-key
```

## 错误处理

项目现在包含统一的错误处理机制：

- **400 Bad Request**：参数验证失败或请求格式错误
- **500 Internal Server Error**：服务器内部错误或模型调用失败

错误响应格式：
```json
{
  "code": 400,
  "message": "请求参数验证失败",
  "details": {
    "message": "消息内容不能为空"
  }
}
```

## 日志配置

日志级别配置：
- `org.springframework.ai`: INFO
- `com.example.aibot`: DEBUG

## 迁移指南

### 从旧版本迁移

1. **更新依赖**：运行 `mvn clean install` 更新依赖
2. **更新配置**：按照新的 `application.yml` 格式更新配置
3. **更新 API 调用**：使用新的 DTO 格式调用 API
4. **设置环境变量**：配置 API 密钥环境变量

### 兼容性

- 保留了 `/api/chat/simple` 接口以保持向后兼容
- 旧的 Map 格式请求仍然支持，但推荐使用新的 DTO 格式

## 最佳实践

1. **使用环境变量**：不要将 API 密钥硬编码在配置文件中
2. **错误处理**：客户端应该处理各种 HTTP 状态码
3. **日志监控**：监控应用日志以发现潜在问题
4. **参数验证**：始终验证用户输入
5. **模型选择**：根据任务需求选择合适的模型

## 后续优化建议

1. **流式响应**：支持流式输出（Streaming）
2. **多轮对话**：支持对话历史管理
3. **速率限制**：添加 API 调用速率限制
4. **缓存机制**：添加响应缓存
5. **监控指标**：添加 Prometheus 指标
6. **健康检查**：添加模型健康检查端点
