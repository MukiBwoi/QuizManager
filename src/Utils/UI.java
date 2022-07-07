package Utils;
import Constants.Widgets;
import Controller.Common.DialogBox.C_DialogHeading;
import Model.Authentication.CurrentUserModel;
import com.jfoenix.controls.*;
import com.sun.istack.internal.Nullable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UI {

    public  Stage setUI(String location) throws IOException {
        Stage stage = new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location+ ".fxml"))));
        stage.setResizable(false);
        stage.show();

        return  stage;
    }

    public  Stage setUIWithLogout(String location) throws IOException {
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

    public  Stage setUIWithInitiModility(String location) throws IOException {
        Stage stage = new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location+ ".fxml"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
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

    public void showErrorAlert(AnchorPane rootPane , String message){
        ErrorHandler.setError(message);
        showDialog(rootPane , "Error Occured !" , message);
        //new Alert(Alert.AlertType.ERROR , ErrorHandler.getMessage()).show();
    }

    public void showSuccessAlert(AnchorPane rootPane , String message){
        ErrorHandler.setError(message);
        showDialog(rootPane , "Success !" , message);
        //new Alert(Alert.AlertType.INFORMATION , ErrorHandler.getMessage()).show();
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

    public static byte[] imageToByte(Image image) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte [] data = output.toByteArray();
        return data;
    }

    public void showDialog(AnchorPane rootPane, String heading , String body){
        JFXDialogLayout content= new JFXDialogLayout();
        content.setPrefWidth(300);
            content.setHeading(new Text(heading));
            content.setBody(new Text(body));
            StackPane stackpane = new StackPane();
            stackpane.setStyle("-fx-background-color: transparent");
            stackpane.setPrefSize(rootPane.getPrefWidth(),rootPane.getPrefHeight());
            rootPane.getChildren().add(stackpane);
            JFXDialog dialog =new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button=new JFXButton("Okay");
            button.setStyle("-fx-background-color: #2196f3;-fx-text-fill: #ffffff");
            button.setOnAction(event -> {
                dialog.close();
                rootPane.getChildren().remove(stackpane);
            });
            content.setActions(button);
            dialog.show();
    }
}
