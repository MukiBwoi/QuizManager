package Controller;

import Utils.UI;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class C_Login {
    public JFXTextField txt_Email;
    public JFXPasswordField txt_Password;
    public static String userType;
    public Label lbl_Register;
    public Label lbl_forgotPassword;

    UI ui = new UI();

    public void btn_LoginOnAction(ActionEvent actionEvent) {
        if(userType.equals("Teacher")){
            System.out.println("Teacher");
        }else{
            System.out.println("Student");

        }
    }

    public void lbl_RegisterOnAction(MouseEvent mouseEvent) {
        try {
           Stage stage =  (Stage)lbl_Register.getScene().getWindow();
           stage.close();
           ui.setUI("/View/V_Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lbl_forgotPasswordOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage =  (Stage)lbl_forgotPassword.getScene().getWindow();
            stage.close();
            ui.setUI("/View/V_ResetPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUserType(String u){
        userType = u;
    }
}
