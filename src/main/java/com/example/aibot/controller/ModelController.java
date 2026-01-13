package com.example.aibot.controller;

import com.example.aibot.enums.ModelType;
import com.example.aibot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 模型管理控制器
 */
@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ChatService chatService;

    @Autowired
    public ModelController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 获取支持的模型列表
     * 
     * @return 支持的模型信息
     */
    @GetMapping("/list")
    public Map<String, Object> getSupportedModels() {
        Set<ModelType> models = chatService.getSupportedModels();
        Map<String, Object> result = new HashMap<>();
        result.put("models", models.stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
        result.put("count", models.size());
        result.put("default", ModelType.DASHSCOPE.name());
        return result;
    }
}