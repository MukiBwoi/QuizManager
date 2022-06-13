package Model;

import Utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class M_Login {
    public static String getPassword(String e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT password FROM auth WHERE email=?");
        stm.setString(1,e);
        ResultSet rst = stm.executeQuery();
        return  rst.next()? rst.getString("password") :null;
    }
}
