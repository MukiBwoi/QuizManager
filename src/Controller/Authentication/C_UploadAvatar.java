package Controller.Authentication;

import Constants.Screens;
import Constants.Users;
import Model.Authentication.M_UploadAvatar;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    String email;
    FileInputStream in = null;

    public void initialize(){
        email = C_VerifyCode.email;
    }

    public void btn_NextOnAction(ActionEvent actionEvent) {
        try{
            if(in != null){
                if(M_UploadAvatar.UploadAvatar(in , email , Users.current_user)){
                    new UI().closeUIButton(btn_Next);
                    new UI().setUI(Screens.login);
                }else{
                    new UI().showErrorAlert("Image Not found");
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
            new UI().setUI(Screens.login);
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
                circle_Avatar.setFill(new ImagePattern(new Image(file.toURI().toString())));
                in = new FileInputStream(file);
            }else{
                new UI().showErrorAlert("File Not Selected");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
