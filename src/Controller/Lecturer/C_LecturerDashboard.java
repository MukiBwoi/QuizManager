package Controller.Lecturer;

import Constants.Screens;
import Controller.Authentication.C_UploadAvatar;
import Controller.Common.C_LeadBoardCard;
import Model.Authentication.CurrentUserModel;
import Model.Database.CategoryService;
import Model.Database.LeadBoardService;
import Model.Database.TestService;
import Model.Entities.LeadBoardCard;
import Model.Entities.Lecturer;
import Model.Lecturer.DashboardData;
import Utils.ReportGenerator;
import Utils.UI;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class C_LecturerDashboard {
    public JFXTreeTableView tableView_TestTable;
    public JFXSpinner spinner_TestEnrollement;
    public JFXNodesList nodeList_optionNodes;
    public JFXButton btn_Insert;
    public JFXButton btn_Delete;
    public JFXButton btn_GenerateReport;
    public JFXButton btn_Option;
    public Label lbl_DateTime;
    public JFXDrawer Drawer_leftDrawer;
    public Label lbl_Name;
    public Circle circle_Avatar;
    public JFXButton btn_Logout;
    public AnchorPane rootPane;
    public VBox vBox_LeadBoardList;

    public void initialize(){

        crateAndLoadMyTestTable();
        LoadLeadBoard();
        UI.progressBarAnimation(spinner_TestEnrollement , 0.25);
        setNodebtnList();
        setPersonalDetails();
        setTestEnrollementProgressBar();

    }

    public void  setTestEnrollementProgressBar(){
        try {
            UI.progressBarAnimation(spinner_TestEnrollement, DashboardData.totalEnrollement(
                    CurrentUserModel.lecturer
            ));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btn_LogoutOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Logout);
            CurrentUserModel.lecturer = null;
            CurrentUserModel.isLoggedIn = false;
            new UI().setUI(Screens.login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNodebtnList(){

        nodeList_optionNodes.setRotate(270);
        nodeList_optionNodes.setSpacing(80d);
        nodeList_optionNodes.addAnimatedNode(btn_Option);
        nodeList_optionNodes.addAnimatedNode(btn_Insert);
        nodeList_optionNodes.addAnimatedNode(btn_Delete);
        nodeList_optionNodes.addAnimatedNode(btn_GenerateReport);
    }

    public void setCurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy 'at' hh:mm a ");
        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtString = formatter.format(zdt);
        lbl_DateTime.setText(zdtString);
    }

    public void btn_InsertOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Insert);
            new UI().setUI(Screens.addTest);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPersonalDetails(){
        Lecturer lecturer = CurrentUserModel.lecturer;
        lbl_Name.setText(lecturer.getFirst_name() + " "+ CurrentUserModel.lecturer.getLast_name());
        circle_Avatar.setFill(UI.pattern(new Image(lecturer.getAvatar().toURI().toString()),20));
    }


    public void crateAndLoadMyTestTable(){
        try {
            Lecturer lecturer = CurrentUserModel.lecturer;
            DashBoardTableController.createTable(tableView_TestTable,TestService.searchTests(
                    lecturer.getFirst_name()+" "+lecturer.getLast_name()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void LoadLeadBoard(){
        vBox_LeadBoardList.getChildren().clear();

        Node node = null;
        try {
            if(LeadBoardService.getLeadBoardCards().size()>0){
                System.out.println(LeadBoardService.leadBoardCards.get(0));
                for (LeadBoardCard leadBoardCard : LeadBoardService.leadBoardCards) {
                    System.out.println(leadBoardCard);
                    C_LeadBoardCard.leadBoardCard = leadBoardCard;
                    node = FXMLLoader.load(getClass().getResource(Screens.leadBoardCard + ".fxml"));
                    vBox_LeadBoardList.getChildren().add(node);
                }
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btn_GenerateReportOnAction(ActionEvent actionEvent) {
        HashMap parameters = new HashMap();
        parameters.put("Author_Name",CurrentUserModel.lecturer.getFirst_name()+" "
                +CurrentUserModel.lecturer.getLast_name());
        ReportGenerator.showReport(parameters,"MyTest");

    }

    public void btn_deleteOnAction(ActionEvent actionEvent) {
        if(tableView_TestTable.getSelectionModel().getSelectedIndices().size()>0){
            try {
                RecursiveTreeItem selectedItem = (RecursiveTreeItem) tableView_TestTable.getSelectionModel().getSelectedItem();
                DashBoardTableController.Test test = (DashBoardTableController.Test) selectedItem.getValue();
                boolean isDeleted = TestService.deleteTest(test.getId().getValue());
                if(isDeleted){
                    new UI().showSuccessAlert(rootPane,"Data Deleted Successfully");
                    CategoryService.updateTestCount(test.getCategory().getValue() , false);
                    crateAndLoadMyTestTable();
                }else{
                    new UI().showErrorAlert(rootPane,"Something went wrong !");
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        }else{
            UI.showSnack(rootPane,"Please Select a Row !");
        }

    }

    public void circle_AvatarOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) circle_Avatar.getScene().getWindow();
            stage.close();
            C_UploadAvatar.email = CurrentUserModel.lecturer.getEmail();
            C_UploadAvatar.nextScreen = Screens.lecturerDashboard;
            new UI().setUI(Screens.uploadAvatar);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
