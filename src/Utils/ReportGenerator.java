package Utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.HashMap;

public class ReportGenerator {

    private static JasperPrint createReport(HashMap parameters, String jasperFileName) throws SQLException, ClassNotFoundException, JRException {
        return JasperFillManager.fillReport(
                System.getProperty("user.dir")+"/src/Reports/"+jasperFileName+".jasper", parameters,
                DBConnection.getInstance().getConnection());
    }

    public static void showReport(HashMap parameters, String jasperFileName){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                JasperPrint jp = null;
                try {
                    jp = createReport(parameters, jasperFileName);
                    JasperViewer viewer = new JasperViewer(jp, false);
                    viewer.setZoomRatio(0.75f);
                    viewer.setVisible(true);
                } catch (SQLException | JRException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
