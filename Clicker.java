import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Clicker
{
    private static Rectangle rect;
    public static void main(String[] args)
    {
        final JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rect = new Rectangle(100, 100, 100, 100);
        class ClickerComp extends JComponent
        {
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                g2.fill(rect);
            }
        }
        ClickerComp c = new ClickerComp();
        frame.add(c);
        class Displayer extends MouseAdapter
        {
            public void mouseClicked(MouseEvent e)
            {
                rect.setLocation(e.getX(), e.getY());
                frame.repaint();
            }
        }
        Displayer d = new Displayer();
        c.addMouseListener(d);
        frame.setVisible(true);
    }
}