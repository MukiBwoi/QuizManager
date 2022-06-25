package Model.Entities;

import java.io.Serializable;

public class Quiz {

    private String quiz;
    private String type;
    private Answer answer;



    public Quiz() {
    }

    public Quiz( String quiz, String type , Answer answer) {
        this.setQuiz(quiz);
        this.setType(type);
        this.setAnswer(answer);

    }


    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quiz='" + quiz + '\'' +
                ", type='" + type + '\'' +
                ", answer=" + answer +
                '}';
    }
}
