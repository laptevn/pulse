package com.pulse;

import com.pulse.request.RequestHandler;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;
import java.util.List;

public class PulseBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final BotProperties botProperties;

    public PulseBot(RequestHandler requestHandler, BotProperties botProperties) {
        this.requestHandler = requestHandler;
        this.botProperties = botProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();

            if (!requestHandler.handle(message, this)) {
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("Cannot handle your request");

                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                }
            }

//            //check if the message has text. it could also contain for example a location ( message.hasLocation() )
//            if(message.hasText()){
//                //create an object that contains the information to send back the message
//                SendMessage sendMessageRequest = new SendMessage();
//                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
//                sendMessageRequest.setText("you said: " + message.getText());
//
//                //sendMessageRequest.setReplyMarkup(getAlertsKeyboard());
//
//                try {
//                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
//                } catch (TelegramApiException e) {
//                    //do some error handling
//                }
//            }
        }
    }

    private static ReplyKeyboardMarkup getAlertsKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("row1 command1");
        row.add("row1 command2");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("row2 command1");
        row.add("row2 command2");
        keyboard.add(row);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
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