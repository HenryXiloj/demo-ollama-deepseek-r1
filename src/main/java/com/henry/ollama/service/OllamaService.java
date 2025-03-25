package com.henry.ollama.service;

import com.henry.ollama.config.OllamaProperties;
import com.henry.ollama.record.OllamaRequest;
import com.henry.ollama.record.OllamaResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class OllamaService {

    private final RestTemplate restTemplate;
    private final OllamaProperties properties;

    public OllamaService(OllamaProperties properties) {
        this.properties = properties;

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(properties.getTimeout().getConnect()))
                .setResponseTimeout(Timeout.ofMilliseconds(properties.getTimeout().getRead()))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        this.restTemplate = new RestTemplate(requestFactory);
    }

    public String generateResponse(String prompt) {
        try {
            OllamaRequest request = new OllamaRequest(properties.getModel(), prompt, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<OllamaResponse> response = restTemplate.exchange(
                    properties.getEndpoint(),
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
