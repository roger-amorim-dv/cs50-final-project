package com.br.chatgpt.service;

import reactor.core.publisher.Mono;

public interface ChatGPTService {
    Mono<String> generateAnswer(final String input);
}
