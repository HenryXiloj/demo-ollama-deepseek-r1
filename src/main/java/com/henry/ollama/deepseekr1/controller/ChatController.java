package com.henry.ollama.deepseekr1.controller;

import com.henry.ollama.deepseekr1.service.OllamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OllamaService ollamaService;

    public ChatController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return ResponseEntity.badRequest().body("Prompt cannot be empty");
        }
        String response = ollamaService.generateResponse(prompt);
        return ResponseEntity.ok(response);
    }
}
