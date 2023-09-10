package com.br.chatgpt.service.impl;

import com.br.chatgpt.client.ChatGPTClient;
import com.br.chatgpt.service.ChatGPTService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTClient chatGPTClient;

    public ChatGPTServiceImpl(ChatGPTClient chatGPTClient) {
        this.chatGPTClient = chatGPTClient;
    }

    @Override
    public Mono<String> generateAnswer(final String input) {
        return chatGPTClient.sendQuestion(input);
    }
}
