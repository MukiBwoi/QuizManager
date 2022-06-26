package Controller.Authentication;

import Constants.Screens;
import Constants.Users;
import Controller.Common.C_Validation;
import Model.Authentication.M_Register;
import Model.Entities.Lecturer;
import Model.Entities.Student;
import Utils.UI;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class C_Register {

    public JFXTextField txt_email;
    public Label lbl_Login;
    public JFXButton btn_Register;
    public JFXTextField txt_firstName;
    public JFXPasswordField txt_createPassword;
    public JFXTextField txt_lastName;
    public JFXDatePicker datePicker_DOB;
    public JFXTextField txt_age;
    public JFXCheckBox checkBox_TC;
    public JFXPasswordField txt_ConfirmPassword;
    public Label lbl_FNameError;
    public Label lbl_LNameError;
    public Label lbl_emailError;
    public Label lbl_DOBError;
    public Label lbl_CityError;
    public Label lbl_createPasswordError;
    public Label lbl_confirmPasswordError;
    public Label lbl_tcError;
    public JFXTextField txt_Position;
    public JFXComboBox<String> cmb_batch;
    public JFXComboBox<String> cmb_branch;
    public JFXComboBox<String> current_cmb;
    public Label lbl_why;
    public ImageView img_loadingIndicator;
    private  String cmb_validation_message;
    public static boolean isValidated;



    UI ui = new UI();
    public void initialize(){
        isValidated = false;
        setFieldNull();
        showCombo();

    }
    public void lbl_LoginOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage =  (Stage)lbl_Login.getScene().getWindow();
            stage.close();
            ui.setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_RegisterOnAction(ActionEvent actionEvent) {
        validateAllFields();
        if(isValidated){
            if(C_Validation.isEmailExist(txt_email.getText())){
                ui.showErrorAlert("Email already exists ! Please use a different email.");
            }else{
                try {
                    boolean isRegistered;
                    if(Objects.equals(Users.current_user, Users.student)){
                        isRegistered = M_Register.registerStudent(
                                new Student(txt_firstName.getText(),txt_lastName.getText() , txt_email.getText(),
                                        java.sql.Date.valueOf(datePicker_DOB.getValue()),
                                        Integer.parseInt(txt_age.getText()), cmb_batch.getValue(),1,
                                        txt_ConfirmPassword.getText(), txt_Position.getText(),false,
                                        Timestamp.valueOf(LocalDateTime.now()))
                        );
                    }else{
                        isRegistered = M_Register.registerLecturer(
                                new Lecturer(txt_firstName.getText(),txt_lastName.getText() , txt_email.getText(),
                                        cmb_branch.getValue(),0,txt_ConfirmPassword.getText(),
                                        txt_Position.getText(),false, Timestamp.valueOf(LocalDateTime.now())));
                    }

                    if(isRegistered){
                        sendVerifyCode("Registration" , btn_Register,Screens.uploadAvatar ,
                                img_loadingIndicator,txt_email.getText());
                    }else{
                        ui.showErrorAlert("Something went wrong please try again !");
                    }
                } catch (IOException | ClassNotFoundException | SQLException e) {
                    ui.showErrorAlert(e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }


    public void lbl_TermsAndConditionOnAction(MouseEvent mouseEvent) {
        //Open Link From Browser
        try {
            Desktop.getDesktop().browse(new URL("https://mukibwoi.github.io/govidiriya-privacy/").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        calculateAge();
    }

    //Load All Batched to Combo Box
    public void loadBatches(){
        current_cmb.setItems(FXCollections.observableArrayList(
                "DSE 21.1F", "DSE 21.1P"
        ));
    }

    //Load All Branches to Combo Box
    public void loadBranches(){
        current_cmb.setItems(FXCollections.observableArrayList(
                "Colombo", "Galle"
        ));
    }
    //Calculate Age from Birthday
    public void calculateAge(){
        int a = Period.between(datePicker_DOB.getValue(),new Date().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate() ).getYears();
        txt_age.setText(String.valueOf(a));

    }

    //Change Combo Boxes according to Lecturer & Student
    void showCombo(){
        if(Objects.equals(Users.current_user, Users.lecturer)){
            cmb_batch.setVisible(false);
            current_cmb = cmb_branch;
            cmb_validation_message = "Branch required";
            loadBranches();
        }else{
            cmb_branch.setVisible(false);
            current_cmb = cmb_batch;
            cmb_validation_message = "Batch required";
            loadBatches();
        }
    }

    //Validate All Fields
    public void validateAllFields(){
        lbl_FNameError.setText(C_Validation.commonValidator(txt_firstName.getText() , "First Name required"));
        lbl_LNameError.setText(C_Validation.commonValidator(txt_lastName.getText() , "Last Name required"));
        lbl_emailError.setText(C_Validation.validateEmail(txt_email.getText()));
        lbl_DOBError.setText(C_Validation.validateDOB(datePicker_DOB.getValue()));
        lbl_CityError.setText(C_Validation.commonValidator(current_cmb.getValue() == null?
                        null : current_cmb.getValue(),
                cmb_validation_message));
        lbl_createPasswordError.setText(C_Validation.validateNewPass(txt_createPassword.getText() ,lbl_why ));
        lbl_confirmPasswordError.setText(C_Validation.validateConfirmPassword(txt_createPassword.getText() ,
                txt_ConfirmPassword.getText()));
        lbl_tcError.setText(C_Validation.validateTC(checkBox_TC.isSelected()));
    }

    public void passwordWhyOnHovered(MouseEvent mouseEvent) {
    }

    //Set All Fields Null
    private void setFieldNull(){
        txt_Position.setText(Users.current_user);
        txt_firstName.setText(null);
        txt_lastName.setText(null);
        txt_email.setText(null);
    }

    //Send Verify Code With Loading Indicator
    public void sendVerifyCode(
            String purpose , JFXButton currentButton , String nextScreen , ImageView loading_image ,
            String email) throws IOException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                C_VerifyCode.email = email;
                try {
                    Utils.EmailSender.sendCode(email,"Verification code for " + purpose);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.start();

                while(t1.isAlive()){
                    currentButton.setVisible(false);
                    loading_image.setVisible(true);
                }

                loading_image.setVisible(false);
                currentButton.setVisible(true);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            C_VerifyCode.purpose = purpose;
                            C_VerifyCode.nextScreen = nextScreen;
                            new UI().closeUIButton(currentButton);
                            new UI().setUI(Screens.verifyCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }).start();
    }


}
