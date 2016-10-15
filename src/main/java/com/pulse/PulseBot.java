package com.pulse;

import com.pulse.datasource.DataSource;
import com.pulse.poll.PollSession;
import com.pulse.request.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class PulseBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger(PulseBot.class);

    private final RequestHandler requestHandler;
    private final BotProperties botProperties;
    private final PollSession pollSession;
    private final Set<String> registeredUsers = new HashSet<>();
    private final MessageSender messageSender;

    @Autowired
    public PulseBot(RequestHandler requestHandler, BotProperties botProperties, DataSource dataSource) throws IOException {
        this.requestHandler = requestHandler;
        this.botProperties = botProperties;
        messageSender = new MessageSender(this);
        pollSession = new PollSession(dataSource.loadQuestions(), messageSender, new QuestionToMessageMapper());
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            LOGGER.info("Received a message {}", () -> messageToString(message));

            if (!requestHandler.handle(message, new RequestContext(messageSender, registeredUsers, pollSession))) {
                LOGGER.warn("Cannot handle the message");
                messageSender.send(message.getChatId().toString(), "Не могу обработать ваш запрос.");
            }
        }
    }

    private static String messageToString(Message message) {
        StringBuilder result = new StringBuilder();
        result.append("From: ").append(message.getFrom().toString()).append(". ");
        if (message.hasText()) {
            result.append("Text: '").append(message.getText()).append("'");
        } else {
            result.append("No text");
        }

        return result.toString();
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}