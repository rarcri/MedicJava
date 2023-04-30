import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BazaDeDate {
   public static void main(String[] args) {

      String driver = "org.mariadb.jdbc.Driver";
      String MySQLURL = "jdbc:mariadb://localhost:3306/clienti?useSSL=false";
      String databseUserName = "root";
      String databasePassword = "mysql1234";
      Connection con = null;

      try {
         Class.forName(driver);
         con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
         if (con != null) {
            System.out.println("Database connection is successful !!!!");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
