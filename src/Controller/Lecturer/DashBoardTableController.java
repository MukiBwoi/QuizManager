package Controller.Lecturer;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

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
                return param.getValue().getValue().getName();
            }
        });

        JFXTreeTableColumn<Test, String> author = new JFXTreeTableColumn<>("Author");
        author.setPrefWidth(150);
        author.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().getAuthor();
            }
        });

        JFXTreeTableColumn<Test, String> category = new JFXTreeTableColumn<>("Category");
        category.setPrefWidth(150);
        category.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().getCategory();
            }
        });

        JFXTreeTableColumn<Test, String> nofQuiz = new JFXTreeTableColumn<>("Quizs");
        nofQuiz.setPrefWidth(150);
        nofQuiz.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().getNofQuizs();
            }
        });

        JFXTreeTableColumn<Test, String> enrollCount = new JFXTreeTableColumn<>("Enrolled");
        enrollCount.setPrefWidth(150);
        enrollCount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Test, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Test, String> param) {
                return param.getValue().getValue().getEnrolledCount();
            }
        });

        ObservableList<Test> tests = FXCollections.observableArrayList();
        for (Model.Entities.Test test:dbTests) {
                tests.add(new Test(test.getId(),test.getName() , test.getAuthor(),test.getCategory(),test.getNofQuizs()+"",
                        test.getEnrolledCount()+""));
        }
        final TreeItem<Test> root = new RecursiveTreeItem<Test>(tests, RecursiveTreeObject::getChildren);
        tableView.getColumns().setAll(name, author, category,nofQuiz,enrollCount);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    static class Test extends RecursiveTreeObject<Test> {

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + getId() +
                    ", name=" + getName() +
                    ", author=" + getAuthor() +
                    ", category=" + getCategory() +
                    ", nofQuizs=" + getNofQuizs() +
                    ", enrolledCount=" + getEnrolledCount() +
                    '}';
        }

        private IntegerProperty id;
        private StringProperty name;
        private StringProperty author;
        private StringProperty category;
        private StringProperty nofQuizs;
        private StringProperty enrolledCount;

        public Test(int id,String name, String author, String category , String noOfQuiz , String enrolledCount) {
            this.setId(new SimpleIntegerProperty(id));
            this.setName(new SimpleStringProperty(name));
            this.setAuthor(new SimpleStringProperty(author));
            this.setCategory(new SimpleStringProperty(category));
            this.setNofQuizs(new SimpleStringProperty(noOfQuiz));
            this.setEnrolledCount(new SimpleStringProperty(enrolledCount));
        }

        public IntegerProperty getId() {
            return id;
        }

        public void setId(IntegerProperty id) {
            this.id = id;
        }

        public StringProperty getName() {
            return name;
        }

        public void setName(StringProperty name) {
            this.name = name;
        }

        public StringProperty getAuthor() {
            return author;
        }

        public void setAuthor(StringProperty author) {
            this.author = author;
        }

        public StringProperty getCategory() {
            return category;
        }

        public void setCategory(StringProperty category) {
            this.category = category;
        }

        public StringProperty getNofQuizs() {
            return nofQuizs;
        }

        public void setNofQuizs(StringProperty nofQuizs) {
            this.nofQuizs = nofQuizs;
        }

        public StringProperty getEnrolledCount() {
            return enrolledCount;
        }

        public void setEnrolledCount(StringProperty enrolledCount) {
            this.enrolledCount = enrolledCount;
        }
    }
}


