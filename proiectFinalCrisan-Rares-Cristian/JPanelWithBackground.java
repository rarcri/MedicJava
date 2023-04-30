import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class JPanelWithBackground extends JPanel {

  private BufferedImage backgroundImage;

  public JPanelWithBackground(String fileName) {
    try {
      backgroundImage = ImageIO.read(new File(fileName));
    } catch(Exception e) {
      System.out.println("This is not working");
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw the background image.
    g.drawImage(backgroundImage, 0, 0, this);
  }
}
