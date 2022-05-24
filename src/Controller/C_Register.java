package Controller;

import Utils.UI;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class C_Register {
    public JFXTextField txt_email;
    public Label lbl_Login;
    public Button btn_Register;
    public Label lbl_forgotPassword;
    public JFXTextField txt_firstName;
    public JFXPasswordField txt_createPassword;
    public JFXTextField txt_lastName;
    public JFXDatePicker datePicker_DOB;
    public JFXComboBox cmb_city;
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

    UI ui = new UI();
    public void initialize(){
        txt_Position.setText(C_Login.userType);
        txt_firstName.setText(null);
        txt_lastName.setText(null);
        txt_email.setText(null);

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
        System.out.println(txt_lastName.getText());System.out.println(txt_firstName.getText());
        System.out.println(txt_email.getText());

        if(txt_firstName.getText() == null || txt_firstName.getText() == ""){
                lbl_FNameError.setText("First name required");
        }
        if(txt_lastName.getText() == null || txt_lastName.getText() == ""){
            lbl_LNameError.setText("Last name required");
        }
        if(txt_email.getText() == null || txt_email.getText() == ""){
            lbl_emailError.setText("Email required");
        }
        if(datePicker_DOB.getValue() == null){
            lbl_DOBError.setText("Date Of Birth required");
        }
        if(cmb_city.getValue() == null){
            lbl_CityError.setText("City is required");
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

    public void loadCities(){}

    public void calculateAge(){
        int a = Period.between(datePicker_DOB.getValue(),new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ).getYears();
        txt_age.setText(String.valueOf(a));

    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        calculateAge();
    }
}
