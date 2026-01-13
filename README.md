# AI-Bot
个人AI助手 - 基于Spring Boot 3.5和多大模型支持

## 项目简介

这是一个基于Spring Boot 3.5构建的个人AI助手项目，集成了多种大语言模型，包括Spring AI Alibaba和OpenAI，提供与不同大语言模型交互的能力。

## 功能特性

- 简单的聊天接口
- 支持多种大模型（通义千问、OpenAI GPT）
- 可灵活切换模型
- RESTful API设计

## 快速开始

1. 克隆项目
2. 配置application.yml中的API密钥
3. 运行 `mvn spring-boot:run`
4. 访问 `/api/chat/simple` 发送消息

## 支持的模型

- **DASHSCOPE**: 阿里云通义千问系列 (qwen-max, qwen-plus, qwen-turbo 等)
- **OPENAI**: OpenAI GPT系列 (gpt-3.5-turbo, gpt-4 等)

## API 接口

- POST /api/chat/simple - 发送消息并获得回复（可指定模型）
- GET /api/chat/stream - 流式聊天（同步方式，可指定模型）
- POST /api/chat/chat - 指定模型聊天
- GET /api/models/list - 获取支持的模型列表

## 配置

在 `application.yml` 中配置不同模型的API密钥和其他参数。
