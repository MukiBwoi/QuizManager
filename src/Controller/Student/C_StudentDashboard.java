package Controller.Student;

import Constants.Assets;
import Constants.Screens;
import Model.Authentication.CurrentUserModel;
import Model.Entities.LeadBoardItem;
import Model.Entities.TestTile;
import Model.Student.M_GridTestTiles;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


public class C_StudentDashboard {


    public StackPane stackPane_Main;
    public Pane pane_MyProfile;
    public Pane pane_Home;
    public PieChart piechart_CategoryInterests;
    public JFXListView List_LeadBoard;
    public Circle circle_Avatar;
    public Label lbl_Name;
    public Pane pane_Search;
    public Label lbl_DateTime;
    public JFXTextField txt_Search;
    public GridPane gridView_TestGrid;
    public StackPane stackPane_TestGrid;
    public Label lbl_NoResultFound;
    public ScrollPane scrollPane_TestGrid;
    public AnchorPane anchorPane_Root;
    public JFXButton btn_Home;
    public JFXButton btn_MyProfile;
    public JFXButton btn_Logout;
    public JFXListView listView_MyTests;
    public Label lbl_Email;
    public Label lbl_Batch;
    public Label lbl_fullName;
    public Circle circle_ProfilePic;
    public Label lbl_Age;


    public void initialize(){
        initPieChart();
        LoadLeadBoard();
        LoadPersonalDetails();
        setCurrentDateTime();
        LoadTestGrid();

        LoadMyTestList();

    }

    public void NavigatePane(Pane nextPane){
        new Thread(() -> {
            for (Node pane : stackPane_Main.getChildren()) {
                pane.setVisible(false);
            }
            nextPane.setVisible(true);
        }).start();
    }

    public void btn_HomeOnAction(ActionEvent actionEvent) {
       NavigatePane(pane_Home);
    }

    public void btn_MyProfileOnAction(ActionEvent actionEvent) {
        NavigatePane(pane_MyProfile);
    }


    public void txt_OnSearchAction(MouseEvent mouseEvent) {
        NavigatePane(pane_Search);
        LoadTestGrid();
    }

    public void LoadMyTestList(){
        try {
            if(M_GridTestTiles.getTestTiles().size()>0){
                for (TestTile testile:M_GridTestTiles.testTiles) {
                    C_GridTestItem.testTile = testile;
                    listView_MyTests.getItems().add(FXMLLoader
                            .load(getClass().getResource(Screens.gridTestItem+".fxml")));
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
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
        lbl_fullName.setText(CurrentUserModel.student.getFirst_name()+" "+CurrentUserModel.student.getLast_name());
        lbl_Email.setText(CurrentUserModel.student.getEmail());
        lbl_Batch.setText(CurrentUserModel.student.getBatch());
        lbl_Age.setText(String.valueOf(CurrentUserModel.student.getAge()));
        circle_ProfilePic.setFill(new ImagePattern(new Image(Assets.defaultAvatar)));
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
                ArrayList<ArrayList<TestTile>> gridTests = moveTo2DArray();
                for(int i= 0;i<gridTests.size();i++){
                    for (int j = 0; j < 2; j++) {
                        C_GridTestItem.testTile = gridTests.get(i).get(j);
                        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                                Screens.gridTestItem + ".fxml")));
                        gridView_TestGrid.add(node ,j ,i);
                    }
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

    public ArrayList<ArrayList<TestTile>> moveTo2DArray(){

        ArrayList<ArrayList<TestTile>> grid = new ArrayList<>();
        grid.clear();
        try {
            M_GridTestTiles.getTestTiles();
            if(M_GridTestTiles.testTiles.size() %2 !=0){
                M_GridTestTiles.testTiles.add(new TestTile(
                        "Sample Test","Sample author","Sample category",0.0,false
                ));
            }

            for(int i=0;i<M_GridTestTiles.testTiles.size()/2;i++) {

                for (int j = 0; j < 2; j++) {
                    if(j < 1){
                        grid.add(new ArrayList<>());
                    }
                    grid.get(i).add(M_GridTestTiles.testTiles.get((i*2)+j));
                }
            }
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return  grid;
    }


    public void btn_LogoutOnAction(ActionEvent actionEvent) {
    }
}
