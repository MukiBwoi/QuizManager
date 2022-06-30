package Controller.Student;

import Constants.Screens;
import Model.Authentication.CurrentUserModel;
import Model.Database.TestService;
import Model.Entities.MyTest;
import Model.Entities.Test;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.sun.istack.internal.Nullable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class C_GridTestItem {
    public Label lbl_TestName;
    public Label lbl_Author;
    public Label lbl_Category;
    public JFXSpinner progress_Marks;
    public JFXButton btn_StartTest;
    public static MyTest myTest;
    public static Test test;

    public void initialize(){
        System.out.println(myTest + "  " + test );
        if(test == null && myTest != null){

            lbl_TestName.setText(myTest.getTestData().getName());
            lbl_Author.setText(myTest.getTestData().getAuthor());
            lbl_Category.setText(myTest.getTestData().getCategory());
            progress_Marks.setProgress(myTest.getMarks());
            btn_StartTest.setText("Redo Test");
            UI.progressBarAnimation(progress_Marks,myTest.getMarks());

        }else if(myTest == null && test != null){
            lbl_TestName.setText(test.getName());
            lbl_Author.setText(test.getAuthor());
            lbl_Category.setText(test.getCategory());
            progress_Marks.setProgress(0);
            btn_StartTest.setText("Start Test");
            UI.progressBarAnimation(progress_Marks,0.0);
        }else{
            System.out.println("null");
        }

    }

    public void btn_StartTestOnAction(ActionEvent actionEvent) {

        try {
            if(test == null && myTest != null){
                C_StartTest.setData(null,myTest);
                new UI().closeUIButton(btn_StartTest);
                new UI().setUI(Screens.startTest);
            }else if(myTest == null && test != null){
                C_StartTest.setData(test,null);
                new UI().closeUIButton(btn_StartTest);
                new UI().setUI(Screens.startTest);
            }else{
                System.out.println("null");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void dividerTests(){

    }


}
