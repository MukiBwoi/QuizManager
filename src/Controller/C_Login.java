package Controller;

import Constants.Screens;
import Constants.Users;
import Utils.UI;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class C_Login {
    public JFXTextField txt_Email;
    public JFXPasswordField txt_Password;
    public Label lbl_Register;
    public Label lbl_forgotPassword;
    public Pane pane_RegisterRedirect;
    public AnchorPane pane_Login;

    UI ui = new UI();

    public void initialize(){
        ifAdmin();
    }

    public void btn_LoginOnAction(ActionEvent actionEvent) {
        if(Users.current_user.equals(Users.lecturer)){
            System.out.println(Users.lecturer);
        }else if(Users.current_user.equals(Users.student)){
            System.out.println(Users.student);
        }else{
            System.out.println(Users.admin);
        }
    }

    public void lbl_RegisterOnAction(MouseEvent mouseEvent) {
        try {
           Stage stage =  (Stage)lbl_Register.getScene().getWindow();
           stage.close();
           ui.setUI(Screens.register);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lbl_forgotPasswordOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage =  (Stage)lbl_forgotPassword.getScene().getWindow();
            stage.close();
            ui.setUI(Screens.resetPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void ifAdmin(){
        if(Users.current_user.equals(Users.admin)){
            pane_RegisterRedirect.setVisible(false);
            pane_Login.setPrefSize(313,460);
        }
    }
}
