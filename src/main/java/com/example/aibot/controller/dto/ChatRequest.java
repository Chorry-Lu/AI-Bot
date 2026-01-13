package com.example.aibot.controller.dto;

import com.example.aibot.enums.ModelType;
import jakarta.validation.constraints.NotBlank;

/**
 * 聊天请求DTO
 */
public class ChatRequest {
    
    @NotBlank(message = "消息内容不能为空")
    private String message;
    
    private ModelType modelType = ModelType.DASHSCOPE;
    
    public ChatRequest() {
    }
    
    public ChatRequest(String message, ModelType modelType) {
        this.message = message;
        this.modelType = modelType;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public ModelType getModelType() {
        return modelType;
    }
    
    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }
}
