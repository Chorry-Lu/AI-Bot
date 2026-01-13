package com.example.aibot.controller.dto;

import java.util.Map;

/**
 * 错误响应DTO
 */
public class ErrorResponse {
    
    private int code;
    private String message;
    private Map<String, String> details;
    
    public ErrorResponse() {
    }
    
    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public ErrorResponse(int code, String message, Map<String, String> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Map<String, String> getDetails() {
        return details;
    }
    
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
