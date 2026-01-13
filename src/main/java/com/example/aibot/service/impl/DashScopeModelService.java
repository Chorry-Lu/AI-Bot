package com.example.aibot.service.impl;

import com.example.aibot.enums.ModelType;
import com.example.aibot.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * DashScope (阿里云通义千问) 模型服务实现
 */
@Service
@Qualifier("dashscope")
public class DashScopeModelService implements ModelService {

    private static final Logger logger = LoggerFactory.getLogger(DashScopeModelService.class);
    
    private final ChatClient chatClient;

    public DashScopeModelService(@Qualifier("dashscopeChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String sendMessage(String message) {
        if (!StringUtils.hasText(message)) {
            throw new IllegalArgumentException("消息内容不能为空");
        }
        
        try {
            logger.debug("发送消息到DashScope模型: {}", message);
            String response = chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
            logger.debug("DashScope模型响应: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("调用DashScope模型失败", e);
            throw new RuntimeException("调用DashScope模型失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getModelType() {
        return ModelType.DASHSCOPE.name();
    }
}