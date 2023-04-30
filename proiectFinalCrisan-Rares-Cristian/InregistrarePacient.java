import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InregistrarePacient {

  JFrame fr;
  JLabel l1, l2, l3, l4, l5, l6, l7;
  JTextField nume, adresa, telefon, varsta, grupaSanguina, boliCronice;
  JComboBox<String> sex;
  JButton submit;

  InregistrarePacient() {

    fr = new JFrame("medic InregistrarePacient"); 
    fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    fr.setLayout(null);
    fr.setSize(400,300);
    fr.setLocation(400,200);

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

    
    // TEXT FIELDS
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
    submit = new JButton("Inregistrează Pacient");
    submit.setBounds(120,200,150,20);
    fr.add(submit);

    submit.addActionListener(new handleInregistreazaPacient());

    // Facem Frame-ul vizibil
    fr.setVisible(true);
  }

  class handleInregistreazaPacient implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(!nume.getText().equals("") 
          && !adresa.getText().equals("")
          && !telefon.getText().equals("")
          && !varsta.getText().equals("")
          && !sex.getSelectedItem().toString().equals("")
          && !grupaSanguina.getText().equals("")
          && !boliCronice.getText().equals("")) {
        try {

          String query = "INSERT INTO pacienti(nume, adresa, telefon, varsta, sex, grupa_sanguina, boli_cronice,id_doctor) VALUES('"+
            nume.getText() + "', '"+
            adresa.getText() + "', '"+
            telefon.getText() + "', "+
            Integer.parseInt(varsta.getText()) + ", '"+
            sex.getSelectedItem().toString() + "', '"+
            grupaSanguina.getText() + "', '"+
            boliCronice.getText() + "', 1)";

          Login.con.createStatement().executeQuery(query);

          // AFTER QUERY
          nume.setText("");
          adresa.setText("");
          telefon.setText("");
          varsta.setText("");
          sex.setSelectedItem("Masculin");
          grupaSanguina.setText("");
          boliCronice.setText("");

          JOptionPane.showMessageDialog(null, "Pacientul a fost înregistrat cu success.");

        } catch(SQLIntegrityConstraintViolationException exc){

          JOptionPane.showMessageDialog(null, "Pacientul cu numele '" + nume.getText() + "' există deja.");

        } catch (Exception exc) {
          exc.printStackTrace();
        }
      } else {
          JOptionPane.showMessageDialog(null, "Completați toate câmpurile.");
      }
    }
  }
}


