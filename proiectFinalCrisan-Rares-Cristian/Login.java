import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

// Imports for the database
import java.sql.*;


public class Login{

  public JFrame fr;
  public JLabel numeUtilizator, parola, bg, login;
  public JTextField nume;
  public JPasswordField passwordField;
  public JButton loginButton;
  public JScrollPane pane;
  public Container content;
  public BufferedImage backgroundImage;
  
  // Static assets
  public static Login log;
  public static Connection con;

  Login() {

        // Setup the JFrame 
        fr = new JFrame("medic Login");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(450,250);
        fr.setLocation(400,200);
        
        // Creem un Panou cu layere
        JLayeredPane jLayeredPane = new JLayeredPane();
        
        // Here we handle the IOException
        try {
          // Here we create the Label for the background Image
          // Read the image 
          Image img = ImageIO.read(new File("./img/2.jpg")); 
          // Scale the image
          // Image scaledImage = img.getScaledInstance(500,600,Image.SCALE_DEFAULT);

          // We create an ImageIcon from the Image
          ImageIcon icon = new ImageIcon(img);
          JLabel bg = new JLabel(icon);
          bg.setBounds(0,0,500,300);

          // We add the bg Label to the pane
          jLayeredPane.add(bg, JLayeredPane.DEFAULT_LAYER);

        } catch(Exception e) {
          System.out.println("FIle not read");
        }
        
        // Creeem eticheta pentru Numele utilizatorului
        JLabel numeUtilizator = new JLabel("Nume utilizator");
        numeUtilizator.setBounds(20,60,100,20);
        jLayeredPane.add(numeUtilizator, JLayeredPane.PALETTE_LAYER);

        // Creeem eticheta pentru parola 
        JLabel parola = new JLabel("parola");
        parola.setBounds(20,80,100,20);
        jLayeredPane.add(parola, JLayeredPane.PALETTE_LAYER);

        // Creeem un textfiled pentru numele utilizatorului 
        nume = new JTextField();
        nume.setBounds(220,60,200,20);
        jLayeredPane.add(nume, JLayeredPane.PALETTE_LAYER);

        // Creeem un textfield pentru parola
        passwordField = new JPasswordField();
        passwordField.setBounds(220,80,200,20);
        jLayeredPane.add(passwordField, JLayeredPane.PALETTE_LAYER);

        // We create the login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(100,120,200,20);
        jLayeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER);

        loginButton.addActionListener(new LoginTheUser());

        // Adaugam JLayered Pane-ul in Frame
        fr.add(jLayeredPane);
        
        // Facem Frame-ul vizibil
        fr.setVisible(true);


   }

   void setVisible(boolean value) {
     if(value) {
       fr.setVisible(true);
     } else {
       fr.setVisible(false);
     }
   }


  public static void main(String[] args) {
      
    // We instantiate a new object from class Login
    log = new Login();

  }

  public class LoginTheUser implements ActionListener {
    public void actionPerformed(ActionEvent e) {

      String driver = "org.mariadb.jdbc.Driver";
      String url = "jdbc:mariadb://localhost:3306/cabinetMedical?useSSL=false";
      String username = "root";
      String password = "";
      con = null;

      if(!new String(passwordField.getPassword()).equals("") && !nume.getText().equals("")) {
        try{
          Class.forName(driver);
          con = DriverManager.getConnection(url, username, password);
          if (con != null) {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM doctori WHERE nume='" + nume.getText() + "'");

            if (result.next()) {
              String numeBaza  = result.getString("nume");
              String parolaBaza = result.getString("parola");
              
              // Verificam dacă parola este corectă
              if (parolaBaza.equals(new String(passwordField.getPassword()))){
                new Home();
                log.setVisible(false);
              } else {
                JOptionPane.showMessageDialog(null, "Parola nu este corectă");
              }
            } else {
              JOptionPane.showMessageDialog(null, "Utilizatorul nu există");
            }
          }
        } catch (Exception exception) {
           exception.printStackTrace();
        }
      } else {
          JOptionPane.showMessageDialog(null, "Introduceți valori în toate câmpurile");
      }

    }
  }

}
