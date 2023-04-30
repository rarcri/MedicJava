import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class BasicBackgroundPanel extends JPanel
{
    private Image background;
 
    public BasicBackgroundPanel(Image background)
    {
        this.background = background;
        setLayout( new BorderLayout() );
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
 
        g.drawImage(background, 0, 0, null); // image full size
        //g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }
 
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}
