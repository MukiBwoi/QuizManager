package Controller.Student;

import Model.Entities.LeadBoardItem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class C_LeadBoardCard {
    static LeadBoardItem leadBoardItem;
    public Label lbl_Name;
    public Circle img_Avatar;
    public Label lbl_Points;
    public Label lbl_Rank;

    public  void initialize(){
        if(leadBoardItem != null){
            img_Avatar.setFill(new ImagePattern(leadBoardItem.getAvatar()));
            lbl_Name.setText(leadBoardItem.getName());
            lbl_Points.setText(String.valueOf(leadBoardItem.getTotalPoints()));
            lbl_Rank.setText(String.valueOf(leadBoardItem.getRank()));
        }
    }
}
