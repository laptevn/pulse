package com.pulse;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class BotProperties {
    private final String name;
    private final String token;

    public BotProperties(Reader propertiesReader) throws IOException {
        Properties properties = new Properties();
        properties.load(propertiesReader);

        name = properties.getProperty("name");
        token = properties.getProperty("token");
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}