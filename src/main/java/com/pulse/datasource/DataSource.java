package com.pulse.datasource;

import com.pulse.QuestionContext;

import java.io.IOException;
import java.util.List;

public interface DataSource {
    List<QuestionContext> loadQuestions() throws IOException;
}