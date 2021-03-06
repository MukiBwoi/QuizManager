package Model.Authentication;

import Constants.Users;
import Model.Database.UserService;
import Model.Entities.Lecturer;
import Model.Entities.Student;

import java.io.IOException;
import java.sql.SQLException;

public class CurrentUserModel {
    public static String currentUserEmail;
    public static Student student;
    public static Lecturer lecturer;
    public static boolean isLoggedIn = false;

    public static void getCurrentUser(){
            try {
                if(Users.current_user.equals(Users.student)) {
                    student = UserService.getStudent(currentUserEmail);
                    System.out.println(student);
                }else if(Users.current_user.equals(Users.lecturer)){
                    lecturer = UserService.getLecturer(currentUserEmail);
                    System.out.println(student);
                }else{

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
