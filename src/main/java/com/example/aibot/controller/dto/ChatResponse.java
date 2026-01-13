package com.example.aibot.controller.dto;

import com.example.aibot.enums.ModelType;

/**
 * 聊天响应DTO
 */
public class ChatResponse {
    
    private String content;
    private ModelType modelType;
    private long timestamp;
    
    public ChatResponse() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public ChatResponse(String content, ModelType modelType) {
        this.content = content;
        this.modelType = modelType;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public ModelType getModelType() {
        return modelType;
    }
    
    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
