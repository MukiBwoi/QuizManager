package Controller.Lecturer;

import Model.Entities.Test;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashBoardTableController {
    public void initialize(){



    }

    public static void createTable(JFXTreeTableView tableView , ArrayList<Model.Entities.Test> dbTests){
        JFXTreeTableColumn<Test, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Test, String> author = new JFXTreeTableColumn<>("Author");
        author.setPrefWidth(150);
        author.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().author;
            }
        });

        JFXTreeTableColumn<Test, String> category = new JFXTreeTableColumn<>("Category");
        category.setPrefWidth(150);
        category.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().category;
            }
        });

        JFXTreeTableColumn<Test, String> nofQuiz = new JFXTreeTableColumn<>("Quizs");
        nofQuiz.setPrefWidth(150);
        nofQuiz.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().nofQuizs;
            }
        });

        JFXTreeTableColumn<Test, String> enrollCount = new JFXTreeTableColumn<>("Enrolled");
        enrollCount.setPrefWidth(150);
        enrollCount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().enrolledCount;
            }
        });

        ObservableList<Test> tests = FXCollections.observableArrayList();
        for (Model.Entities.Test test:dbTests) {
                tests.add(new Test(test.getName() , test.getAuthor(),test.getCategory(),test.getNofQuizs()+"",
                        test.getEnrolledCount()+""));
        }
        final TreeItem<Test> root = new RecursiveTreeItem<Test>(tests, RecursiveTreeObject::getChildren);
        tableView.getColumns().setAll(name, author, category,nofQuiz,enrollCount);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    static class Test extends RecursiveTreeObject<Test> {

        StringProperty name;
        StringProperty author;
        StringProperty category;
        StringProperty nofQuizs;
        StringProperty enrolledCount;

        public Test(String name, String author, String category , String noOfQuiz , String enrolledCount) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.category = new SimpleStringProperty(category);
            this.nofQuizs = new SimpleStringProperty(noOfQuiz);
            this.enrolledCount = new SimpleStringProperty(enrolledCount);
        }

    }
}


