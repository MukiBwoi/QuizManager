package Model.Student;

import Model.Entities.TestTile;
import Utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M_GridTestTiles {

    public static ArrayList<TestTile> testTiles = new ArrayList<>();

    public static ArrayList<TestTile> getTestTiles() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM test";
        ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        testTiles.clear();
        if(rs.next()){
            do{
                testTiles.add(new TestTile(
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("marks"),
                        rs.getBoolean("isDone")
                ));
            }while (rs.next());
        }
        return testTiles;
    }


}
