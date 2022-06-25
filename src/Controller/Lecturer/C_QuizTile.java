package Controller.Lecturer;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class C_QuizTile {
    public Label lbl_Question;
    public FontAwesomeIconView lbl_Delete;
    public static int quizIndex;

    public void intialize(){
        lbl_Question.setText("Question 1 " + quizIndex);
    }

    public void lbl_DeleteOnAction(MouseEvent mouseEvent) {
    }
}
