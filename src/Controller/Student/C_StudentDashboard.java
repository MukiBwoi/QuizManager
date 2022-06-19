package Controller.Student;

import Constants.Assets;
import Constants.Screens;
import Model.Authentication.CurrentUserModel;
import Model.Entities.LeadBoardItem;
import Model.Entities.TestTile;
import Model.Student.M_GridTestTiles;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    public JFXTextField txt_Search;
    public GridPane gridView_TestGrid;
    public StackPane stackPane_TestGrid;
    public Label lbl_NoResultFound;
    public ScrollPane scrollPane_TestGrid;


    public void initialize(){
        initPieChart();
        LoadLeadBoard();
        LoadPersonalDetails();
        setCurrentDateTime();
        LoadTestGrid();


    }

    public void NavigatePane(Pane nextPane){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Node pane:stackPane_Main.getChildren()) {
                    pane.setVisible(false);
                }
                nextPane.setVisible(true);
            }
        }).run();
    }

    public void btn_HomeOnAction(ActionEvent actionEvent) {
       NavigatePane(pane_Home);
    }

    public void btn_CategoryOnAction(ActionEvent actionEvent) {
        NavigatePane(pane_Categories);
    }

    public void btn_MyProfileOnAction(ActionEvent actionEvent) {
        NavigatePane(pane_MyProfile);
    }

    public void btn_LeadBoardOnAction(ActionEvent actionEvent) {
        NavigatePane(pane_LeadBoard);
    }

    public void txt_OnSearchAction(MouseEvent mouseEvent) {
        NavigatePane(pane_Search);
        LoadTestGrid();
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
        gridView_TestGrid.getChildren().clear();
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


    public void setCurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy 'at' hh:mm a ");
        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtString = formatter.format(zdt);
        lbl_DateTime.setText(zdtString);
    }

    public void LoadTestGrid(){
        try {
            if(M_GridTestTiles.getTestTiles().size()>0){
                Node node;
                int k=0;
                int testLength = M_GridTestTiles.testTiles.size();

                if(M_GridTestTiles.testTiles.size()%2==0){
                    testLength = testLength/2;
                }else{
                    testLength = (int)(((double)testLength/2) + 0.5);
                }

                for (int i = 0; i < testLength; i++) {
                    ArrayList<TestTile> tests = new ArrayList<>();
                    tests.clear();
                    if(M_GridTestTiles.testTiles.size()>1){
                        tests.add(M_GridTestTiles.testTiles.get(k));
                        tests.add(M_GridTestTiles.testTiles.get(k+1));
                    }else{
                        tests.add(M_GridTestTiles.testTiles.get(k));
                    }

                    for (int j = 0;j<tests.size();j++){
                        C_GridTestItem.testTile = tests.get(j);
                        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                                Screens.gridTestItem + ".fxml")));
                        gridView_TestGrid.add(node ,j ,i);
                    }
                    if(i>=4){
                        gridView_TestGrid.setPrefHeight(gridView_TestGrid.getPrefHeight()+250);
                    }
                   k = k+2;
                }
            }else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (Node pane:stackPane_TestGrid.getChildren()) {
                            pane.setVisible(false);
                        }
                        lbl_NoResultFound.setVisible(true);
                    }
                }).run();
            }
        } catch (SQLException | ClassNotFoundException |IOException e) {
            e.printStackTrace();
        }

    }
}
