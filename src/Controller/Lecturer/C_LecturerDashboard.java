package Controller.Lecturer;

import Constants.Screens;
import Utils.UI;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class C_LecturerDashboard {
    public JFXTreeTableView tableView_TestTable;
    public JFXSpinner spinner_TestEnrollement;
    public JFXNodesList nodeList_optionNodes;
    public JFXHamburger hamburger_Drawer;
    public JFXButton btn_Insert;
    public JFXButton btn_Delete;
    public JFXButton btn_GenerateReport;
    public JFXButton btn_Option;
    public Label lbl_DateTime;
    public StackPane stackPane_DrawerPane;
    public JFXDrawer Drawer_leftDrawer;

    public void initialize(){
        DashBoardTableController.createTable(tableView_TestTable);
        UI.progressBarAnimation(spinner_TestEnrollement , 0.25);
        setNodebtnList();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(hamburger_Drawer);
        burgerTask.setRate(-1);
        hamburger_Drawer.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
            burgerTask.setRate(burgerTask.getRate()*-1);
            burgerTask.play();
        });

        //Drawer_leftDrawer.setSidePane(stackPane_DrawerPane);
        Drawer_leftDrawer.setDefaultDrawerSize(150);
        Drawer_leftDrawer.setOverLayVisible(false);
    }

    public void btn_LogoutOnAction(ActionEvent actionEvent) {
    }

    public void setNodebtnList(){

        nodeList_optionNodes.setRotate(270);
        nodeList_optionNodes.setSpacing(100d);
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

    public void hamburger_ToggleDrawerOnActionPerformed(MouseEvent mouseEvent) {
        if(!Drawer_leftDrawer.isShowing()){
            Drawer_leftDrawer.open();
        }else{
            Drawer_leftDrawer.close();
        }
    }

    public void btn_InsertOnAction(ActionEvent actionEvent) {

        try {
            new UI().closeUIButton(btn_Insert);
            new UI().setUI(Screens.addTest);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
