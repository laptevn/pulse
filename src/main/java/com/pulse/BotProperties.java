package com.pulse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:bot.properties", encoding = "UTF-8")
public class BotProperties {
    private final String name;
    private final String token;
    private final String introductionMessage;

    public BotProperties(@Value("${name}") String name,
                         @Value("${token}") String token,
                         @Value("${introductionMessage}") String introductionMessage) {
        this.name = name;
        this.token = token;
        this.introductionMessage = introductionMessage;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getIntroductionMessage() {
        return introductionMessage;
    }
}