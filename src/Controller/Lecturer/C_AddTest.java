package Controller.Lecturer;

import Constants.Screens;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class C_AddTest {


    public JFXButton btn_Next;
    public JFXButton btn_Cancel;
    public ScrollPane scrollPane_Quizs;
    public VBox vBox_QuizList;
    public StackPane stackPane_AddQuestions;

    public void initialize(){
        scrollPane_Quizs.setContent(vBox_QuizList);
        for (int i = 0; i < 10; i++) {
            vBox_QuizList.getChildren().add(new Label("Label" + i));
        }
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

    }


}
