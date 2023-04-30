import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

// database
import java.sql.*;

public class InregistrareDiagnostic {

  JFrame fr;
  JLabel l1, l2, l3, l4, l5, l6, l7;
  JTextField numeDiagnostic;
  JTextArea detaliiDiagnostic;
  JComboBox<String> numePacient;
  JButton submit;
  int idPacientSelectat;

  ArrayList<String> pacientiList = new ArrayList<String>();


  InregistrareDiagnostic() {
    pacientiList.add("Selecteaza aici");

    fr = new JFrame("medic InregistrareDiagnostic"); 
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
    l2 = new JLabel("Nume Diagnostic");
    l2.setBounds(20,70,100,20);
    fr.add(l2);

    // Label pentru Telefon 
    l3 = new JLabel("Detalii Diagnostic");
    l3.setBounds(20,100,100,20);
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
    numeDiagnostic = new JTextField();
    numeDiagnostic.setBounds(120,70,150,20);
    fr.add(numeDiagnostic); 

    // TextField pentru adresa
    detaliiDiagnostic = new JTextArea();
    detaliiDiagnostic.setBounds(120,100,150,100);
    fr.add(detaliiDiagnostic);

 
    
    // We add the submit Button
    submit = new JButton("Înregistrare diagnostic");
    submit.setBounds(70,200,200,20);
    fr.add(submit);

    submit.addActionListener(new handleInregistreazaDiagnostic());

    // Facem Frame-ul vizibil
    fr.setVisible(true);
  }

  class handleInregistreazaDiagnostic implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(!numePacient.getSelectedItem().toString().equals("") &&
         !numePacient.getSelectedItem().toString().equals("Selecteaza aici") &&
         !numeDiagnostic.getText().equals("") &&
         !detaliiDiagnostic.getText().equals("")) {
        try {

          String query = "INSERT INTO diagnostice(nume_diagnostic, detalii_diagnostic, data_diagnostic, id_pacient) VALUES('"+
            numeDiagnostic.getText() + "', '"+
            detaliiDiagnostic.getText() + "',CURRENT_DATE(), "+ idPacientSelectat + ")";


          Login.con.createStatement().executeQuery(query);

          // AFTER QUERY
          numePacient.setSelectedItem("Selecteaza aici");
          numeDiagnostic.setText("");
          detaliiDiagnostic.setText("");

          JOptionPane.showMessageDialog(null, "Diagnosticul a fost înregistrat cu success.");

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


