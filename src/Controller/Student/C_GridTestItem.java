package Controller.Student;

import Model.Entities.MyTest;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class C_GridTestItem {
    public Label lbl_TestName;
    public Label lbl_Author;
    public Label lbl_Category;
    public JFXSpinner progress_Marks;
    public JFXButton btn_StartTest;
    public static MyTest testTile;

    public void initialize(){
        if(testTile != null){
            lbl_TestName.setText(testTile.getTestData().getName());
            lbl_Author.setText(testTile.getTestData().getAuthor());
            lbl_Category.setText(testTile.getTestData().getCategory());
            progress_Marks.setProgress(testTile.getMarks());
            btn_StartTest.setText(testTile.isDone()?"Retry Test" : "Start Test");
            UI.progressBarAnimation(progress_Marks,testTile.getMarks());
        }

    }

    public void btn_StartTestOnAction(ActionEvent actionEvent) {

    }


}
