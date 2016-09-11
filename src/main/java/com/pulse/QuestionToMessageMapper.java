package com.pulse;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionToMessageMapper {
    public List<SendMessage> map(QuestionContext questionContext, Collection<String> chatIds) {
        String message = toMessage(questionContext);
        ReplyKeyboardMarkup keyboard = getKeyboard(questionContext.getAnswers().size());

        return chatIds.stream().map(chatId -> {
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(chatId);
            sendMessageRequest.setText(message);
            sendMessageRequest.setReplyMarkup(keyboard);
            return sendMessageRequest;
        }).collect(Collectors.toList());
    }

    private String toMessage(QuestionContext questionContext) {
        StringBuilder result = new StringBuilder();
        result.append(questionContext.getQuestion());
        result.append(System.lineSeparator());
        result.append("Варианты ответов:");

        for (int i = 0; i < questionContext.getAnswers().size(); ++i) {
            result.append(System.lineSeparator());
            result.append(i + 1);
            result.append(". ");
            result.append(questionContext.getAnswers().get(i));
        }

        return result.toString();
    }

    private static ReplyKeyboardMarkup getKeyboard(int numberOfAnswers) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        for (int i = 1; i <= numberOfAnswers; ++i) {
            row.add(Integer.toString(i));
        }
        keyboard.add(row);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}