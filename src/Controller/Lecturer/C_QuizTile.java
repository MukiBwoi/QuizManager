package Controller.Lecturer;

import Model.Database.QuizService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class C_QuizTile {
    public Label lbl_Question;
    public FontAwesomeIconView lbl_Delete;
    public static int quizIndex;

    public void initialize(){
        lbl_Question.setText("Question " + quizIndex);
    }

    public void lbl_DeleteOnAction(MouseEvent mouseEvent) {

        System.out.println("Deletation");

    }
}
