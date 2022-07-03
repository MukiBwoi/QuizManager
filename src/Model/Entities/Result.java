package Model.Entities;

public class Result {
    public Result(int quizID, boolean isCorrect, int correctAnswerIndex) {
        this.setQuizID(quizID);
        this.setCorrect(isCorrect);
        this.setCorrectAnswerIndex(correctAnswerIndex);
    }

    @Override
    public String toString() {
        return "Result{" +
                "quizID=" + quizID +
                ", isCorrect=" + isCorrect +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }

    private int quizID;
    private boolean isCorrect;
    private int correctAnswerIndex;

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
