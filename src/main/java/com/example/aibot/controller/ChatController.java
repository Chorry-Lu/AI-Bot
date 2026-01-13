package com.example.aibot.controller;

import com.example.aibot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/simple")
    public String chat(@RequestBody Map<String, String> request) {
        String userInput = request.get("message");
        return chatService.sendMessage(userInput);
    }

    @GetMapping("/stream")
    public String streamChat(@RequestParam String message) {
        return chatService.sendMessage(message);
    }
}