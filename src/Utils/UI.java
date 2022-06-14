package Utils;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class UI {

    public  void setUI(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location+ ".fxml"))));
        stage.setResizable(false);
        stage.show();
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
}
