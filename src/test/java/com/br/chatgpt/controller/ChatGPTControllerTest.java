package com.br.chatgpt.controller;

import com.br.chatgpt.service.ChatGPTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ChatGPTControllerTest {

    @InjectMocks
    private ChatGPTController chatGPTController;

    @Mock
    private ChatGPTService chatGPTService;

    @Mock
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToController(chatGPTController).build();
    }

    @Test
    public void postTextTest() {
        String inputText = "Hello, chatbot!";
        String expectedResponse = "Hello! How can I assist you today?"; // This is the current answer practiced by ChatGPT

        when(chatGPTService.generateAnswer(inputText)).thenReturn(Mono.just(expectedResponse));

        webTestClient
                .post()
                .uri("/post-text")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inputText)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                    // Assert the response matches the expected response
                    assert(response.equals(expectedResponse));
                });
    }
}
