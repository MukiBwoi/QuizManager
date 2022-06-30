package Controller.Student;

import Model.Database.QuizService;
import Utils.UI;
import com.jfoenix.controls.JFXButton;
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
            System.out.println(QuizService.result.entrySet());
        }else{
            System.out.println("Please select a answer");
        }
    }

    public void btn_ViewReportOnACtion(ActionEvent actionEvent) {
    }

    public void btn_RedoTestOnAction(ActionEvent actionEvent) {
    }
}
