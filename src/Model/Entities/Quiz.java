package Model.Entities;

import com.sun.istack.internal.Nullable;

public class Quiz {

    private int id;
    private String quiz;
    private String type;
    private Answer answer;



    public Quiz() {
    }

    public Quiz( String quiz, String type , Answer answer,@Nullable int id) {
        this.setQuiz(quiz);
        this.setType(type);
        this.setAnswer(answer);
        this.setId(id);

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
