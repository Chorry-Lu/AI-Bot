# API 接口文档

## 1. 简单聊天接口

### 请求信息
- **URL**: `/api/chat/simple`
- **方法**: `POST`
- **内容类型**: `application/json`

### 请求参数
```json
{
  "message": "用户输入的消息内容",
  "modelType": "DASHSCOPE" // 可选，默认为DASHSCOPE，支持DASHSCOPE或OPENAI
}
```

### 返回结果
```json
"AI助手的回复内容"
```

### 示例请求
```bash
curl -X POST http://localhost:8080/api/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message":"你好","modelType":"DASHSCOPE"}'
```

```bash
curl -X POST http://localhost:8080/api/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message":"你好","modelType":"OPENAI"}'
```

---

## 2. 流式聊天接口

### 请求信息
- **URL**: `/api/chat/stream`
- **方法**: `GET`
- **参数**: `message` (查询参数), `modelType` (可选查询参数)

### 请求参数
- `message`: 用户输入的消息内容
- `modelType`: 模型类型，可选值 DASHSCOPE 或 OPENAI，默认为 DASHSCOPE

### 返回结果
```json
"AI助手的回复内容"
```

### 示例请求
```bash
curl "http://localhost:8080/api/chat/stream?message=你好&modelType=DASHSCOPE"
```

```bash
curl "http://localhost:8080/api/chat/stream?message=你好&modelType=OPENAI"
```

---

## 3. 指定模型聊天接口

### 请求信息
- **URL**: `/api/chat/chat`
- **方法**: `POST`
- **内容类型**: `application/json`

### 请求参数
```json
{
  "message": "用户输入的消息内容",
  "modelType": "OPENAI" // 必须指定，支持DASHSCOPE或OPENAI
}
```

### 返回结果
```json
"AI助手的回复内容"
```

### 示例请求
```bash
curl -X POST http://localhost:8080/api/chat/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"你好","modelType":"OPENAI"}'
```

---

## 4. 获取支持的模型列表

### 请求信息
- **URL**: `/api/models/list`
- **方法**: `GET`

### 返回结果
```json
["DASHSCOPE", "OPENAI"]
```

### 示例请求
```bash
curl "http://localhost:8080/api/models/list"
```

---

## 5. 健康检查接口

### 请求信息
- **URL**: `/health`
- **方法**: `GET`

### 返回结果
```json
"AI Bot服务正常运行"
```

---

## 6. 主页接口

### 请求信息
- **URL**: `/`
- **方法**: `GET`

### 返回结果
```json
"欢迎使用AI Bot! 请访问 /api/chat/simple 发送消息."
```

---

## 配置说明

### 环境变量配置
- `DASHSCOPE_API_KEY`: 阿里云DashScope API密钥
- `OPENAI_API_KEY`: OpenAI API密钥

### application.yml 配置
```yaml
spring:
  ai:
    dashscope:
      api-key: ${DASHSCOPE_API_KEY:your-api-key-here}
      model: qwen-max
      options:
        temperature: 0.7
        top-p: 0.8
    openai:
      api-key: ${OPENAI_API_KEY:your-openai-api-key-here}
      chat:
        options:
          model: gpt-3.5-turbo
          temperature: 0.7
          max-tokens: 1000
```

### 支持的模型
- DASHSCOPE: 阿里云通义千问系列 (qwen-max, qwen-plus, qwen-turbo 等)
- OPENAI: OpenAI GPT系列 (gpt-3.5-turbo, gpt-4 等)