package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    public void lbl_LoginOnAction(MouseEvent mouseEvent) {
    }

    public void btn_RegisterOnAction(ActionEvent actionEvent) {
    }

    public void lbl_forgotPasswordOnAction(MouseEvent mouseEvent) {
    }
}
