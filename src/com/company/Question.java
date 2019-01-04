package com.company;

import java.util.Arrays;
import java.util.Objects;

public class Question {

    String feature;
    String question;
    String[] answer;

    public Question(String feature, String question, String[] answers) {
        this.feature = feature;
        this.question = question;
        this.answer = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(feature, question1.feature) &&
                Objects.equals(question, question1.question) &&
                Arrays.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(feature, question);
        result = 31 * result + Arrays.hashCode(answer);
        return result;
    }
}
