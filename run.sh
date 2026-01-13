#!/bin/bash

echo "启动 AI Bot 应用..."

# 检查是否已设置API密钥
if [ -z "$DASHSCOPE_API_KEY" ]; then
    echo "警告: DASHSCOPE_API_KEY 环境变量未设置，将使用默认值 'your-api-key-here'"
    echo "请设置环境变量: export DASHSCOPE_API_KEY='你的API密钥'"
fi

# 编译并运行应用
./mvnw spring-boot:run

echo "应用已启动，请访问 http://localhost:8080 查看"