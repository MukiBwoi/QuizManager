package Controller.Student;

import Constants.Screens;
import Controller.Authentication.C_UploadAvatar;
import Controller.Common.C_LeadBoardCard;
import Model.Authentication.CurrentUserModel;
import Model.Database.CategoryService;
import Model.Database.LeadBoardService;
import Model.Entities.LeadBoardCard;
import Model.Entities.Student;
import Model.Entities.Test;
import Model.Entities.MyTest;
import Model.Database.TestService;
import Utils.Helpers;
import Utils.UI;
import com.jfoenix.controls.*;
import com.sun.istack.internal.Nullable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.*;


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
    public Label lbl_FinishedTests;
    public Label lbl_totalMarks;
    public Label lbl_Rank;
    public VBox vBox_SearchList;
    public JFXListView listView_CategoryList;
    int mytestCount = 0;
    int marks = 0;

    public void initialize(){
        initPieChart();
        LoadLeadBoard();
        LoadPersonalDetails();
        setCurrentDateTime();
        loadCategories();
        LoadTestTiles(null);
        loadDashboardTiles();


    }



    public void btn_HomeOnAction(ActionEvent actionEvent) {
        new UI().NavigatePane(stackPane_Main,pane_Home);
    }

    public void btn_MyProfileOnAction(ActionEvent actionEvent) {
        new UI().NavigatePane(stackPane_Main, pane_MyProfile);
        LoadMyTestList();
        UI.progressBarAnimation(spinner_Done,calculateFinishTestPercentage());
        UI.progressBarAnimation(spinner_AverageMarks,calculateAverageMarks());


    }

    public double calculateAverageMarks(){
        try{
            return Helpers.round(marks/mytestCount,2);
        }catch (ArithmeticException ex){
            ex.printStackTrace();
        }
            return 0;
    }

    public void txt_OnSearchAction(MouseEvent mouseEvent) {
        new UI().NavigatePane(stackPane_Main,pane_Search);
        LoadTestTiles(null);
    }

    public void LoadMyTestList(){
        listView_MyTests.getItems().clear();
        listView_MyTests.setOrientation(Orientation.HORIZONTAL);

        try {

            if(TestService.getMyTests(CurrentUserModel.student.getAuth_id()).size()>0){

                for (MyTest testile: TestService.myTests) {

                    C_GridTestItem.myTest = testile;
                    listView_MyTests.getItems().add(FXMLLoader
                            .load(getClass().getResource(Screens.gridTestItem+".fxml")));

                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void LoadLeadBoard(){
        List_LeadBoard.getItems().clear();
        Node node = null;
        try {
            if(LeadBoardService.getLeadBoardCards().size()>0){

                for (LeadBoardCard leadBoardCard : LeadBoardService.leadBoardCards) {
                    C_LeadBoardCard.leadBoardCard = leadBoardCard;
                    node = FXMLLoader.load(getClass().getResource(Screens.leadBoardCard + ".fxml"));
                    List_LeadBoard.getItems().add(node);
                }
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initPieChart(){
        try {
            List<String> categories = new ArrayList<String>();
            List<String> uniqueCategories = new ArrayList<>();
            TestService.getMyTests(CurrentUserModel.student.getAuth_id()).forEach( myTest ->categories
                    .add(myTest.getTestData().getCategory()));
            Map<String ,Integer>  categoryCount = new HashMap<String , Integer>();
            categories.forEach(category->{
                if(!uniqueCategories.contains(category)){
                    uniqueCategories.add(category);
                }
            });
            final int[] i = {0};
            uniqueCategories.forEach(uniqueCategory->{

                categories.forEach(category->{
                    if(Objects.equals(category, uniqueCategory)){
                        i[0]++;
                    }
                });
                categoryCount.put(uniqueCategory , i[0]);
                i[0] = 0;
            });
            ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
            categoryCount.forEach((k,v)->{
                piechartData.add(new PieChart.Data(k,v));
            });
            piechart_CategoryInterests.setData(piechartData);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    public void LoadTestTiles(@Nullable  String category){

        try {
            C_GridTestItem.myTest = null;
            C_GridTestItem.test = null;
            if(TestService.getTestsByCategory(category).size()>0){

                vBox_SearchList.getChildren().clear();
                TestService.testListByCategory.forEach(test -> {
                    try {
                        TestService.getMyTests(CurrentUserModel.student.getAuth_id()).forEach(myTest -> {
                            if(myTest.getTestData().getId() == test.getId()){
                                try {
                                    addTestItem(myTest,null);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        if(C_GridTestItem.myTest == null){
                            addTestItem(null,test);
                        }

                    } catch (IOException | SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public double calculateFinishTestPercentage(){
        try {
            double percentage = (double)mytestCount/(double)TestService.getTests().size();
            return percentage;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
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

    private void loadDashboardTiles(){
        try {



            for (MyTest myTest:TestService.getMyTests(CurrentUserModel.student.getAuth_id())) {
                    marks = marks + (int) (myTest.getMarks() * 10.0);
                    mytestCount += 1;
            }
            lbl_FinishedTests.setText(mytestCount+"");
            lbl_totalMarks.setText(marks+"");
            if(TestService.getMyTests(CurrentUserModel.student.getAuth_id()).size()>0){
                for (LeadBoardCard leadBoardcard:LeadBoardService.getLeadBoardCards()) {
                    if(leadBoardcard.getAuth_id() == CurrentUserModel.student.getAuth_id()){
                        lbl_Rank.setText("#"+leadBoardcard.getRank());
                    }
                }
            }else{
                lbl_Rank.setText("No Rank");
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCategories(){
        listView_CategoryList.getItems().clear();
        listView_CategoryList.setStyle("-fx-font-size: 15");


        try {
            ObservableList<String> observableList = FXCollections.observableArrayList("All Categories");

            CategoryService.getCategories().forEach(category -> {
                observableList.add(category.getName());
            });

            listView_CategoryList.getItems().addAll(observableList);
            listView_CategoryList.getSelectionModel().select(0);
            listView_CategoryList.getFocusModel().focus(0);
            listView_CategoryList.scrollTo(0);
            listView_CategoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if(newValue == "All Categories"){
                        LoadTestTiles(null);
                    }else{
                        LoadTestTiles(newValue.toString());
                    }
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTestItem(@Nullable  MyTest mt , @Nullable Test t) throws IOException {
        C_GridTestItem.test = t;
        C_GridTestItem.myTest = mt;
        Node node = FXMLLoader.load(getClass().getResource(Screens.gridTestItem+".fxml"));
        JFXRippler rippler = new JFXRippler(node);
        vBox_SearchList.getChildren().add(rippler);
    }
}
