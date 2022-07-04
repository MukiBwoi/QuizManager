package Controller.Student;

import Constants.Screens;
import Model.Authentication.CurrentUserModel;
import Model.Database.QuizService;
import Model.Database.TestService;
import Model.Entities.MyTest;
import Model.Entities.Quiz;
import Model.Entities.Result;
import Model.Entities.Test;
import Utils.Helpers;
import Utils.ReportGenerator;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

public class C_DoQuiz {
    public JFXButton btn_NextQuiz;
    public JFXRadioButton lbl_Answer4;
    public JFXRadioButton lbl_Answer3;
    public JFXRadioButton lbl_Answer2;
    public ToggleGroup answers;
    public JFXRadioButton lbl_Answer1;
    public Label lbl_Quiz;
    public Label lbl_QuizIndex;
    public AnchorPane rootPane;
    public StackPane stackPane_Quizs;
    public Pane pane_resultPane;
    public ImageView imageView_Winner;
    public Label lbl_Greeting;
    public Label lbl_Status;
    public JFXSpinner spinner_Marks;
    public Label lbl_XP;
    public JFXButton btn_ViewReport;
    public JFXButton btn_RedoTest;
    public ScrollPane scrollPane_Quizs;
    public JFXButton btn_BackToDashboard;


    public void initialize(){
        lbl_QuizIndex.setVisible(true);
        UI.NavigatePane(stackPane_Quizs,scrollPane_Quizs);
        newQuiz();

    }

    public void btn_NextQuizOnAction(ActionEvent actionEvent) {
        newQuiz();
    }


    public void newQuiz(){
        if(QuizService.quizIndex<QuizService.quizs.size()){
            if(QuizService.quizIndex == 0){
                incrementQuiz();
            }else{
                if(getIndexOfToggle(answers.getSelectedToggle()) != -1){
                    QuizService.result.put(QuizService.quizIndex , getIndexOfToggle(answers.getSelectedToggle()));
                    answers.getSelectedToggle().setSelected(false);
                    incrementQuiz();
                }else{
                   UI.showSnack(rootPane,"Please Select an answer !");
                }
            }

            if(QuizService.quizIndex==QuizService.quizs.size()){
                btn_NextQuiz.setText("Finish");
            }
        }else{
           finishTest();
        }
    }

    private void incrementQuiz() {
        QuizService.currentQuiz = QuizService.quizs.get(QuizService.quizIndex++);
        lbl_QuizIndex.setText("Question "+QuizService.quizIndex);
        lbl_Quiz.setText(QuizService.currentQuiz.getQuiz());
        lbl_Answer1.setText(QuizService.currentQuiz.getAnswer().getAnswers()[0]);
        lbl_Answer2.setText(QuizService.currentQuiz.getAnswer().getAnswers()[1]);
        lbl_Answer3.setText(QuizService.currentQuiz.getAnswer().getAnswers()[2]);
        lbl_Answer4.setText(QuizService.currentQuiz.getAnswer().getAnswers()[3]);
    }

    private int getIndexOfToggle(Toggle toggle){
        int index = -1;
        for (int i = 0; i < answers.getToggles().size(); i++) {
            if(toggle == answers.getToggles().get(i)){
                index =i;
                break;
            }
        }
        return  index;
    }

    private  void finishTest(){
        if(getIndexOfToggle(answers.getSelectedToggle()) != -1){
            QuizService.result.put(QuizService.quizIndex , getIndexOfToggle(answers.getSelectedToggle()));
            UI.NavigatePane(stackPane_Quizs,pane_resultPane);
            lbl_QuizIndex.setVisible(false);
            answers.getSelectedToggle().setSelected(false);
            UI.progressBarAnimation(spinner_Marks,calculateTotalMarks());
            lbl_XP.setText("You got "+(int)calculateTotalMarks()*10+"XP");
            if(calculateTotalMarks()>0.4){
                lbl_Greeting.setText("Hooray!");
                lbl_Quiz.setText("Pass");
                lbl_Quiz.setTextFill(Color.valueOf("#2ecc71"));
            }else if(calculateTotalMarks()<0.4 && calculateTotalMarks()>=0.0){
                lbl_Greeting.setText("Ouch ! Let's Redo Quiz");
                lbl_Status.setText("Fail");
                lbl_Status.setTextFill(Color.valueOf("#27ae60"));
            }

            try {
                if(TestService.test != null && TestService.myTest == null){
                    TestService.addMyTest(new MyTest(TestService.test.getId(),TestService.test,calculateTotalMarks(),true,
                            CurrentUserModel.student.getAuth_id()));
                }else if (TestService.myTest != null && TestService.test == null){

                }else{

                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }else{
            UI.showSnack(rootPane,"Please Select an answer !");
        }
    }

    public void btn_ViewReportOnACtion(ActionEvent actionEvent) {
        try{
            System.out.println(TestService.test.getId());
            HashMap parameters = new HashMap();
            parameters.put("auth_id",CurrentUserModel.student.getAuth_id());
            parameters.put("test_id",TestService.test.getId());
            parameters.put("username",CurrentUserModel.student.getFirst_name()+" "+CurrentUserModel.student.getLast_name());
            ReportGenerator.showReport(parameters,"ResultSheet");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public void btn_RedoTestOnAction(ActionEvent actionEvent) {
       UI.showSnack(rootPane , "You can not redo this quiz");
    }

    public ArrayList<Result> checkAnswers(){
        ArrayList<Result> results = new ArrayList<>();
        System.out.println(QuizService.result.entrySet());
        System.out.println(QuizService.quizs.get(0).getId());

        for(Map.Entry<Integer, Integer> entry : QuizService.result.entrySet()) {
            final int[] i = {1};
            QuizService.quizs.forEach(quiz->{
                System.out.println(quiz.getId() +"  "+entry.getKey());

                //couldnt use quiz id for this
                if( i[0] == entry.getKey()){
                    System.out.println("match 1");
                    if (quiz.getAnswer().getCorrectAnswerIndex() == entry.getValue()+1){
                        System.out.println("match "+ entry.getValue()+1 );
                        results.add(new Result(quiz.getId(),true , quiz.getAnswer().getCorrectAnswerIndex()));
                    }else{
                        results.add(new Result(quiz.getId(),false , quiz.getAnswer().getCorrectAnswerIndex()));
                    }
                }
                i[0]++;
            });
        }
        System.out.println(results.toString());
        return results;
    }

    public double calculateTotalMarks(){
        int correctCount = (int) checkAnswers().stream().filter(Result::isCorrect).count();
        return  Helpers.round(correctCount/checkAnswers().size(),1);

    }




    public void btn_BackToDashboardOnAction(ActionEvent actionEvent) {
        try {
            QuizService.quizIndex = 0;
            QuizService.currentQuiz = null;
            QuizService.result.clear();
            new UI().closeUIButton(btn_BackToDashboard);
            new UI().setUIWithLogout(Screens.studentDashboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
