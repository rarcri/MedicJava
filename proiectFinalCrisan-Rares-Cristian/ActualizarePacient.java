import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class ActualizarePacient {

  JFrame fr;
  JLabel l1, l2, l3, l4, l5, l6, l7, l8;
  JTextField nume, adresa, telefon, varsta, grupaSanguina, boliCronice;
  JComboBox<String> sex, numePacient;
  JButton submit;
  
  // Lucruri auxiliare
  int idPacientSelectat;

  ArrayList<String> pacientiList = new ArrayList<String>();

  ActualizarePacient() {
    pacientiList.add("Selecteaza aici");

    fr = new JFrame("medic InregistrarePacient"); 
    fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    fr.setLayout(null);
    fr.setSize(400,300);
    fr.setLocation(400,200);

    // Label pentru Nume și prenume
    l8 = new JLabel("Alege pacientul");
    l8.setBounds(20,20,100,20);
    fr.add(l8);

    // LABELS
    // Label pentru Nume și prenume
    l1 = new JLabel("Nume și prenume");
    l1.setBounds(20,40,100,20);
    fr.add(l1);

    // Label pentru Adresa
    l2 = new JLabel("Adresa");
    l2.setBounds(20,60,100,20);
    fr.add(l2);

    // Label pentru Telefon 
    l3 = new JLabel("Telefon");
    l3.setBounds(20,80,100,20);
    fr.add(l3);

    // Label pentru vârsta
    l4 = new JLabel("Vârsta");
    l4.setBounds(20,100,100,20);
    fr.add(l4);

    // Label pentru sex 
    l5 = new JLabel("Sex");
    l5.setBounds(20,120,100,20);
    fr.add(l5);

    // Label pentru grupaSanguina 
    l6 = new JLabel("Grupa sanguina");
    l6.setBounds(20,140,100,20);
    fr.add(l6);

    // Label pentru boliCronice 
    l7 = new JLabel("Boli cronice");
    l7.setBounds(20,160,100,20);
    fr.add(l7);

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

    // Aici Transformam ArrayList-ul în String array
    String pacientiString[] = pacientiList.toArray(new String[pacientiList.size()]);

    numePacient = new JComboBox<>();
    numePacient.setModel(new DefaultComboBoxModel<>(pacientiString));
    numePacient.setBounds(120,20,150,20);
    fr.add(numePacient);

    numePacient.addActionListener(new handleOnChange());

    // TextField pentru nume
    nume = new JTextField();
    nume.setBounds(120,40,150,20);
    fr.add(nume); 

    // TextField pentru adresa
    adresa = new JTextField();
    adresa.setBounds(120,60,150,20);
    fr.add(adresa);

    // TextField pentru telefon
    telefon = new JTextField();
    telefon.setBounds(120,80,150,20);
    fr.add(telefon);
 
    // TextField pentru varsta
    varsta = new JTextField();
    varsta.setBounds(120,100,150,20);
    fr.add(varsta);
    
    // ComboBox pentru sex 
    sex = new JComboBox<>();
    sex.setModel(new DefaultComboBoxModel<>(new String[] { "Masculin", "Feminin", "Altul" }));
    sex.setBounds(120,120,150,20);
    fr.add(sex);

    // TextField pentru grupa Sanguina
    grupaSanguina  = new JTextField();
    grupaSanguina.setBounds(120,140,150,20);
    fr.add(grupaSanguina);

    // TextField pentru Boli Cronice
    boliCronice = new JTextField();
    boliCronice.setBounds(120,160,150,20);
    fr.add(boliCronice);

    
    // We add the submit Button
    submit = new JButton("Actualizare Pacient");
    submit.setBounds(120,200,150,20);
    fr.add(submit);

    submit.addActionListener(new handleActualizeazaPacient());

    // Facem Frame-ul vizibil
    fr.setVisible(true);
  }

  class handleActualizeazaPacient implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if( !numePacient.getSelectedItem().toString().equals("") &&
          !numePacient.getSelectedItem().toString().equals("Selecteaza aici")
          && !nume.getText().equals("") 
          && !adresa.getText().equals("")
          && !telefon.getText().equals("")
          && !varsta.getText().equals("")
          && !sex.getSelectedItem().toString().equals("")
          && !grupaSanguina.getText().equals("")
          && !boliCronice.getText().equals("")) {
        try {

          String query = "UPDATE pacienti SET nume='" + nume.getText() 
            + "', adresa='" + adresa.getText() 
            + "', telefon='" + telefon.getText() 
            + "', varsta=" + Integer.parseInt(varsta.getText()) 
            + ", sex='" + sex.getSelectedItem().toString() 
            + "', grupa_sanguina='" + grupaSanguina.getText()
            + "', boli_cronice='" + boliCronice.getText()
            + "', id_doctor=1 WHERE id_pacient=" + idPacientSelectat;


          Login.con.createStatement().executeQuery(query);

          // AFTER QUERY
          numePacient.setSelectedItem("Selecteaza aici");
          nume.setText("");
          adresa.setText("");
          telefon.setText("");
          varsta.setText("");
          sex.setSelectedItem("Masculin");
          grupaSanguina.setText("");
          boliCronice.setText("");

          new updatePacientiList(pacientiList, numePacient);

          JOptionPane.showMessageDialog(null, "Pacientul a fost actualizat cu success.");

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

          nume.setText(result.getString("nume"));
          adresa.setText(result.getString("adresa"));
          telefon.setText(result.getString("telefon"));
          varsta.setText(result.getString("varsta"));
          sex.setSelectedItem(result.getString("sex"));
          grupaSanguina.setText(result.getString("grupa_sanguina"));
          boliCronice.setText(result.getString("boli_cronice"));
        }

      } catch (Exception exc) {
        exc.printStackTrace();
      }
    }
  }
}


