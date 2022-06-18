package Controller.Student;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class C_SideBar {
    public ImageView img_Close;

    public void img_CloseOnAction(MouseEvent mouseEvent) {
        Stage stage =  (Stage)img_Close.getScene().getWindow();
        stage.close();
    }
}
