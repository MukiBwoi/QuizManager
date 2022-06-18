package Controller.Student;

import Constants.Assets;
import Constants.Screens;
import Model.Authentication.CurrentUserModel;
import Model.Entities.LeadBoardItem;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class C_StudentDashboard {


    public StackPane stackPane_Main;
    public Pane pane_MyProfile;
    public Pane pane_Home;
    public Pane pane_LeadBoard;
    public PieChart piechart_CategoryInterests;
    public JFXListView List_LeadBoard;
    public Circle circle_Avatar;
    public Pane pane_Categories;
    public ImageView img_SidBarIcon;
    public Label lbl_Name;
    public Pane pane_Search;
    public Label lbl_DateTime;


    public void initialize(){
        initPieChart();
        LoadLeadBoard();
        LoadPersonalDetails();
        setCurrentDateTime();
    }



    public void btn_HomeOnAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Node pane:stackPane_Main.getChildren()) {
                    pane.setVisible(false);
                }
                pane_Home.setVisible(true);
            }
        }).run();


    }

    public void btn_CategoryOnAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Node pane:stackPane_Main.getChildren()) {
                    pane.setVisible(false);
                }
                pane_Categories.setVisible(true);
            }
        }).run();
    }

    public void btn_MyProfileOnAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Node pane:stackPane_Main.getChildren()) {
                    pane.setVisible(false);
                }
                pane_MyProfile.setVisible(true);
            }
        }).run();
    }

    public void btn_LeadBoardOnAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Node pane:stackPane_Main.getChildren()) {
                    pane.setVisible(false);
                }
                pane_LeadBoard.setVisible(true);
            }
        }).run();
    }


    public void img_SidebarIconOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Screens.sideBar+ ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene= new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    private void LoadLeadBoard(){
        Node node = null;
        try {
            for (int i = 0; i <10 ; i++) {
                C_LeadBoardCard.leadBoardItem = new LeadBoardItem(
                        new Image("./Assets/avatar.jpg"),"Will Smith",500,1
                );
                node = FXMLLoader.load(getClass().getResource(Screens.LeadBoardCard));
                List_LeadBoard.getItems().add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPieChart(){
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Android" , 15),
                new PieChart.Data("JavaFx" , 30),
                new PieChart.Data("C" , 3)
        );
        piechart_CategoryInterests.setData(piechartData);
    }

    private void LoadPersonalDetails(){
        lbl_Name.setText(CurrentUserModel.student.getLast_name());
        circle_Avatar.setFill(new ImagePattern(new Image(Assets.defaultAvatar)));
    }

    public void OnSearchClicked(MouseEvent mouseEvent) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Node pane:stackPane_Main.getChildren()) {
                        pane.setVisible(false);
                    }
                    pane_Search.setVisible(true);
                }
            }).run();
    }

    public void setCurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy 'at' hh:mm a ");
        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtString = formatter.format(zdt);
        lbl_DateTime.setText(zdtString);
    }
}
