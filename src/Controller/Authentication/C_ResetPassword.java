package Controller.Authentication;

import Constants.Screens;
import Controller.Common.C_Validation;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class C_ResetPassword {
    public JFXButton btn_Login;
    public Pane pane_SendCode;
    public Label lbl_EmailError;
    public Label lbl_Heading;
    public JFXButton btn_SendVerification;
    public JFXTextField txt_email;
    public ImageView img_loadingIndicator;


    public void initialize(){
        txt_email.setText(null);
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
        String emailValidation = C_Validation.validateEmail(txt_email.getText());
        if(emailValidation == null){
            try {
                btn_Login.setDisable(true);
                new C_Register().sendVerifyCode("Reset Password",btn_SendVerification,Screens.changePassword,
                        img_loadingIndicator,txt_email.getText());
            } catch (IOException e) {
                btn_Login.setDisable(false);
                e.printStackTrace();
            }catch (Exception e){
                btn_Login.setDisable(false);
                e.printStackTrace();
            }
        }else{
            lbl_EmailError.setText(emailValidation);
        }
    }

}
