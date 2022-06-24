package Model.Entities;
import java.io.File;

public class Quiz {
    private String quiz;
    private File image;
    private String type;


    public Quiz() {
    }

    public Quiz( String quiz, String type) {
        this.setQuiz(quiz);
        this.setType(type);
    }

    public Quiz( String quiz, File image, String type) {
        this.setQuiz(quiz);
        this.setImage(image);
        this.setType(type);
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
