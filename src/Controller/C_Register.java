package Controller;

import Constants.Screens;
import Constants.Users;
import Model.*;
import Utils.UI;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private  String cmb_validation_message;
    public static boolean isValidated = false;



    UI ui = new UI();
    public void initialize(){

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

            try {
                boolean isRegistered;
                if(Users.current_user == Users.student){
                    isRegistered = M_Register.registerStudent(
                            new Student(txt_firstName.getText(),txt_lastName.getText() , txt_email.getText(),
                                    java.sql.Date.valueOf(datePicker_DOB.getValue()),Integer.parseInt(txt_age.getText()),
                                    cmb_batch.getValue(),1,txt_ConfirmPassword.getText(),txt_Position.getText(),
                                    Timestamp.valueOf(LocalDateTime.now()))
                    );
                }else{
                    isRegistered = M_Register.registerLecturer(
                            new Lecturer(txt_firstName.getText(),txt_lastName.getText() , txt_email.getText(),
                                    cmb_branch.getValue(),0,txt_ConfirmPassword.getText(),txt_Position.getText(),
                                    Timestamp.valueOf(LocalDateTime.now())));
                }

                if(isRegistered){
                    Stage stage =  (Stage)btn_Register.getScene().getWindow();
                    stage.close();
                    ui.setUI(Screens.dashboard);
                }else{
                    ErrorHandler.setError("Something went wrong please try again !");
                    Alert alert = new Alert(Alert.AlertType.ERROR ,ErrorHandler.getMessage());
                    alert.show();
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                ErrorHandler.setError(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR ,ErrorHandler.getMessage());
                alert.show();
                e.printStackTrace();
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

    public void loadBatches(){
        current_cmb.setItems(FXCollections.observableArrayList(
                "DSE 21.1F", "DSE 21.1P"
        ));
    }

    public void loadBranches(){
        current_cmb.setItems(FXCollections.observableArrayList(
                "Colombo", "Galle"
        ));
    }

    public void calculateAge(){
        int a = Period.between(datePicker_DOB.getValue(),new Date().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate() ).getYears();
        txt_age.setText(String.valueOf(a));

    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        calculateAge();
    }

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

    public void validateAllFields(){
        lbl_FNameError.setText(ValidationModel.commonValidator(txt_firstName.getText() , "First Name required"));
        lbl_LNameError.setText(ValidationModel.commonValidator(txt_lastName.getText() , "Last Name required"));
        lbl_emailError.setText(ValidationModel.validateEmail(txt_email.getText()));
        //lbl_DOBError.setText(ValidationModel.validateDOB(datePicker_DOB.getValue()));
        lbl_CityError.setText(ValidationModel.commonValidator(current_cmb.getValue() == null?
                        null : current_cmb.getValue(),
                cmb_validation_message));
        lbl_createPasswordError.setText(ValidationModel.validateNewPass(txt_createPassword.getText() ,lbl_why ));
        lbl_confirmPasswordError.setText(ValidationModel.validateConfirmPassword(txt_createPassword.getText() ,
                txt_ConfirmPassword.getText()));
        lbl_tcError.setText(ValidationModel.validateTC(checkBox_TC.isSelected()));
    }

    public void passwordWhyOnHovered(MouseEvent mouseEvent) {
    }

    private void setFieldNull(){
        txt_Position.setText(Users.current_user);
        txt_firstName.setText(null);
        txt_lastName.setText(null);
        txt_email.setText(null);
    }
}
