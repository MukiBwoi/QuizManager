package Model.Entities;

import java.util.Map;

public class Quiz {
    int quiz_id;
    String quiz;
    String[] images;
    Map<String ,  String> Answers;
    int correct_answer;

    public Quiz() {
    }

    public Quiz(int quiz_id, String quiz, String[] images, Map<String, String> answers, int correct_answer) {
        this.quiz_id = quiz_id;
        this.quiz = quiz;
        this.images = images;
        Answers = answers;
        this.correct_answer = correct_answer;
    }

}
