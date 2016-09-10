package com.pulse;

import java.util.List;

public class QuestionContext {
    private final String question;
    private final List<String> answers;
    private final int correctAnswerIndex;

    public QuestionContext(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionContext that = (QuestionContext) o;

        if (correctAnswerIndex != that.correctAnswerIndex) return false;
        if (!answers.equals(that.answers)) return false;

        return question.equals(that.question);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answers.hashCode();
        result = 31 * result + correctAnswerIndex;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionContext{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }
}