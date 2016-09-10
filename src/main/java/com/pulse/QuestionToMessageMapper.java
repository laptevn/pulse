package com.pulse;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class QuestionToMessageMapper {
    public List<SendMessage> map(QuestionContext questionContext, List<String> chatIds) {
        // TODO: We should create the list of SendMessage instances for each chatId.
        // Message should contain the question text and all answers numbered 1-N, where N is the number of answers.
        // For example:
        // Кто президент России?
        // 1. Кадыров
        // 2. Путин
        // 3. Киркоров
        // 4. Обама

//                SendMessage sendMessageRequest = new SendMessage();
//                sendMessageRequest.setChatId(message.getChatId().toString());
//                sendMessageRequest.setText("you said: " + message.getText());
//
//                sendMessageRequest.setReplyMarkup(getAlertsKeyboard());
        return null;
    }

    // TODO: This method is an example of how create keyboard with answers for the user.
    // We should update it so that the user have N buttons with numbers 1-N, where N is the number of answers to the question.
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
}