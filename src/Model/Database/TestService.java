package Model.Database;

import Model.Entities.TestData;
import Model.Entities.MyTest;
import Utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestService {

    public static ArrayList<MyTest> testTiles = new ArrayList<>();
    public static ArrayList<TestData> tests = new ArrayList<>();



    public static ArrayList<MyTest> getTestTiles() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name , author , category , marks , COUNT(myt.auth_id) AS enrolled_count " +
                " FROM test t INNER JOIN my_test myt ON t.test_id = myt.test_id";

        ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        testTiles.clear();
        if(rs.next()){
            do{
                TestData test = new TestData(
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("category"),
                        10,
                        rs.getInt("enrolled_count")
                );
                tests.add(test);
                testTiles.add(new MyTest(
                        test,
                        rs.getDouble("marks"),
                        false
                ));
            }while (rs.next());
        }
        return testTiles;
    }


}
