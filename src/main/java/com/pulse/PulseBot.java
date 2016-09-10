package com.pulse;

import com.pulse.request.RequestHandler;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.LinkedList;
import java.util.List;

public class PulseBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final BotProperties botProperties;
    private final PollSession pollSession = new PollSession();
    private final List<Long> registeredUsers = new LinkedList<>();
    private final MessageSender messageSender;

    public PulseBot(RequestHandler requestHandler, BotProperties botProperties) {
        this.requestHandler = requestHandler;
        this.botProperties = botProperties;
        messageSender = new MessageSender(this);
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