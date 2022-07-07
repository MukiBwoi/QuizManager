package Controller.Common.DialogBox;

import javafx.scene.control.Label;

public class C_DialogHeading {

    public Label lbl_heading;
    public static String heading;

    public void initialize(){
        lbl_heading.setText(heading);
    }
}
