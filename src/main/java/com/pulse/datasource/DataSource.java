package com.pulse.datasource;

import com.pulse.QuestionContext;

import java.util.List;

public interface DataSource {
    List<QuestionContext> loadQuestions();
}