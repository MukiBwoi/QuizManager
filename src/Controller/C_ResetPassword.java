package Controller;

import Constants.Screens;
import Model.ValidationModel;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class C_ResetPassword {
    public JFXButton btn_Login;
    public Label lbl_EmailError1;
    public Pane pane_SendCode;
    public JFXTextField txt_Email;
    public Label lbl_EmailError;
    public Pane pane_VerifyCode;
    public JFXTextField txt_VerifyCode;
    public Label lbl_VerificationCodeError;
    public Label lbl_Heading;
    public JFXButton btn_ResendEmail;

    public void initialize(){
        txt_Email.setText(null);
        txt_VerifyCode.setText(null);
    }
    public void btn_LoginOnActionPerfomed(ActionEvent actionEvent) {
        try {
            Stage stage =  (Stage)btn_Login.getScene().getWindow();
            stage.close();
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_SendVerificationOnAction(ActionEvent actionEvent) {
        String emailValidation = ValidationModel.validateEmail(txt_Email.getText());
        if(emailValidation == null){
            btn_Login.setFocusTraversable(false);
            pane_SendCode.setVisible(false);
            pane_VerifyCode.setVisible(true);
            lbl_Heading.setText("Verify Code");
        }else{
            lbl_EmailError.setText(emailValidation);
        }

    }

    public void btn_VerifyCodeOnAction(ActionEvent actionEvent) {
        String verifyCodeValidation = ValidationModel.commonValidator(txt_VerifyCode.getText() ,
                "Verification code required !");

        if(verifyCodeValidation == null){

        }else{
            lbl_VerificationCodeError.setText(verifyCodeValidation);
        }
    }

    public void btn_ChangeEmailOnAction(ActionEvent actionEvent) {
        pane_VerifyCode.setVisible(false);
        pane_SendCode.setVisible(true);
        lbl_Heading.setText("Reset Password");
    }

    public void btn_ResendOnAction(ActionEvent actionEvent) {
    }
}
