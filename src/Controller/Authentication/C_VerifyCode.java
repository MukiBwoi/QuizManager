package Controller.Authentication;


import Controller.Common.C_Validation;
import Utils.EmailSender;
import Utils.ErrorHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.mail.MessagingException;
import java.io.IOException;


public class C_VerifyCode{
    public JFXButton btn_VerifyCode;
    public JFXButton btn_ResendCode;
    static String email;
    public JFXTextField txt_verificationCode;
    public Label lbl_timerTxt;
    public Label lbl_Email;
    public JFXButton btn_ChangeEmail;
    public ImageView img_loadingIndicator;
    public static String nextScreen;
    public static String purpose;

    public void initialize(){
        lbl_Email.setText(email);
        txt_verificationCode.setText(null);
    }

    public void btn_VerifyCodeOnAction(ActionEvent actionEvent) {
        String validator = C_Validation.commonValidator(txt_verificationCode.getText(),
                "verification code required !");
        if(validator!= null){
            ErrorHandler.setError(validator);
            new Alert(Alert.AlertType.ERROR , ErrorHandler.getMessage());

        }else{
            if(txt_verificationCode.getText().equals(String.valueOf(EmailSender.code))){

                try {
                    if(purpose == "Reset Password"){
                        C_ChangePassword.email = email;
                    }
                    new Utils.UI().closeUIButton(btn_VerifyCode);
                    new Utils.UI().setUI(nextScreen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
               new Utils.UI().showErrorAlert("Wrong verification code !");
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
                for (int i = 59; i >= 0; i--) {
                    String j = String.valueOf(i);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lbl_timerTxt.setText(j + " Sec");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lbl_timerTxt.setText("59 Sec");
                    }
                });
                btn_ResendCode.setDisable(false);

            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    btn_ResendCode.setDisable(true);
                    EmailSender.sendCode(email,"Verification code for " + purpose);
                    thread.start();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.start();
                while (t1.isAlive()){
                    btn_ResendCode.setVisible(false);
                    img_loadingIndicator.setVisible(true);
                }
                btn_ResendCode.setVisible(true);
                img_loadingIndicator.setVisible(false);
            }
        }).start();

    }

    public void btn_ChangeEmailOnAction(ActionEvent actionEvent) {
    }
}
