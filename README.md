# AI-Bot
个人AI助手 - 基于Spring Boot 3.5和Spring AI Alibaba

## 项目简介

这是一个基于Spring Boot 3.5构建的个人AI助手项目，集成了Spring AI Alibaba模块，提供与大语言模型交互的能力。

## 功能特性

- 简单的聊天接口
- 支持通义千问（Qwen）模型
- RESTful API设计

## 快速开始

1. 克隆项目
2. 配置application.yml中的API密钥
3. 运行 `mvn spring-boot:run`
4. 访问 `/api/chat/simple` 发送消息

## API 接口

- POST /api/chat/simple - 发送消息并获得回复
- GET /api/chat/stream - 流式聊天（同步方式）

## 配置

在 `application.yml` 中配置通义千问的API密钥和其他参数。
