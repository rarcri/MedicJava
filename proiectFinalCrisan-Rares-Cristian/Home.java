
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

// Imports for the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Home{

  public static InregistrarePacient inregistrarePacientFrame;
  public static InregistrareDiagnostic inregistrareDiagnosticFrame;
  public static InregistrareIstoric inregistrareIstoricFrame;
  public static ActualizarePacient actualizarePacientFrame;
  public static Programare programare;


  public JFrame fr;
  public JButton inregistrarePacient, inregistrareDiagnostic, inregistrareIstoric, actualizarePacient, programari;

  public BufferedImage backgroundImage;

  Home() {

        // BasicBackgroundPanel fr = new BasicBackgroundPanel(ImageIO.read(new File("2.jpg")));
        // Setup the JFrame 
        fr = new JFrame("medic Login");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(500,400);
        fr.setLayout(null);
        fr.setLocation(400,200);
        
        // Creem un Panou cu layere
        JPanel leftPane = new JPanel();
        leftPane.setLayout(null);
        leftPane.setBounds(0,0,200,400);
        
        // Here we handle the IOException
        try {
          // Here we create the Label for the background Image
          // Read the image 
          Image img = ImageIO.read(new File("./img/2.jpg")); 
          // We create an ImageIcon from the Image
          ImageIcon icon = new ImageIcon(img);
          JLabel bg = new JLabel(icon);
          bg.setBounds(200,0,500,400);

          // We add the bg Label to the pane
          fr.add(bg);

        } catch(Exception e) {
          System.out.println("2.jpg not read");
        }

        // Here we handle the IOException
        try {
          // Here we create the Label for the background Image
          // Read the image 
          Image img1 = ImageIO.read(new File("./img/med.png")); 
          // We create an ImageIcon from the Image
          ImageIcon icon1 = new ImageIcon(img1);
          JLabel logo = new JLabel(icon1);
          logo.setBounds(20,20,160,120);

          // We add the bg Label to the pane
          fr.add(logo);

        } catch(Exception e) {
          System.out.println("med.png not read");
        }
        
        // Creeem butonul de inregistrare Pacient 
        inregistrarePacient = new JButton("Inregistrare Pacient");
        inregistrarePacient.setBounds(20,150,160,20);
        leftPane.add(inregistrarePacient);

        inregistrarePacient.addActionListener(new handleInregistrarePacient());

        // Creeem butonul de inregistrare Diagnostic 
        inregistrareDiagnostic = new JButton("Inregistrare Diagnostic");
        inregistrareDiagnostic.setBounds(20,180,160,20);
        leftPane.add(inregistrareDiagnostic);

        inregistrareDiagnostic.addActionListener(new handleInregistrareDiagnostic());

        // Creeem butonul de inregistrare Pacient 
        inregistrareIstoric = new JButton("Inregistrare Istoric");
        inregistrareIstoric.setBounds(20,210,160,20);
        leftPane.add(inregistrareIstoric);

        inregistrareIstoric.addActionListener(new handleInregistrareIstoric());

        // Creeem butonul de inregistrare Pacient 
        actualizarePacient = new JButton("Actualizare Pacient");
        actualizarePacient.setBounds(20,240,160,20);
        leftPane.add(actualizarePacient);

        actualizarePacient.addActionListener(new handleActualizarePacient());

        // Creeem butonul de inregistrare Pacient 
        programari = new JButton("Contact si programari");
        programari.setBounds(20,270,160,20);
        leftPane.add(programari);

        programari.addActionListener(new handleProgramare());

        // Adaugam JLayered Pane-ul in Frame
        fr.add(leftPane);
        
        // Facem Frame-ul vizibil
        fr.setVisible(true);


   }

  public class handleInregistrarePacient implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    // Code to handle inregistrarePacient
    inregistrarePacientFrame = new InregistrarePacient();
      
    }
  }

  public class handleInregistrareDiagnostic implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    // Code to handle inregistrare Diagnostic 
    inregistrareDiagnosticFrame = new InregistrareDiagnostic();

    }
  }

  public class handleInregistrareIstoric implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    // Code to handle inregistrare Istoric 
    inregistrareIstoricFrame = new InregistrareIstoric();
    
    }
  }

  public class handleActualizarePacient implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    // Code to handle actualizare Pacient 
    actualizarePacientFrame = new ActualizarePacient();

    }
  }

  public class handleProgramare implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    // Code to handle programare 
    programare = new Programare();

    }
  }
}


