package com.henry.ollama.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ollama")
public class OllamaProperties {
    private String endpoint;
    private String model;
    private Timeout timeout = new Timeout();

    @Data
    public static class Timeout {
        private int connect;
        private int read;
    }
}
