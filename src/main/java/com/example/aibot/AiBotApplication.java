package com.example.aibot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * AI Bot 应用主类
 * 排除冲突的自动配置类以解决Bean冲突问题
 */
@SpringBootApplication(exclude = {
        // 排除冲突的ChatClient自动配置类
        // 使用我们自己的配置来创建ChatClient
        org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration.class
})
public class AiBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiBotApplication.class, args);
    }

}