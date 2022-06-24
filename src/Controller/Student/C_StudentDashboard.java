package Controller.Student;

import Constants.Screens;
import Controller.Authentication.C_UploadAvatar;
import Model.Authentication.CurrentUserModel;
import Model.Entities.LeadBoardItem;
import Model.Entities.Student;
import Model.Entities.TestData;
import Model.Entities.MyTest;
import Model.Database.TestService;
import Utils.UI;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    public JFXSpinner spinner_Done;
    public JFXSpinner spinner_AverageMarks;
    public JFXButton btn_ChangeProfilePic;


    public void initialize(){
        initPieChart();
        LoadLeadBoard();
        LoadPersonalDetails();
        setCurrentDateTime();
        LoadTestGrid();
        LoadMyTestList();


    }



    public void btn_HomeOnAction(ActionEvent actionEvent) {
       new UI().NavigatePane(stackPane_Main,pane_Home);
    }

    public void btn_MyProfileOnAction(ActionEvent actionEvent) {
        new UI().NavigatePane(stackPane_Main, pane_MyProfile);
        LoadMyTestList();
        UI.progressBarAnimation(spinner_Done,0.24);
        UI.progressBarAnimation(spinner_AverageMarks,0.97);


    }


    public void txt_OnSearchAction(MouseEvent mouseEvent) {
        new UI().NavigatePane(stackPane_Main,pane_Search);
        LoadTestGrid();
    }

    public void LoadMyTestList(){
        listView_MyTests.setOrientation(Orientation.HORIZONTAL);
        try {
            if(TestService.getTestTiles().size()>0){
                for (MyTest testile: TestService.testTiles) {
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

    public void LoadPersonalDetails(){
        Student student = CurrentUserModel.student;
        lbl_Name.setText(student.getLast_name());
        circle_Avatar.setFill(UI.pattern(new Image(student.getAvatar().toURI().toString()),20));
        lbl_fullName.setText(student.getFirst_name()+" "+student.getLast_name());
        lbl_Email.setText(student.getEmail());
        lbl_Batch.setText(student.getBatch());
        lbl_Age.setText(String.valueOf(student.getAge()));
        circle_ProfilePic.setFill(UI.pattern(new Image(student.getAvatar().toURI().toString()),65));
    }


    public void setCurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy 'at' hh:mm a ");
        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtString = formatter.format(zdt);
        lbl_DateTime.setText(zdtString);
    }

    public void LoadTestGrid(){
        try {
            if(TestService.getTestTiles().size()>0){
                Node node;
                ArrayList<ArrayList<MyTest>> gridTests = moveTo2DArray();
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

    public ArrayList<ArrayList<MyTest>> moveTo2DArray(){

        ArrayList<ArrayList<MyTest>> grid = new ArrayList<>();
        grid.clear();
        try {
            TestService.getTestTiles();
            if(TestService.testTiles.size() %2 !=0){
                TestService.testTiles.add(new MyTest(
                        new TestData("Sample Test","Sample author","Sample category",10,5),0.0,false
                ));
            }

            for(int i = 0; i< TestService.testTiles.size()/2; i++) {

                for (int j = 0; j < 2; j++) {
                    if(j < 1){
                        grid.add(new ArrayList<>());
                    }
                    grid.get(i).add(TestService.testTiles.get((i*2)+j));
                }
            }
        } catch (SQLException | ClassNotFoundException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return  grid;
    }


    public void btn_LogoutOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Logout);
            CurrentUserModel.student = null;
            CurrentUserModel.isLoggedIn = false;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_ChangeProfilePicOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_ChangeProfilePic);
            C_UploadAvatar.email = CurrentUserModel.student.getEmail();
            C_UploadAvatar.nextScreen = Screens.studentDashboard;
            new UI().setUI(Screens.uploadAvatar);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
