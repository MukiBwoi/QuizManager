package Controller.Authentication;

import Constants.Screens;
import Controller.Common.C_Validation;
import Model.Authentication.M_ChangePassword;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.SQLException;

public class C_ChangePassword {
    public JFXPasswordField txt_newPassword;
    public JFXPasswordField txt_confirmNewPassword;
    public Label lbl_newPasswordError;
    public Label lbl_ConfirmNewPasswordError;
    public JFXButton btn_ChangePassword;
    public JFXButton btn_Login;
    public static String email;

    public void initialize(){
        txt_newPassword.setText(null);
        txt_confirmNewPassword.setText(null);

    }
    public void btn_LoginOnActionPerfomed(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_Login);
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_ChangePasswordOnAction(ActionEvent actionEvent) {
        String newPasswordValidation = C_Validation.commonValidator(txt_newPassword.getText(),
                "New Password Required");
        String confirmPasswordValidation = C_Validation.validateConfirmPassword(txt_newPassword.getText(),
                txt_confirmNewPassword.getText());
        lbl_newPasswordError.setText(newPasswordValidation);
        lbl_ConfirmNewPasswordError.setText(confirmPasswordValidation);
        if(newPasswordValidation == null && confirmPasswordValidation == null){
            try {
                if(M_ChangePassword.changePassword(email,txt_confirmNewPassword.getText())){
                    new Utils.UI().closeUIButton(btn_ChangePassword);
                    new Utils.UI().setUI(Screens.login);
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
