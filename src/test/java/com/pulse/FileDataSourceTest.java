package com.pulse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileDataSourceTest {
    @Test
    public void loadQuestionsList() throws IOException {
        ArrayList<QuestionContext> questionContexts = new ArrayList<>(2);
        questionContexts.add(new QuestionContext("Sample question1", Arrays.asList("answer1 to question1", "answer2 to question1"), 1));
        questionContexts.add(new QuestionContext("Sample question2", Arrays.asList("answer1 to question2", "answer2 to question2"), 0));

        checkQuestionsLoading(questionContexts);
    }

    @Test
    public void loadOneQuestion() throws IOException {
        ArrayList<QuestionContext> questionContexts = new ArrayList<>(1);
        questionContexts.add(new QuestionContext("Sample question1", Arrays.asList("answer1 to question1", "answer2 to question1"), 1));

        checkQuestionsLoading(questionContexts);
    }

    @Test
    public void loadNoQuestions() throws IOException {
        checkQuestionsLoading(new ArrayList<>());
    }

    private static void checkQuestionsLoading(List<QuestionContext> questionContexts) throws IOException {
        try (Reader reader = new StringReader(writeQuestions(questionContexts))) {
            DataSource dataSource = new FileDataSource(reader);
            List<QuestionContext> loadedContexts = dataSource.loadQuestions();

            System.out.println(questionContexts);
            System.out.println(loadedContexts);
            assertEquals(questionContexts, loadedContexts);
        }
    }

    private static String writeQuestions(List<QuestionContext> questionContexts) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<QuestionContext>>() {}.getType();
        return gson.toJson(questionContexts, listType);
    }
}