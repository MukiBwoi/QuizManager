package Controller.Common;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.Authentication.C_Register;
import Model.Entities.AuthUser;
import Model.Database.UserService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.control.Label;

public class C_Validation {

    //Using for validate null values
    public static String commonValidator(String input , String message){
        if(input == null || input == "" || input.equals(null) || input.isEmpty()){
            C_Register.isValidated = false;
            return message;
        }
        C_Register.isValidated = true;
        return null;
    }


    private static boolean isValidMail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String validateEmail(String email){
        String isNullMessage = commonValidator(email , "Email address required!");
        if(isNullMessage != null){
            C_Register.isValidated = false;
            return  isNullMessage;
        }else if(!isValidMail(email)){
            C_Register.isValidated = false;
            return "Invalid email format!";
        }else{
            C_Register.isValidated = true;
            return  null;
        }
    }


    public static String validateDOB(LocalDate localDate){
        if(localDate == null  || localDate.equals(null)){
            C_Register.isValidated = false;
            return "Date Of Birth required!";
        }
        C_Register.isValidated = true;
        return null;
    }


    public static String validateNewPass(String password , Label why){
        if(password == null || password.equals(null)){
            C_Register.isValidated = false;
            return  "Password required!";
        }
        else if(!Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$").matcher(password).matches()){
            why.setVisible(true);
            C_Register.isValidated = false;
            return "Password format error!";
        }else{
            why.setVisible(false);
            C_Register.isValidated = true;
            return null;
        }
    }


    public static String validateConfirmPassword(String npassword , String cpassword){
        if(!npassword.equalsIgnoreCase(cpassword)){
            C_Register.isValidated = false;
            return "Password doesn't match!";
        }else{
            C_Register.isValidated = true;
            return  null;
        }
    }


    public static String validateTC(boolean isChecked){
        if(!isChecked){
            C_Register.isValidated = false;
            return  "you have to agree to Terms and conditions before continue!";
        }
        C_Register.isValidated = true;
        return  null;
    }



    public static boolean isEmailExist(String email){
        try{
                for (AuthUser authUser: UserService.getAllAuthUsers()) {
                        if(authUser.getEmail().equalsIgnoreCase(email)){
                            return  true;
                        }
                }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
       return  false;

    }

    public static boolean requiredValidator(JFXTextField textField , String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        textField.setValidators(validator);
        textField.getValidators().get(0).setMessage(message);
        return textField.validate();
    }
    public static boolean requiredValidator(JFXTextArea textField , String message){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        textField.setValidators(validator);
        textField.getValidators().get(0).setMessage(message);
        return textField.validate();
    }

    public static boolean numberValidator(JFXTextField textField , String filedName){
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        NumberValidator numberValidator = new NumberValidator();
        textField.setValidators(requiredFieldValidator,numberValidator);
        textField.getValidators().get(0).setMessage(filedName + " required ");
        textField.getValidators().get(1).setMessage(filedName + " must be a number");
        return textField.validate();
    }
}
