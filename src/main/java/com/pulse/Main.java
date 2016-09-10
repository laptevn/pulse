package com.pulse;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.BotSession;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        BotSession session = null;
        try {
            session = telegramBotsApi.registerBot(new Bot());
            new Scanner(System.in).nextLine();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}