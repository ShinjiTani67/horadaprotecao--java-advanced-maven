package br.com.horadaprotecao.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IAChatService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.completions-path}")
    private String completionsPath;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public String perguntarIA(String pergunta) {
        String url = baseUrl + completionsPath + "?key=" + apiKey;

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "user", "content", pergunta)
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) candidates.get(0).get("content");
                if (message != null && message.containsKey("parts")) {
                    List<Map<String, String>> parts = (List<Map<String, String>>) message.get("parts");
                    return parts.get(0).get("text");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao se comunicar com a IA.";
        }

        return "A IA não conseguiu responder.";
    }
}
