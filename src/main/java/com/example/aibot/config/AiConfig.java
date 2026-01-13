package com.example.aibot.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String dashScopeApiKey;
    
    @Value("${spring.ai.dashscope.model:qwen-max}")
    private String modelName;
    
    @Bean
    public ChatModel chatModel() {
        DashScopeApi dashScopeApi = new DashScopeApi(dashScopeApiKey);
        return new DashScopeChatModel(dashScopeApi, modelName);
    }
    
    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
}