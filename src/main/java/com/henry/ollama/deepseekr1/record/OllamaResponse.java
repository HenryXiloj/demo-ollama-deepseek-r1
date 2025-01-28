package com.henry.ollama.deepseekr1.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OllamaResponse(
        String model,
        String response,
        String created_at,
        boolean done
) {}