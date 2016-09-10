package com.pulse.datasource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pulse.QuestionContext;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class FileDataSource implements DataSource {
    private final Reader sourceReader;

    public FileDataSource(Reader sourceReader) {
        this.sourceReader = sourceReader;
    }

    @Override
    public List<QuestionContext> loadQuestions() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<QuestionContext>>() {}.getType();
        return gson.fromJson(sourceReader, listType);
    }
}