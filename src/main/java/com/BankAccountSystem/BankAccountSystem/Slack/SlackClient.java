package com.BankAccountSystem.BankAccountSystem.Slack;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class SlackClient {
    public String sendMessage(String text) {

        return WebClient.create().post()
                .uri("https://hooks.slack.com/services/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")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SlackPayload(text))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
