package Controller.Lecturer;

import Constants.Screens;
import Model.Entities.Quiz;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.ArrayList;

public class C_AddTest {


    public JFXButton btn_Next;
    public JFXButton btn_Cancel;
    public ScrollPane scrollPane_Quizs;
    public VBox vBox_QuizList;
    public StackPane stackPane_AddQuestions;
    public ScrollPane scrollPane_QuizContents;
    public VBox vBox_QuizContents;
    public Label lbl_QuestionNumber;
    public JFXTextArea textArea_Question;
    public JFXTextField txt_Answer1;
    public JFXTextField txt_Answer2;
    public JFXTextField txt_Answer3;
    public JFXTextField txt_Answer4;
    public JFXTextField txt_CorrectAnswer;
    public JFXButton btn_AddQuiz;
    public FontAwesomeIconView icon_Add_DoneQuiz;
    private boolean isAddQuizClicked = false;
    private  int quizindex = 0;


    public void initialize(){
        scrollPane_Quizs.setContent(vBox_QuizList);
        scrollPane_QuizContents.setContent(vBox_QuizContents);
    }

    public void btn_CancelOnAction(ActionEvent actionEvent) {

        try {
            new UI().setUI(Screens.lecturerDashboard);
            new UI().closeUIButton(btn_Cancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_NextOnAction(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_Next);
            new UI().setUI(Screens.lecturerDashboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_AddQuizOnAction(ActionEvent actionEvent) {
        if(!isAddQuizClicked){

            isAddQuizClicked = true;
            quizindex = quizindex+1;
            lbl_QuestionNumber.setText("Question " + quizindex);
            new UI().NavigatePane(stackPane_AddQuestions,scrollPane_QuizContents);
            btn_Next.setDisable(true);
            btn_AddQuiz.setText("Done");

        }else{

            isAddQuizClicked = false;
            //----------Add Quiz Object to Quiz List

            new UI().NavigatePane(stackPane_AddQuestions,scrollPane_Quizs);

            btn_AddQuiz.setText("Add");

            btn_Next.setDisable(false);
            addToQuizList();
        }

    }

    private void addToQuizList(){
        try {
            C_QuizTile.quizIndex = quizindex;
            Node node = FXMLLoader.load(getClass().getResource(Screens.quizTile+".fxml"));
            JFXRippler ripple = new JFXRippler(node);
            ripple.setRipplerFill(Color.valueOf("#2196f3"));
            vBox_QuizList.getChildren().add(ripple);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
