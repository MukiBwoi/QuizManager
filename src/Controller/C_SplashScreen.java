package Controller;

import Utils.UI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class C_SplashScreen {


    public Button btn_Teacher;
    public Button btn_Student;

    public void btn_TeacherOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_Teacher.getScene().getWindow();
        stage.close();
        try {

            new UI().setUI("/View/V_Login");
            C_Login.setUserType("Teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_StudentOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_Student.getScene().getWindow();
        stage.close();
        try {
            new UI().setUI("/View/V_Login");
            C_Login.setUserType("Student");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
