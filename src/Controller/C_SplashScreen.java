package Controller;

import Utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class C_SplashScreen {

    public Button btn_start;

    public void btn_start(ActionEvent actionEvent) {
            Stage stage = (Stage) btn_start.getScene().getWindow();
            stage.close();
        try {
           new UI().setUI("/View/V_Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
