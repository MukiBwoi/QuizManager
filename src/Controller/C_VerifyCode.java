package Controller;

import Constants.Screens;
import Model.ValidationModel;
import Utils.EmailSender;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class C_VerifyCode{
    public JFXButton btn_VerifyCode;
    public JFXButton btn_ResendCode;
    static String email;
    public JFXTextField txt_verificationCode;
    public Label lbl_timerTxt;

    public void btn_VerifyCodeOnAction(ActionEvent actionEvent) {
        String validator = Model.ValidationModel.commonValidator(txt_verificationCode.getText(),
                "verification code required !");
        if(validator!= null){
            Model.ErrorHandler.setError(validator);
            new Alert(Alert.AlertType.ERROR , Model.ErrorHandler.getMessage());

        }else{
            Stage stage =  (Stage)btn_VerifyCode.getScene().getWindow();
            stage.close();
            try {
                new Utils.UI().setUI(Screens.dashboard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btn_ResendCodeOnAction(ActionEvent actionEvent) {
        try {
            sendVerifyCode();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    private void sendVerifyCode() throws MessagingException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 60; i >= 0; i--) {
                    String j = String.valueOf(i);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            lbl_timerTxt.setText(j + " Seconds");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                btn_ResendCode.setDisable(false);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    btn_ResendCode.setDisable(true);
                    EmailSender.sendCode(email,"Verification code for Registration");
                    thread.start();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        }).start();




    }
}
