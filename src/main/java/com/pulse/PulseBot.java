package com.pulse;

import com.pulse.datasource.DataSource;
import com.pulse.poll.PollSession;
import com.pulse.request.RequestHandler;
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

            if (!requestHandler.handle(message, new RequestContext(messageSender, registeredUsers, pollSession))) {
                messageSender.send(message.getChatId().toString(), "Не могу обработать ваш запрос.");
            }
        }
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