package Model.Database;

import Model.Entities.Category;
import Utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService {

    public static ArrayList<Category> categories = new ArrayList<>();

    public static ArrayList<Category> getCategories()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM category";
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
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
}
