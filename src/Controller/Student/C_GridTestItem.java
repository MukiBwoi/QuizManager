package Controller.Student;

import Model.Entities.TestTile;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class C_GridTestItem {
    public Label lbl_TestName;
    public Label lbl_Author;
    public Label lbl_Category;
    public JFXSpinner progress_Marks;
    public JFXButton btn_StartTest;
    public static TestTile testTile;

    public void initialize(){
        if(testTile != null){
            lbl_TestName.setText(testTile.getName());
            lbl_Author.setText(testTile.getAuthor());
            lbl_Category.setText(testTile.getCategory());
            progress_Marks.setProgress(testTile.getMarks());
            btn_StartTest.setText(testTile.isDone()?"Retry Test" : "Start Test");
            UI.progressBarAnimation(progress_Marks,testTile.getMarks());
        }

    }

    public void btn_StartTestOnAction(ActionEvent actionEvent) {

    }


}
