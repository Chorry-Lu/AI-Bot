package com.example.aibot.controller;

import com.example.aibot.controller.dto.ChatRequest;
import com.example.aibot.controller.dto.ChatResponse;
import com.example.aibot.enums.ModelType;
import com.example.aibot.service.ChatService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天控制器
 * 提供统一的聊天API接口，支持OpenAI和DashScope模型
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 标准聊天接口（推荐使用）
     * 
     * @param request 聊天请求，包含消息内容和模型类型
     * @return 聊天响应
     */
    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, 
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        logger.info("收到聊天请求 - 模型: {}, 消息长度: {}", 
                request.getModelType(), request.getMessage().length());
        String response = chatService.sendMessage(request.getMessage(), request.getModelType());
        return new ChatResponse(response, request.getModelType());
    }

    /**
     * 简单聊天接口（兼容旧版本）
     * 
     * @param message 用户消息
     * @param modelType 模型类型（可选，默认为DASHSCOPE）
     * @return 聊天响应
     */
    @GetMapping("/simple")
    public ChatResponse simpleChat(
            @RequestParam String message,
            @RequestParam(required = false, defaultValue = "DASHSCOPE") String modelType) {
        try {
            ModelType modelTypeEnum = ModelType.valueOf(modelType.toUpperCase());
            String response = chatService.sendMessage(message, modelTypeEnum);
            return new ChatResponse(response, modelTypeEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("不支持的模型类型: " + modelType + 
                    "，支持的模型: DASHSCOPE, OPENAI");
        }
    }
    
    /**
     * 使用默认模型（DashScope）的聊天接口
     * 
     * @param request 聊天请求
     * @return 聊天响应
     */
    @PostMapping(value = "/default", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatResponse chatWithDefaultModel(@Valid @RequestBody ChatRequest request) {
        logger.info("使用默认模型聊天 - 消息长度: {}", request.getMessage().length());
        String response = chatService.sendMessage(request.getMessage());
        return new ChatResponse(response, ModelType.DASHSCOPE);
    }
}