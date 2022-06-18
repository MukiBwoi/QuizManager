package Model.Authentication;

import Constants.Users;
import Model.Database.DatabaseService;
import Model.Entities.Lecturer;
import Model.Entities.Student;

import java.sql.SQLException;

public class CurrentUserModel {
    public static String currentUserEmail;
    public static Student student;
    public static Lecturer lecturer;

    public static void getCurrentUser(){
            try {
                if(Users.current_user.equals(Users.student)) {
                    student = DatabaseService.getStudent(currentUserEmail);
                }else if(Users.current_user.equals(Users.lecturer)){
                    lecturer = DatabaseService.getLecturer(currentUserEmail);
                }else{

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }

}
