package Model.Database;

import Model.Entities.Category;
import Utils.DBConnection;
import Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService {

    public static ArrayList<Category> categories = new ArrayList<>();

    public static ArrayList<Category> getCategories()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM category";
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        categories.clear();
        if(rst.next()){
            do{
                Category category = new Category(
                    rst.getInt("id"),
                        rst.getString("name"),
                        rst.getInt("test_count")
                );
                categories.add(category);

            }while(rst.next());

        }
        return categories;
    }

    public static boolean addCategory(Category category) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM category where name = ? OR name = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,category.getName());
        ps.setString(2,category.getName().toLowerCase());
        if(!ps.executeQuery().next()){
           sql = "INSERT INTO category VALUES(?,?,?)";
           ps = conn.prepareStatement(sql);
           ps.setInt(1,category.getId());
           ps.setString(2,category.getName());
           ps.setInt(3,category.getTest_count());
           return ps.executeUpdate()>0;
        }else {
            ErrorHandler.setError("Category Already exist");
        }
        return false;
    }

    public static boolean updateTestCount(String category , boolean isINC) throws SQLException, ClassNotFoundException {
        String sql;
        if(isINC){
            sql = "UPDATE category SET test_count = test_count + 1 WHERE name = ?";
        }else{
            sql = "UPDATE category SET test_count = test_count - 1 WHERE name = ?";
        }
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setString(1,category);
        return ps.executeUpdate()>0;
    }
}
