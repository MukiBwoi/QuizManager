package Model;

import Utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class M_Register {

    private static boolean authenticateUser(AuthUser auth) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO auth VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,auth.getId());
        ps.setString(2,auth.getEmail());
        ps.setString(3,auth.getPassword());
        ps.setString(4,auth.getEmp_type());
        ps.setBoolean(5,auth.getIsVerified());
        ps.setTimestamp(6 , auth.getLast_login());
        return ps.executeUpdate()>0;
    }


    public static boolean registerStudent(Student student) throws SQLException, ClassNotFoundException {
       if(authenticateUser(student.getAuthUser())){
           int authId = getAuthId(student.getEmail());
           String sql = "INSERT INTO student values (?,?,?,?,?,?,?,?)";
           PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
           ps.setInt(1 , 0);
           ps.setString(2,student.getFirst_name());
           ps.setString(3,student.getLast_name());
           ps.setString(4,student.getEmail());
           ps.setDate(5,student.getDOB());
           ps.setInt(6,student.getAge());
           ps.setString(7,student.getBatch());
           ps.setInt(8,authId);

           return ps.executeUpdate()>0;
       }else{
            ErrorHandler.setError("Authentication Error");
       }
       return  false;
    }

    public static boolean registerLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        if(authenticateUser(lecturer.getAuthUser())){
            int authId = getAuthId(lecturer.getEmail());
            String sql = "INSERT INTO lecturer values (?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1 , 0);
            ps.setString(2,lecturer.getFirst_name());
            ps.setString(3,lecturer.getLast_name());
            ps.setString(4,lecturer.getEmail());
            ps.setString(5,lecturer.getBranch());
            ps.setInt(6,authId);

            return ps.executeUpdate()>0;
        }else{
            ErrorHandler.setError("Authentication Error");
        }
        return  false;
    }

    public static int getAuthId(String email) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement()
                .executeQuery("SELECT id FROM auth WHERE email = '"+email+"'");
        return  resultSet.next()?resultSet.getInt("id"):null;
    }
}
