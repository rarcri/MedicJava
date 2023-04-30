import javax.swing.*;
import java.sql.*;
import java.awt.*;
// Aici afisam Email-ul și numărul de telefon al doctorului

public class showEmailAndTelephone {
  JLabel l1, l2, l3;
  showEmailAndTelephone(JFrame fr) {
    try {
      String query = "SELECT * FROM doctori WHERE id_doctor=1";
      ResultSet result = Login.con.createStatement().executeQuery(query);

      l1 = new JLabel("Date Contact");
      // facem fontul bold
      Font font = new Font("Courier", Font.BOLD,14);
      l1.setFont(font);
      l1.setBounds(20, 120, 200, 20);
      fr.add(l1);

      if(result.next()) {

        l2 = new JLabel("Email: " + result.getString("email"));
        l2.setBounds(20, 140, 200, 20);
        fr.add(l2);

        l3 = new JLabel("Număr de telefon: " + result.getString("telefon"));
        l3.setBounds(20, 160, 300, 20);
        fr.add(l3);

      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
