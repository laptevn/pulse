package com.pulse.datasource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pulse.QuestionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class FileDataSource implements DataSource {
    private final Reader sourceReader;

    public FileDataSource(@Value(value = "classpath:questions.json") Reader sourceReader) {
        this.sourceReader = sourceReader;
    }

    @Override
    public List<QuestionContext> loadQuestions() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<QuestionContext>>() {}.getType();
        try {
            return gson.fromJson(sourceReader, listType);
        } finally {
            sourceReader.close();
        }
    }
}