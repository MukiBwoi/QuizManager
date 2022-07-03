package Controller.Lecturer;

import Controller.Common.C_Validation;
import Model.Database.CategoryService;
import Model.Entities.Category;
import Utils.ErrorHandler;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

import java.sql.SQLException;

public class C_AddCategory {
    public JFXButton btn_AddCategory;
    public JFXTextField txt_CategoryName;

    public void btn_AddCategoryOnAction(ActionEvent actionEvent) {
        try {
            if(C_Validation.requiredValidator(txt_CategoryName,"Category name required")){
                boolean isAdded = CategoryService.addCategory(new Category(
                        0,
                        txt_CategoryName.getText(),
                        0
                ));
                if(isAdded){
                    new UI().showSuccessAlert("Category Added Successfully");
                    new UI().closeUIButton(btn_AddCategory);
                }else{
                    new UI().showErrorAlert(ErrorHandler.getMessage());
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
