package com.example.aibot.service;

import com.example.aibot.enums.ModelType;
import com.example.aibot.service.impl.DashScopeModelService;
import com.example.aibot.service.impl.OpenAIModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 聊天服务
 * 统一管理不同模型的调用
 */
@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    
    private final Map<ModelType, ModelService> modelServices;

    public ChatService(DashScopeModelService dashScopeModelService, 
                      OpenAIModelService openAIModelService) {
        this.modelServices = new HashMap<>();
        this.modelServices.put(ModelType.DASHSCOPE, dashScopeModelService);
        this.modelServices.put(ModelType.OPENAI, openAIModelService);
        logger.info("ChatService初始化完成，支持的模型: {}", modelServices.keySet());
    }

    /**
     * 发送消息到指定模型
     * 
     * @param message 用户消息
     * @param modelType 模型类型
     * @return AI响应
     */
    public String sendMessage(String message, ModelType modelType) {
        if (!StringUtils.hasText(message)) {
            throw new IllegalArgumentException("消息内容不能为空");
        }
        
        ModelService modelService = modelServices.get(modelType);
        if (modelService == null) {
            throw new IllegalArgumentException("不支持的模型类型: " + modelType + 
                    "，支持的模型: " + modelServices.keySet());
        }
        
        logger.debug("使用模型 {} 处理消息", modelType);
        return modelService.sendMessage(message);
    }
    
    /**
     * 使用默认模型发送消息（DashScope）
     * 
     * @param message 用户消息
     * @return AI响应
     */
    public String sendMessage(String message) {
        return sendMessage(message, ModelType.DASHSCOPE);
    }
    
    /**
     * 获取支持的模型列表
     * 
     * @return 支持的模型类型集合
     */
    public java.util.Set<ModelType> getSupportedModels() {
        return modelServices.keySet();
    }
}