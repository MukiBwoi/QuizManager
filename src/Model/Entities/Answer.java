package Model.Entities;

import java.util.Arrays;

public class Answer {


    private String[] answers;
    private int correctAnswerIndex;

    public Answer(String[] answers, int correctAnswerIndex) {
        this.setAnswers(answers);
        this.setCorrectAnswerIndex(correctAnswerIndex);
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answers=" + Arrays.toString(answers) +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }
}
