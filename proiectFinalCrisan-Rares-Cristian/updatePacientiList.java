import javax.swing.*;
import java.sql.*;
import java.util.*;

public class updatePacientiList {

  updatePacientiList(ArrayList<String> pacientiList, JComboBox<String> numePacient) {
    // Afisam toți pacienti in combobox
    try {
      String query1 = "SELECT * FROM pacienti";
      ResultSet result = Login.con.createStatement().executeQuery(query1);
      
      pacientiList.clear();
      pacientiList.add("Selecteaza aici");

      while(result.next()) {
        pacientiList.add(result.getString("nume"));
      }
      
      // Here we convert the arrayList to string array
      String pacientiString[] = pacientiList.toArray(new String[pacientiList.size()]);

      // Here we add the values in pacientiString
      numePacient.setModel(new DefaultComboBoxModel<>(pacientiString));

    } catch( Exception e) {
      e.printStackTrace();
    }
  }

}
