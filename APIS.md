# API 接口文档

## 1. 简单聊天接口

### 请求信息
- **URL**: `/api/chat/simple`
- **方法**: `POST`
- **内容类型**: `application/json`

### 请求参数
```json
{
  "message": "用户输入的消息内容"
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
  -d '{"message":"你好"}'
```

---

## 2. 流式聊天接口

### 请求信息
- **URL**: `/api/chat/stream`
- **方法**: `GET`
- **参数**: `message` (查询参数)

### 请求参数
- `message`: 用户输入的消息内容

### 返回结果
```json
"AI助手的回复内容"
```

### 示例请求
```bash
curl "http://localhost:8080/api/chat/stream?message=你好"
```

---

## 3. 健康检查接口

### 请求信息
- **URL**: `/health`
- **方法**: `GET`

### 返回结果
```json
"AI Bot服务正常运行"
```

---

## 4. 主页接口

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
```

### 支持的模型
- qwen-max
- qwen-plus
- qwen-turbo
- 以及其他阿里云通义千问支持的模型