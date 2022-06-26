package Utils;
import Model.Authentication.CurrentUserModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSpinner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class UI {

    public  Stage setUI(String location) throws IOException {
        Stage stage = new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location+ ".fxml"))));
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            CurrentUserModel.isLoggedIn = false;
            Platform.exit();
        });
        stage.show();

        return  stage;
    }

    public  void closeUIButton(JFXButton button){
        Stage stage =  (Stage)button.getScene().getWindow();
        stage.close();
    }
    public  void closeUILabel(Label label){
        Stage stage =  (Stage)label.getScene().getWindow();
        stage.close();
    }

    public void showErrorAlert(String message){
        ErrorHandler.setError(message);
        new Alert(Alert.AlertType.ERROR , ErrorHandler.getMessage()).show();
    }

    public void showSuccessAlert(String message){
        ErrorHandler.setError(message);
        new Alert(Alert.AlertType.INFORMATION , ErrorHandler.getMessage()).show();
    }



    public static void NavigatePane(StackPane rootPane, Pane nextPane){
        new Thread(() -> {
            for (Node pane : rootPane.getChildren()) {
                pane.setVisible(false);
            }
            nextPane.setVisible(true);
        }).start();
    }

    //Overloading
    public static void NavigatePane(StackPane rootPane, ScrollPane nextPane){
        new Thread(() -> {
            for (Node pane : rootPane.getChildren()) {
                pane.setVisible(false);
            }
            nextPane.setVisible(true);
        }).start();
    }

    public static void progressBarAnimation(JFXSpinner spinner , double value){
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(spinner.progressProperty(),0)
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(spinner.progressProperty(),value)
                )
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public static ImagePattern pattern(Image img, double radius) {
        double hRad = radius;   // horizontal "radius"
        double vRad = radius;   // vertical "radius"
        if (img.getWidth() != img.getHeight()) {
            double ratio = img.getWidth() / img.getHeight();
            if (ratio > 1) {
                // Width is longer, left anchor is outside
                hRad = radius * ratio;
            } else {
                // Height is longer, top anchor is outside
                vRad = radius / ratio;
            }
        }
        return new ImagePattern(img, -hRad, -vRad, 2 * hRad, 2 * vRad, false);
    }

    public static void showSnack(AnchorPane rootPane , String message){
        JFXSnackbar snackbar = new JFXSnackbar(rootPane);
        final JFXSnackbar.SnackbarEvent snackbarEvent = new JFXSnackbar.SnackbarEvent(message);
        snackbar.enqueue(snackbarEvent);
    }


}
