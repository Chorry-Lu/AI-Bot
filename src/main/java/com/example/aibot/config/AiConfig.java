package com.example.aibot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI 配置类
 * 使用Spring Boot自动配置，通过@Qualifier注入不同的ChatModel
 * 注意：由于Spring AI版本差异，允许bean定义覆盖以避免冲突
 */
@Configuration
public class AiConfig {

    /**
     * DashScope ChatClient Bean
     * Spring Boot会自动创建DashScopeChatModel，我们只需要注入并创建ChatClient
     */
    @Bean("dashscopeChatClient")
    @ConditionalOnBean(name = "dashScopeChatModel")
    public ChatClient dashScopeChatClient(@Qualifier("dashScopeChatModel") ChatModel dashScopeChatModel) {
        return ChatClient.builder(dashScopeChatModel)
                .defaultSystem("你是一个有用的AI助手。")
                .build();
    }
    
    /**
     * OpenAI ChatClient Bean
     * Spring Boot会自动创建OpenAiChatModel，我们只需要注入并创建ChatClient
     */
    @Bean("openaiChatClient")
    @ConditionalOnBean(name = "openAiChatModel")
    public ChatClient openAiChatClient(@Qualifier("openAiChatModel") ChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("You are a helpful AI assistant.")
                .build();
    }
}