package com.example.aibot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "欢迎使用AI Bot! 请访问 /api/chat/simple 发送消息.";
    }
    
    @GetMapping("/health")
    public String health() {
        return "AI Bot服务正常运行";
    }
}