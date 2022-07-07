package Controller.Authentication;

import Constants.Screens;
import Constants.Users;
import Controller.Student.C_StudentDashboard;
import Model.Authentication.CurrentUserModel;
import Model.Authentication.M_UploadAvatar;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class C_UploadAvatar {
    public Circle circle_Avatar;
    public JFXButton btn_Next;
    public JFXButton btn_Skip;
    public ImageView img_AddImage;
    public static String email = C_VerifyCode.email;
    public AnchorPane rootPane;
    FileInputStream in = null;
    public static String nextScreen = Screens.login;

    public void initialize(){
       // ifLoggedIn();
    }

    public void btn_NextOnAction(ActionEvent actionEvent) {
        try{
            if(in != null){
                if(M_UploadAvatar.UploadAvatar(in , email , Users.current_user)){
                    CurrentUserModel.getCurrentUser();
                    new UI().closeUIButton(btn_Next);
                    new UI().setUI(nextScreen);
                }else{
                    new UI().showErrorAlert(rootPane,"Image Not found");
                }
            }
        }catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void btn_SkipOnAction(ActionEvent actionEvent) {
        try {
            new UI().closeUIButton(btn_Skip);
            new UI().setUI(nextScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void img_AddImageOnAction(MouseEvent mouseEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            Stage stage = (Stage)img_AddImage.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if(file != null){
                circle_Avatar.setFill(UI.pattern(new Image(file.toURI().toString()),80));
                in = new FileInputStream(file);
            }else{
                new UI().showErrorAlert(rootPane,"File Not Selected");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ifLoggedIn(){
        try{
            CurrentUserModel.getCurrentUser();
            if(Users.current_user.equals(Users.student)){
                circle_Avatar.setFill(UI.pattern(
                        new Image(CurrentUserModel.student.getAvatar().toString()),80));

            }else if(Users.current_user.equals(Users.lecturer)){
                circle_Avatar.setFill(UI.pattern(
                        new Image(CurrentUserModel.student.getAvatar().getAbsolutePath()),80));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
