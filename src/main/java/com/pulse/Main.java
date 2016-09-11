package com.pulse;

import com.pulse.datasource.FileDataSource;
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
            BotProperties botProperties = readProperties(Main.class.getClassLoader().getResource("bot.properties"));
            RequestHandler requestHandler = new RequestHandlerChainFactory().create(botProperties);

            PulseBot pulseBot;
            try (Reader reader = new BufferedReader(new FileReader(Main.class.getClassLoader().getResource("questions.json").getFile()))) {
                pulseBot = new PulseBot(requestHandler, botProperties, new FileDataSource(reader));
            }

            session = telegramBotsApi.registerBot(pulseBot);

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