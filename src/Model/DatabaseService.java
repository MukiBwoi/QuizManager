package Model;

import Utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseService {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Lecturer> lecturers = new ArrayList<>();

    //Get Student
    public static Student getStudent(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM student WHERE id = '"+id+"'");
        return rst.next()?new Student(
               rst.getInt("id"),
                rst.getString("first_name"),
                rst.getString("last_name"),
                rst.getString("email"),
                rst.getDate("DOB"),
                rst.getInt("age"),
                rst.getString("batch"),
                rst.getInt("auth_id")
        ):null;
    }

    //Get All Students Student
    public static ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM student");
        if(rst.next()){
            do{
                students.add(
                        new Student(
                                rst.getInt("id"),
                                rst.getString("first_name"),
                                rst.getString("last_name"),
                                rst.getString("email"),
                                rst.getDate("DOB"),
                                rst.getInt("age"),
                                rst.getString("batch"),
                                rst.getInt("auth_id")
                        )
                );
            }
            while (rst.next());
        }
        return students;
    }

    //Get Lecturer
    public static Lecturer getLecturer(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM lecturer WHERE id = '"+id+"'");
        return rst.next()?new Lecturer(
                rst.getInt("id"),
                rst.getString("first_name"),
                rst.getString("last_name"),
                rst.getString("email"),
                rst.getString("branch"),
                rst.getInt("auth_id")
        ):null;
    }

    //Get All Lecturers
    public static ArrayList<Lecturer> getAllLecturers() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM lecturer");
        if(rst.next()){
            do{
                lecturers.add(
                       new Lecturer(
                                rst.getInt("id"),
                                rst.getString("first_name"),
                                rst.getString("last_name"),
                                rst.getString("email"),
                                rst.getString("branch"),
                                rst.getInt("auth_id")
                        )
                );
            }
            while (rst.next());
        }
        return lecturers;
    }
}
