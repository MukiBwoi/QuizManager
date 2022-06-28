package Controller.Authentication;

import Constants.Screens;
import Constants.Users;
import Controller.Common.C_Validation;
import Model.Authentication.CurrentUserModel;
import Model.Entities.AuthUser;
import Model.Database.UserService;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
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
    public Pane pane_RegisterRedirect;
    public AnchorPane pane_Login;
    public JFXButton btn_forgotPassword;
    public Label lbl_EmailError;
    public Label lbl_PasswordError;
    public JFXButton btn_Login;
    public JFXButton btn_SwitchUser;
    public Label lbl_UserType;

    UI ui = new UI();

    public void initialize(){
        ifAdmin();
        txt_Email.setText(null);
        txt_Password.setText(null);
        lbl_UserType.setText("Not "+ Users.current_user+"?" );
    }

    public void btn_LoginOnAction(ActionEvent actionEvent) {
        String emailValidation = C_Validation.validateEmail(txt_Email.getText());
        String passwordValidation = C_Validation.commonValidator(txt_Password.getText(),
                "Password required!");
        lbl_EmailError.setText(emailValidation);
        lbl_PasswordError.setText(passwordValidation);

        if(emailValidation == null && passwordValidation == null){
            if(Users.current_user.equals(Users.lecturer)){

                loginProcess(Screens.lecturerDashboard);

            }else if(Users.current_user.equals(Users.student)){

                loginProcess(Screens.studentDashboard);

            }else{
                System.out.println("Admin Login");
            }
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


    public void btn_forgotPasswordOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =  (Stage)btn_forgotPassword.getScene().getWindow();
            stage.close();
            ui.setUI(Screens.resetPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //hide registration button for admin
    void ifAdmin(){
        if(Users.current_user.equals(Users.admin)){
            pane_RegisterRedirect.setVisible(false);
            pane_Login.setPrefSize(313,460);
        }
    }

    //process when click Lets login button
    private void loginProcess(String dashboard){
        try{
            if(C_Validation.isEmailExist(txt_Email.getText())){
                if(checkPassword()){
                    CurrentUserModel.currentUserEmail = txt_Email.getText();
                    CurrentUserModel.getCurrentUser();
                    CurrentUserModel.isLoggedIn = true;
                    new Utils.UI().closeUIButton(btn_Login);
                    new Utils.UI().setUI(dashboard);
                }else{
                    new Utils.UI().showErrorAlert("Invalid password ! Please Try again.");
                }
            }else{
                new Utils.UI().showErrorAlert(
                        "Email doesn't exists ! if you don't have an account please register.");
            }

        }catch (IOException e){
                e.printStackTrace();
        }

    }

    //Check if password correct
    private boolean checkPassword(){
        for (AuthUser authuser: UserService.authUsers) {
            if(authuser.getEmail().equals(txt_Email.getText())){
                if(authuser.getEmp_type().equals(Users.current_user)){
                    return authuser.getPassword().equals(txt_Password.getText());
                }
            }
        }
        return  false;
    }

    public void btn_SwitchUserOnAction(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_SwitchUser);
            Users.current_user = null;
            new UI().setUI(Screens.splashScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
