package com.pulse;

import java.util.List;

public interface DataSource {
    List<QuestionContext> loadQuestions();
}