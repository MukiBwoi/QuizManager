package Utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UI {

    public  void setUI(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location+ ".fxml"))));
        stage.setResizable(false);
        stage.show();
    }
}
