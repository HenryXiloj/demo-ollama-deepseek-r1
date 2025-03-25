package com.henry.ollama.record;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OllamaRequest(
        String model,
        String prompt,
        boolean stream
) {}