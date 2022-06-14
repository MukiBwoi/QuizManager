package Controller;

import Constants.Screens;
import Constants.Users;
import Utils.UI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class C_SplashScreen {


    public Button btn_Student;
    public Button btn_Admin;
    public Button btn_Lecturer;
    public void initialize(){

    }


    public void btn_StudentOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_Student.getScene().getWindow();
        stage.close();
        try {
            Users.current_user = Users.student;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btn_AdminOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_Admin.getScene().getWindow();
        stage.close();
        try {
            Users.current_user = Users.admin;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_LecturerOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_Lecturer.getScene().getWindow();
        stage.close();
        try {
            Users.current_user = Users.lecturer;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
