package com.henry.ollama.deepseekr1.service;

import com.henry.ollama.deepseekr1.record.OllamaRequest;
import com.henry.ollama.deepseekr1.record.OllamaResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {

    private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";
    private final RestTemplate restTemplate;

    public OllamaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .build();
    }

    public String generateResponse(String prompt) {
        try {
            OllamaRequest request = new OllamaRequest("deepseek-r1:1.5b", prompt, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<OllamaResponse> response = restTemplate.exchange(
                    OLLAMA_API_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    OllamaResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().response() != null
                        ? response.getBody().response()
                        : "Received empty response from model";
            }
            return "Ollama API returned status: " + response.getStatusCode();
        } catch (RestClientException e) {
            return "Error communicating with Ollama: " + e.getMessage();
        }
    }
}