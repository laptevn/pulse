package com.pulse;

import com.pulse.request.RequestHandler;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.LinkedList;
import java.util.List;

public class PulseBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final BotProperties botProperties;
    private final List<Long> registeredUsers = new LinkedList<>();

    public PulseBot(RequestHandler requestHandler, BotProperties botProperties) {
        this.requestHandler = requestHandler;
        this.botProperties = botProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();

            if (!requestHandler.handle(message, new RequestContext(new MessageSender(this), registeredUsers))) {
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("Cannot handle your request");

                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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