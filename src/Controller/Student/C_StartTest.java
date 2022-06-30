package Controller.Student;

import Constants.Screens;
import Model.Database.QuizService;
import Model.Entities.MyTest;
import Model.Entities.Test;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSpinner;
import com.sun.istack.internal.Nullable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class C_StartTest {

    public HBox hBox_Author;
    public VBox vBox_Menu;
    public HBox hBox_Category;
    public HBox hBox_NOFQuizs;
    public HBox hBox_EnrolledCount;
    private static MyTest myTest;
    private static Test test;
    public Label lbl_author;
    public Label lbl_Category;
    public Label lbl_NofQuizs;
    public Label lbl_EnrolledCount;
    public Label lbl_Description;
    public JFXSpinner spinner_marks;
    public Label lbl_TestName;
    public JFXButton btn_EnrollNow;
    public JFXButton btn_BackToDashboard;


    public void initialize(){

        setRippler();

        try {
            if(test == null && myTest != null){

                initializeControls(myTest.getTestData());
                spinner_marks.setProgress(myTest.getMarks());
                btn_EnrollNow.setText("Redo Test");
                UI.progressBarAnimation(spinner_marks,myTest.getMarks());

            }else if(myTest == null && test != null){
                initializeControls(test);
                spinner_marks.setProgress(0);
                btn_EnrollNow.setText("Enroll to Test");
                UI.progressBarAnimation(spinner_marks,0.0);
            }else{
                System.out.println("null");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setRippler(){
        vBox_Menu.getChildren().clear();
        customRippler(hBox_Author, hBox_Category);
        customRippler(hBox_NOFQuizs, hBox_EnrolledCount);

    }

    private void customRippler(HBox hBox_author, HBox hBox_category) {
        JFXRippler authorRippler = new JFXRippler(hBox_author);
        authorRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(authorRippler);

        JFXRippler categoryRippler = new JFXRippler(hBox_category);
        categoryRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(categoryRippler);
    }

    public static void setData(@Nullable Test t , @Nullable MyTest mt){
        if(t == null && mt != null){
            myTest = mt;
        }else if(mt == null && t != null){
            test = t;
        }else{
            System.out.println("Null");
        }
    }

    public void btn_EnrollNowOnAction(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_EnrollNow);
            new UI().setUI(Screens.doQuizs);
            test = null;
            myTest = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeControls(Test t) throws SQLException, ClassNotFoundException {
        lbl_TestName.setText(t.getName());
        lbl_author.setText(t.getAuthor());
        lbl_Category.setText(t.getCategory());
        lbl_EnrolledCount.setText(t.getEnrolledCount()+"");
        lbl_NofQuizs.setText(t.getNofQuizs()+"");
        QuizService.getQuizs(t.getId());
    }

    public void btn_BackToDashboardOnAction(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_BackToDashboard);
            new UI().setUI(Screens.studentDashboard);
            test = null;
            myTest = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
