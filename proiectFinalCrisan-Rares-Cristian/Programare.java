import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.time.*;

public class Programare {

  JFrame fr;
  JLabel l1, l2, l3;
  JComboBox<String> numePacient;
  JTextField dataProgramarii;
  JButton submit;
  // auxliliar
  int idPacientSelectat;

  ArrayList<String> pacientiList = new ArrayList<String>();

  Programare() {
    pacientiList.add("Selecteaza aici");

    fr = new JFrame("medic InregistrareDiagnostic"); 
    fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    fr.setLayout(null);
    fr.setSize(400,300);
    fr.setLocation(400,200);

    // LABELS
    // Label pentru Nume pacient 
    l1 = new JLabel("Nume Pacient");
    l1.setBounds(20,40,100,20);
    fr.add(l1);

    // Label pentru data Programarii 
    l2 = new JLabel("Data Programării");
    l2.setBounds(20,60,100,20);
    fr.add(l2);

    l3 = new JLabel("Exemplu Format Dată: 2022-03-18");
    l3.setBounds(50,80,220,20);
    fr.add(l3);


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
    String pacientiString[] = pacientiList.toArray(new String[pacientiList.size()]);

    numePacient = new JComboBox<>();
    numePacient.setModel(new DefaultComboBoxModel<>(pacientiString));
    numePacient.setBounds(120,40,150,20);
    fr.add(numePacient);

    numePacient.addActionListener(new handleOnChange());

    // TextField pentru nume Diagnostic 
    dataProgramarii = new JTextField();
    dataProgramarii.setBounds(120,60,150,20);
    fr.add(dataProgramarii); 

 
    
    // We add the submit Button
    submit = new JButton("Programează pacientul");
    submit.setBounds(120,200,200,20);
    fr.add(submit);

    submit.addActionListener(new handleProgramare());
    // Adăugăm datele de Contact
    new showEmailAndTelephone(fr);

    // Facem Frame-ul vizibil
    fr.setVisible(true);
  }

  class handleProgramare implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if( !numePacient.getSelectedItem().toString().equals("") &&
          !numePacient.getSelectedItem().toString().equals("Selecteaza aici") &&
          !dataProgramarii.getText().equals("")) {
        try {

          String query = "INSERT INTO programari(data_curenta, data_programarii, id_pacient)"
        + " VALUES(CURRENT_DATE(), '" + LocalDate.parse(dataProgramarii.getText()) + "', " + idPacientSelectat + ")";


          Login.con.createStatement().executeQuery(query);

          // AFTER QUERY
          numePacient.setSelectedItem("Selecteaza aici");
          dataProgramarii.setText("");

          JOptionPane.showMessageDialog(null, "Programarea a fost înregistrată cu success.");

        } catch (Exception exc) {

          exc.printStackTrace();
          JOptionPane.showMessageDialog(null, "Nu ați introdus data corect");

        }
      } else {
        JOptionPane.showMessageDialog(null, "Completati toate câmpurile.");
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


