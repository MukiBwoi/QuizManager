package Controller.Authentication;

import Constants.Screens;
import Constants.Users;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import java.io.IOException;


public class C_SplashScreen {


    public JFXButton btn_Student;
    public JFXButton btn_Admin;
    public JFXButton btn_Lecturer;
    public void initialize(){

    }


    public void btn_StudentOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Student);
            Users.current_user = Users.student;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btn_AdminOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Admin);
            Users.current_user = Users.admin;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_LecturerOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Lecturer);
            Users.current_user = Users.lecturer;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
