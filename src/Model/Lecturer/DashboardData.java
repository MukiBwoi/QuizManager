package Model.Lecturer;

import Model.Entities.Lecturer;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardData {

    public static double totalEnrollement(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        String sql = "SELECT round((SELECT COUNT(test_id) FROM test WHERE test.enrolledCount>0)/COUNT(test_id),1) " +
                "as enrollement FROM test WHERE author = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,lecturer.getFirst_name() + " "+ lecturer.getLast_name());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getDouble("enrollement");
        }else{
            System.out.println("Error in fetching enrollment data");
        }

        return  0;

    }
}
