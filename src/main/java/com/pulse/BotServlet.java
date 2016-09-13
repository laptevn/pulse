package com.pulse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.BotSession;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@EnableAutoConfiguration
@ComponentScan
public class BotServlet extends SpringBootServletInitializer {
    private volatile BotSession session;
    private final PulseBot pulseBot;

    @Autowired
    public BotServlet(PulseBot pulseBot) {
        this.pulseBot = pulseBot;
    }

    @PostConstruct
    public void onStart() throws TelegramApiException {
        session = new TelegramBotsApi().registerBot(pulseBot);
    }

    @PreDestroy
    public void onStop() {
        if (session != null) {
            session.close();
        }
    }
}