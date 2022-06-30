package Model.Database;

import Model.Entities.Answer;
import Model.Entities.Quiz;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuizService {

    public static ArrayList<Quiz> quizs = new ArrayList<>();
    public static  int lastId;
    public static int quizIndex = 0;
    public static Quiz currentQuiz;
    public static Map<Integer,Integer> result = new HashMap<>();

    public static boolean addQuiz(int testID) throws SQLException, ClassNotFoundException {
        ArrayList<Boolean> booleans = new ArrayList<>();
        for (Quiz quiz:quizs) {
            String sql = "INSERT INTO quiz VALUES(?,?,?,?)";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,0);
            ps.setInt(2,testID);
            ps.setString(3,quiz.getQuiz());
            ps.setString(4, quiz.getType());
            int i = ps.executeUpdate();
            if(i>0){

                int id = getLastQuizID();
                String sql1 = "INSERT INTO answer VALUES(?,?,?,?,?,?,?)";
                PreparedStatement ps1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                ps1.setInt(1,0);
                ps1.setInt(2,id);
                ps1.setString(3,quiz.getAnswer().getAnswers()[0]);
                ps1.setString(4,quiz.getAnswer().getAnswers()[1]);
                ps1.setString(5,quiz.getAnswer().getAnswers()[2]);
                ps1.setString(6,quiz.getAnswer().getAnswers()[3]);
                ps1.setInt(7,quiz.getAnswer().getCorrectAnswerIndex());
                int j = ps1.executeUpdate();
                if(j>0){
                    booleans.add(true);
                }
            }
        }
        return booleans.size() == quizs.size();
    }

    public static ArrayList<Quiz> getQuizs(int testID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM quiz JOIN answer ON quiz.id = answer.question_id WHERE quiz.test_id = ?";

        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,testID);
        ResultSet rs = ps.executeQuery();
        quizs.clear();
        if(rs.next()){
            do{
                String[] answers = {
                  rs.getString("answer1"),
                        rs.getString("answer2"),
                        rs.getString("answer3"),
                        rs.getString("answer4"),
                };
                Quiz quiz = new Quiz(
                        rs.getString("quiz"),
                        rs.getString("type"),
                        new Answer(answers,rs.getInt("correct_answer")),
                                rs.getInt("id"));

                quizs.add(quiz);

            }while (rs.next());
        }
        return quizs;
    }

    public static int getLastQuizID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM quiz ORDER BY id DESC LIMIT 1;";

        ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        if(rs.next()){
            lastId = rs.getInt("id");
        }
        return  lastId;
    }
}
