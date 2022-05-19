package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class C_Login {
    public JFXTextField txt_Email;
    public JFXPasswordField txt_Password;
    private static String userType;

    public void btn_LoginOnAction(ActionEvent actionEvent) {
        if(userType == "Teacher"){
            System.out.println("Teacher");
        }else{
            System.out.println("Student");
        }
    }

    public void lbl_RegisterOnAction(MouseEvent mouseEvent) {
    }

    public void lbl_forgotPasswordOnAction(MouseEvent mouseEvent) {
    }

    public static void setUserType(String u){
        userType = u;
    }
}
