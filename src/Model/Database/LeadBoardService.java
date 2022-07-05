package Model.Database;

import Model.Entities.LeadBoardCard;
import Utils.DBConnection;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeadBoardService {

    public static ArrayList<LeadBoardCard> leadBoardCards = new ArrayList<LeadBoardCard>();

    public static  ArrayList<LeadBoardCard> getLeadBoardCards() throws SQLException, ClassNotFoundException {

        int i = 1;
        String sql = "SELECT mt.auth_id , avatar , first_name , last_name , COUNT(mt.marks) as marks FROM student s JOIN" +
                " my_test mt on s.auth_id = mt.auth_id   GROUP BY mt.auth_id ORDER BY Count(mt.marks) DESC";
        ResultSet rs = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        leadBoardCards.clear();
        if(rs.next()){
            do{

                LeadBoardCard leadBoardCard = new LeadBoardCard(
                        rs.getInt("auth_id"),
                        getFile(rs.getBinaryStream("avatar"),"avatar"+i+".png"),
                  rs.getString("first_name") +" "+ rs.getString("last_name"),
                        (int) (rs.getDouble("marks") * 10.0),
                        i
                );
                leadBoardCards.add(leadBoardCard);
                i++;

            }while (rs.next());
        }
        return leadBoardCards;
    }

    private static File getFile(InputStream input , String fileName)  {
        File file = new File(fileName);
        FileOutputStream output = null;
        try{

            output = new FileOutputStream(file);

            System.out.println("Writing to file " + file.getAbsolutePath());

            byte[] buffer = new byte[1024];
            while (input.read(buffer)>0){
                output.write(buffer);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  file;

    }


}
