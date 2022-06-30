package Controller.Student;

import Model.Entities.MyTest;
import Model.Entities.Test;
import com.jfoenix.controls.JFXRippler;
import com.sun.istack.internal.Nullable;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class C_StartTest {

    public VBox vBox_Author;
    public VBox vBox_Menu;
    public VBox vBox_Category;
    public VBox vBox_NOFQuizs;
    public VBox vBox_EnrolledCount;
    public VBox vBox_Description;
    private static MyTest myTest;
    private static Test test;

    public void initialize(){
        setRippler();
    }

    public void setRippler(){
        vBox_Menu.getChildren().clear();
        JFXRippler authorRippler = new JFXRippler(vBox_Author);
        authorRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(authorRippler);

        JFXRippler categoryRippler = new JFXRippler(vBox_Category);
        categoryRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(categoryRippler);

        JFXRippler nofCountRippler = new JFXRippler(vBox_NOFQuizs);
        nofCountRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(nofCountRippler);

        JFXRippler enrolledCountRippler = new JFXRippler(vBox_EnrolledCount);
        enrolledCountRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(enrolledCountRippler);

        JFXRippler descriptionRippler = new JFXRippler(vBox_Description);
        descriptionRippler.setRipplerFill(Color.valueOf("#2196f3"));
        vBox_Menu.getChildren().add(descriptionRippler);
    }

    public static void setData(@Nullable Test t , @Nullable MyTest mt){
        if(t == null && mt != null){
            myTest = mt;
        }else if(mt == null && t != null){
            test = t;
        }else{
            System.out.println("Null");
        }
    }
}
