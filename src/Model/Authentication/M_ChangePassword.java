package Model.Authentication;

import Utils.DBConnection;

import javax.rmi.CORBA.Util;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class M_ChangePassword {
    public static boolean changePassword(String email , String newPassword) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection()
                .prepareStatement("UPDATE auth SET password = ? WHERE email=?");
        stm.setString(1,newPassword);
        stm.setString(2,email);
        return stm.executeUpdate()>0;
    }
}
