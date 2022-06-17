package Controller.Student;

import Constants.Screens;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class C_StudentDashboard {

    public Label lbl_Home;
    public StackPane stackPane_Main;
    public Pane pane_MyProfile;
    public Pane pane_Home;
    public Pane pane_LeadBoard;
    public Label lbl_MyProfile;
    public Label lbl_LeadBoard;
    public PieChart piechart_CategoryInterests;
    public JFXListView List_LeadBoard;

    public void initialize(){
        initPieChart();
        Node node = null;
        try {
            for (int i = 0; i <10 ; i++) {
                node = FXMLLoader.load(getClass().getResource(Screens.LeadBoardCard));
                List_LeadBoard.getItems().add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void lbl_HomeOnAction(MouseEvent mouseEvent) {
            pane_Home.setVisible(true);
    }

    public void lbl_MyProfileOnAction(MouseEvent mouseEvent) {
        pane_Home.setVisible(false);
        pane_LeadBoard.setVisible(false);
    }

    public void lbl_LeadBoarOnAction(MouseEvent mouseEvent) {
        pane_Home.setVisible(false);
        pane_LeadBoard.setVisible(true);
    }


    private void initPieChart(){
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Android" , 15),
                new PieChart.Data("JavaFx" , 30),
                new PieChart.Data("C" , 3)
        );
        piechart_CategoryInterests.setData(piechartData);
    }


}
