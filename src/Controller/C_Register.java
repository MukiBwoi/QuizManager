package Controller;

import Constants.Users;
import Model.ValidationModel;
import Utils.UI;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class C_Register {

    public JFXTextField txt_email;
    public Label lbl_Login;
    public Button btn_Register;
    public JFXTextField txt_firstName;
    public JFXPasswordField txt_createPassword;
    public JFXTextField txt_lastName;
    public JFXDatePicker datePicker_DOB;
    public JFXTextField txt_age;
    public CheckBox checkBox_TC;
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
    public JFXComboBox cmb_batch;
    public JFXComboBox cmb_branch;
    public JFXComboBox current_cmb;
    public Label lbl_why;
    private  String cmb_validation_message;
    public static boolean isValidated = false;

    UI ui = new UI();
    public void initialize(){
        txt_Position.setText(Users.current_user);
        txt_firstName.setText(null);
        txt_lastName.setText(null);
        txt_email.setText(null);
        showCombo();

    }
    public void lbl_LoginOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage =  (Stage)lbl_Login.getScene().getWindow();
            stage.close();
            ui.setUI("/View/V_Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_RegisterOnAction(ActionEvent actionEvent) {
        validateAllFields();
        if(isValidated){
            try {
                Stage stage =  (Stage)btn_Register.getScene().getWindow();
                stage.close();
                ui.setUI("/View/V_Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void lbl_TermsAndConditionOnAction(MouseEvent mouseEvent) {
        //Open Link From Browser
        try {
            Desktop.getDesktop().browse(new URL("https://mukibwoi.github.io/govidiriya-privacy/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
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
        if(Users.current_user == Users.lecturer){
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
                        null : current_cmb.getValue().toString() ,
                cmb_validation_message));
        lbl_createPasswordError.setText(ValidationModel.validateNewPass(txt_createPassword.getText() ,lbl_why ));
        lbl_confirmPasswordError.setText(ValidationModel.validateConfirmPassword(txt_createPassword.getText() ,
                txt_ConfirmPassword.getText()));
        lbl_tcError.setText(ValidationModel.validateTC(checkBox_TC.isSelected()));
    }

    public void passwordWhyOnHovered(MouseEvent mouseEvent) {
    }
}
