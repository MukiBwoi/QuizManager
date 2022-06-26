package Controller.Lecturer;

import Constants.Screens;
import Controller.Common.C_Validation;
import Model.Database.CategoryService;
import Model.Database.QuizService;
import Model.Database.TestService;
import Model.Entities.Answer;
import Model.Entities.Category;
import Model.Entities.Quiz;
import Model.Entities.Test;
import Utils.UI;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.sql.SQLException;

import static javafx.util.Duration.seconds;


public class C_AddTest {


    public JFXButton btn_Next;
    public JFXButton btn_Cancel;
    public ScrollPane scrollPane_Quizs;
    public  VBox vBox_QuizList;
    public StackPane stackPane_AddQuestions;
    public ScrollPane scrollPane_QuizContents;
    public VBox vBox_QuizContents;
    public Label lbl_QuestionNumber;
    public JFXTextArea textArea_Question;
    public JFXTextField txt_Answer1;
    public JFXTextField txt_Answer2;
    public JFXTextField txt_Answer3;
    public JFXTextField txt_Answer4;
    public JFXTextField txt_CorrectAnswer;
    public JFXButton btn_AddQuiz;
    public StackPane stackPane_AddTest;
    public Pane pane_SecondPane;
    public Pane pane_firstPane;
    public Pane pane_AddQuiz;
    public JFXTextField txt_TestName;
    public JFXTextField txt_Author;
    public JFXTextArea txt_Description;
    public JFXComboBox<String> cmb_Category;
    public JFXButton btn_AddCategory;
    public JFXTextField txt_DurationH;
    public JFXTextField txt_DurationM;
    public JFXTextField txt_DurationS;
    public JFXCheckBox checkBox_IsTimeBased;
    public AnchorPane rootPane;
    public JFXButton btn_Back;
    private boolean isAddQuizClicked;
    public static int quizindex;
    private int screenIndex;




    public void initialize(){
        QuizService.quizs.clear();

        quizindex = 0;
        vBox_QuizList.getChildren().clear();

        isAddQuizClicked = false;
        scrollPane_Quizs.setContent(vBox_QuizList);
        scrollPane_QuizContents.setContent(vBox_QuizContents);
        screenIndex = 0;
        loadCategoryCombo();


    }

    public void btn_CancelOnAction(ActionEvent actionEvent) {

        try {
            new UI().setUI(Screens.lecturerDashboard);
            new UI().closeUIButton(btn_Cancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn_NextOnAction(ActionEvent actionEvent) {



        switch (screenIndex){
            case 0:
                screenIndex += 1;
                System.out.println("case 0");
                UI.NavigatePane(stackPane_AddTest,pane_SecondPane);
                break;

            case 1:
                if(validateTestDetails()){
                    screenIndex += 1;
                    System.out.println("case 2");
                    UI.NavigatePane(stackPane_AddTest,pane_AddQuiz);
                    btn_Next.setText("Finish");
                }

                break;
            case 2:
                System.out.println("case 3");
                if(validateQuizList()){
                    setTestData();
                    addToDatabase();
                }

                break;
            default:
                System.out.println("Done");
        }

    }

    public void btn_AddQuizOnAction(ActionEvent actionEvent) {
        if(!isAddQuizClicked){
            nullFields();
            isAddQuizClicked = true;
            btn_Back.setVisible(true);
            quizindex = quizindex+1;
            lbl_QuestionNumber.setText("Question " + quizindex);
            new UI().NavigatePane(stackPane_AddQuestions,scrollPane_QuizContents);
            btn_Next.setDisable(true);
            btn_AddQuiz.setText("Done");

        }else{

            if(validateQuizFields()){
                isAddQuizClicked = false;
                btn_Back.setVisible(false);
                AddObjects();
                new UI().NavigatePane(stackPane_AddQuestions,scrollPane_Quizs);
                btn_AddQuiz.setText("Add");
                btn_Next.setDisable(false);
                addToQuizList();
            }

        }

    }

    private void addToQuizList(){
        try {
            C_QuizTile.quizIndex = this.quizindex;
            Node node = FXMLLoader.load(getClass().getResource(Screens.quizTile+".fxml"));
            JFXRippler ripple = new JFXRippler(node);
            ripple.setRipplerFill(Color.valueOf("#2196f3"));
            vBox_QuizList.getChildren().add(ripple);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nullFields(){
        textArea_Question.setText(null);
        txt_Answer1.setText(null);
        txt_Answer2.setText(null);
        txt_Answer3.setText(null);
        txt_Answer4.setText(null);
        txt_CorrectAnswer.setText(null);
    }


    private void AddObjects(){

        String[] answers = {txt_Answer1.getText(), txt_Answer2.getText(), txt_Answer3.getText(),
                txt_Answer4.getText()};

        QuizService.quizs.add(
                new Quiz(
                        textArea_Question.getText(),
                        "MCQ",
                        new Answer(answers,
                                Integer.parseInt(txt_CorrectAnswer.getText()))
                )
        );

    }

    public void setTestData(){
        TestService.test = new Test(
                txt_TestName.getText(),
                txt_Author.getText(),
                (String) cmb_Category.getSelectionModel().getSelectedItem(),
                txt_Description.getText(),
                QuizService.quizs.size(),
                0
        );
    }

    public void updateVBox(){

    }

    public void addToDatabase(){

        try {
            if(TestService.addTest()){
                int lastTestID = TestService.getLastTestID();
                System.out.println("LID" + lastTestID);
                if(QuizService.addQuiz(lastTestID)){
                    new UI().closeUIButton(btn_Next);
                    new UI().setUI(Screens.lecturerDashboard);
                    System.out.println("Added");
                }else{
                    new UI().showErrorAlert("Something went wrong with quiz adding");
                }
            }else{
                new UI().showErrorAlert("Something went wrong with test adding");
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void checkBox_IsTimeBasedOnAction(ActionEvent actionEvent) {

    }


    public void loadCategoryCombo(){
        try {
            cmb_Category.getItems().clear();
            CategoryService.getCategories();
            if(CategoryService.categories.size()>0){
                ObservableList<String> categoryNames = FXCollections.observableArrayList();
                for (Category category:CategoryService.categories) {
                    categoryNames.add(category.getName());
                }
                cmb_Category.setItems(categoryNames);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private boolean validateTestDetails(){
        boolean isTestName = C_Validation.requiredValidator(txt_TestName, "Test name required");
        boolean isDescription = C_Validation.requiredValidator(txt_Description,"Description required");
        String isCategory = C_Validation.commonValidator(cmb_Category.getSelectionModel().getSelectedItem(),
                "Category required");
        System.out.println(isCategory);
        if(!(isTestName && isDescription)){
            return false;
        }else if(isCategory != null){
            UI.showSnack(rootPane,"Category Required !");
            return  false;
        }else{
            return  true;
        }

    }

    public boolean validateQuizList(){
        if(vBox_QuizList.getChildren().size()>0){
            return  true;
        }
        UI.showSnack(rootPane,"Please add at least on quiz ");
        return false;
    }

    public boolean validateQuizFields(){
        boolean isQuiz = C_Validation.requiredValidator(textArea_Question , "Question required");
        boolean isAnswer1 = C_Validation.requiredValidator(txt_Answer1,"First answer required");
        boolean isAnswer2 = C_Validation.requiredValidator(txt_Answer2,"Second answer required");
        boolean isAnswer3 = C_Validation.requiredValidator(txt_Answer3 , "Third answer required");
        boolean isAnswer4 = C_Validation.requiredValidator(txt_Answer4 , "Fourth answer required");
        boolean correctAnswer = C_Validation.numberValidator(txt_CorrectAnswer,"Correct answer index");

        if(isQuiz && isAnswer1 && isAnswer2 && isAnswer3 && isAnswer4 && correctAnswer){
            return true;
        }
        return  false;
    }

    public void btn_BackOnAction(ActionEvent actionEvent) {
        if(isAddQuizClicked){
            isAddQuizClicked = false;
            quizindex -=1;
            btn_Back.setVisible(false);
            new UI().NavigatePane(stackPane_AddQuestions,scrollPane_Quizs);
            btn_AddQuiz.setText("Add");
            btn_Next.setDisable(false);
        }
    }
}
