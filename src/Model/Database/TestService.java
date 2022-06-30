package Model.Database;

import Model.Entities.Test;
import Model.Entities.MyTest;
import Utils.DBConnection;
import com.sun.istack.internal.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestService {

    public static ArrayList<MyTest> myTests = new ArrayList<>();
    public static ArrayList<Test> tests = new ArrayList<>();
    public static ArrayList<Test> testListByCategory = new ArrayList<>();
    public static Test test;
    public static int lastId;




    public static ArrayList<MyTest> getMyTests(int auth_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM test t INNER JOIN my_test myt ON t.test_id = myt.test_id WHERE auth_id = ?";

        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,auth_id);
        ResultSet rs = ps.executeQuery();
        myTests.clear();
        if(rs.next()){
            do{
                Test test = new Test(
                        rs.getInt("test_id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("nofQuizs"),
                        rs.getInt("enrolledCount")

                );
                myTests.add(new MyTest(

                        rs.getInt("id"),
                        test,
                        rs.getDouble("marks"),
                        false,
                        rs.getInt("auth_id")
                ));
            }while (rs.next());
        }
        return myTests;
    }

    public static boolean isTestDone(int test_id , int auth_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM my_test WHERE test_id = ? && my_test.auth_id = ?";

        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,test_id);
        ps.setInt(2,auth_id);
        ResultSet rs = ps.executeQuery();
        myTests.clear();
        if(rs.next()){
           return true;
        }else{
            return  false;
        }
    }

    public static ArrayList<Test> getTests() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM test";

        ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        tests.clear();
        if(rs.next()){
            do{
                Test test = new Test(
                        rs.getInt("test_id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("nofQuizs"),
                        rs.getInt("enrolledCount")
                );
                tests.add(test);

            }while (rs.next());
        }
        return tests;
    }



    public static boolean addTest() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO test VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,0);
        ps.setString(2, test.getName());
        ps.setString(3, test.getAuthor());
        ps.setString(4, test.getCategory());
        ps.setString(5, test.getDescription());
        ps.setInt(6, test.getNofQuizs());
        ps.setInt(7,test.getEnrolledCount());

        return ps.executeUpdate()>0;
    }

    public boolean addMyTest(MyTest myTest , int uid) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO my_test VALUES(?,?,?,?)";
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,0);
        ps.setInt(2, myTest.getId());
        ps.setInt(3, uid);
        ps.setDouble(4,myTest.getMarks());


        return ps.executeUpdate()>0;
    }

    public static int getLastTestID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT test_id FROM test ORDER BY test_id DESC LIMIT 1;";

        ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
       if(rs.next()){
           lastId = rs.getInt("test_id");
       }
        return  lastId;
    }

    public static ArrayList<Test> getTestsByCategory(@Nullable  String category) throws SQLException, ClassNotFoundException {

        System.out.println(category);
        if(category == null){
            testListByCategory = getTests();
        }else{
            testListByCategory.clear();
            String sql = "SELECT * FROM test WHERE category = ?";
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1,category);
            ResultSet rs = ps.executeQuery();


            if(rs.next()){
                do{
                    Test test = new Test(
                            rs.getInt("test_id"),
                            rs.getString("name"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getInt("nofQuizs"),
                            rs.getInt("enrolledCount")
                    );
                    testListByCategory.add(test);

                }while (rs.next());


            }
        }
        return  testListByCategory;
    }

}
