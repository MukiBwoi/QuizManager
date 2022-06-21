package Model.Authentication;

import Utils.DBConnection;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class M_UploadAvatar {

    public static boolean UploadAvatar(InputStream avatar , String email , String user)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE "+user.toLowerCase()+" SET avatar = ? WHERE email = ?";
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setBinaryStream(1,avatar);
        ps.setString(2,email);
        return  ps.executeUpdate()>0;
    }
}
