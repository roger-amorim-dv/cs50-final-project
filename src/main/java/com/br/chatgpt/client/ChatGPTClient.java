package com.br.chatgpt.client;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.json.JSONObject;

@Component
public class ChatGPTClient {

    private final WebClient webClient;
    private final String apiKey;

    public ChatGPTClient(WebClient.Builder webClientBuilder, @Value("${openai.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com").build();
        this.apiKey = apiKey;
    }

    public Mono<String> sendQuestion(final String inputText) {
        String requestBody = "{"
                + "\"model\":\"gpt-3.5-turbo\","
                + "\"messages\":["
                +     "{\"role\":\"system\",\"content\":\"You are a helpful assistant.\"},"
                +     "{\"role\":\"user\",\"content\":\"" + inputText + "\"}"
                + "],"
                + "\"max_tokens\":1000"
                + "}";

        return webClient.post()
                .uri("/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray choices = jsonResponse.getJSONArray("choices");
                    if (!choices.isEmpty()) {
                        final JSONObject choice = choices.getJSONObject(0);
                        final JSONObject message = choice.getJSONObject("message");
                        return message.getString("content");
                    } else {
                        return "";
                    }
                });
    }
}
