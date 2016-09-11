package com.pulse.poll;

public class AnswerCountPair implements Comparable<AnswerCountPair> {
    private final Integer answer;
    private final Integer count;

    public AnswerCountPair(Integer answer, Integer count) {
        this.answer = answer;
        this.count = count;
    }

    public Integer getAnswer() {
        return answer;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int compareTo(AnswerCountPair o) {
        return -count.compareTo(o.count);
    }
}