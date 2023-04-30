import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;

public class InregistrareIstoric {

  JFrame fr;
  JLabel l1, l2, l3, l4, l5, l6, l7;
  JTextField titluIstoric;
  JTextArea continutIstoric;
  JComboBox<String> numePacient;
  JButton submit;
  // auxliliar
  int idPacientSelectat;

  ArrayList<String> pacientiList = new ArrayList<String>();

  InregistrareIstoric() {
    pacientiList.add("Selecteaza aici");

    fr = new JFrame("medic InregistrareIstoric"); 
    fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    fr.setLayout(null);
    fr.setSize(400,300);
    fr.setLocation(400,200);

    // LABELS
    // Label pentru Nume și prenume
    l1 = new JLabel("Nume Pacient");
    l1.setBounds(20,40,100,20);
    fr.add(l1);

    // Label pentru Adresa
    l2 = new JLabel("Titlu istoric");
    l2.setBounds(20,70,100,20);
    fr.add(l2);

    // Label pentru Telefon 
    l3 = new JLabel("Conținut istoric");
    l3.setBounds(20,100,100,20);
    fr.add(l3);

    // Afisam toți pacienti in combobox
    try {
      String query1 = "SELECT * FROM pacienti";
      ResultSet result = Login.con.createStatement().executeQuery(query1);

      while(result.next()) {
        pacientiList.add(result.getString("nume"));
      }

    } catch( Exception e) {
      e.printStackTrace();
    }
    

    
    // TEXT FIELDS
    // ComboBox pentru numePacient
    // Transformăm din ArrayList în String array
    String pacientiString[] = pacientiList.toArray(new String[pacientiList.size()]);

    numePacient = new JComboBox<>();
    numePacient.setModel(new DefaultComboBoxModel<>(pacientiString));
    numePacient.setBounds(120,40,150,20);
    fr.add(numePacient);

    numePacient.addActionListener(new handleOnChange());

    // TextField pentru nume Istoric 
    titluIstoric = new JTextField();
    titluIstoric.setBounds(120,70,150,20);
    fr.add(titluIstoric); 

    // TextField pentru adresa
    continutIstoric = new JTextArea();
    continutIstoric.setBounds(120,100,150,100);
    fr.add(continutIstoric);

 
    // We add the submit Button
    submit = new JButton("Adaugă istoric");
    submit.setBounds(120,200,150,20);
    fr.add(submit);

    submit.addActionListener(new handleInregistreazaIstoric());

    // Facem Frame-ul vizibil
    fr.setVisible(true);
  }

  class handleInregistreazaIstoric implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(!numePacient.getSelectedItem().toString().equals("") &&
         !numePacient.getSelectedItem().toString().equals("Selecteaza aici") &&
         !titluIstoric.getText().equals("") &&
         !continutIstoric.getText().equals("")) {
        try {

          String query = "INSERT INTO istoric(titlu_istoric, continut_istoric, data_istoric, id_pacient) VALUES('"+
            titluIstoric.getText() + "', '"+
            continutIstoric.getText() + "',CURRENT_DATE(), "+ idPacientSelectat + ")";


          Login.con.createStatement().executeQuery(query);

          // AFTER QUERY
          numePacient.setSelectedItem("Selecteaza aici");
          titluIstoric.setText("");
          continutIstoric.setText("");

          JOptionPane.showMessageDialog(null, "Istoricul a fost înregistrat cu success.");

        } catch (Exception exc) {
          exc.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(null, "Completați toate câmpurile.");
      }
    }
  }

  class handleOnChange implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {

        String query = "SELECT * FROM pacienti WHERE nume='" 
          + numePacient.getSelectedItem().toString() + "'";


        ResultSet result = Login.con.createStatement().executeQuery(query);

        if(result.next()) {
          idPacientSelectat = result.getInt("id_pacient");
        }

      } catch (Exception exc) {
        exc.printStackTrace();
      }
    }
  }
}
