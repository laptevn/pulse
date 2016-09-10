package com.pulse;

import com.pulse.datasource.DataSource;
import com.pulse.request.RequestHandler;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.LinkedList;
import java.util.List;

public class PulseBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final BotProperties botProperties;
    private final PollSession pollSession;
    private final List<String> registeredUsers = new LinkedList<>(); //TODO: Use set instead of list.
    private final MessageSender messageSender;

    public PulseBot(RequestHandler requestHandler, BotProperties botProperties, DataSource dataSource) {
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
                messageSender.send(message.getChatId().toString(), "Cannot handle your request");
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