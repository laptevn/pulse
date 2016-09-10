package com.pulse;

import com.pulse.request.RequestHandler;
import com.pulse.request.RequestHandlerChainFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.BotSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        BotSession session = null;
        try {
            RequestHandler requestHandler = new RequestHandlerChainFactory().create();
            BotProperties botProperties = readProperties(Main.class.getClassLoader().getResource("bot.properties"));
            session = telegramBotsApi.registerBot(new PulseBot(requestHandler, botProperties));

            new Scanner(System.in).nextLine();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static BotProperties readProperties(URL filePath) throws IOException {
        try (Reader reader = new BufferedReader(new FileReader(filePath.getFile()))) {
            return new BotProperties(reader);
        }
    }
}