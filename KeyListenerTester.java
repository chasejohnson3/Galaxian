import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class KeyListenerTester
{
    private static Rectangle rect;
    public static void main(String[] args)
    {
        final JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rect = new Rectangle(100, 100, 100, 100);
        class thisRectComp extends JComponent
        {
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                g2.fill(rect);
            }
        }
        thisRectComp rc = new thisRectComp();
        frame.add(rc);
        class Mover implements KeyListener
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    rect.translate(0, 10);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    rect.translate(0, -10);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    rect.translate(-10, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    rect.translate(10, 0);
                }
                frame.repaint();
            }
        
            public void keyTyped(KeyEvent e)
            {
                
            }
            public void keyReleased(KeyEvent e)
            {
                rect.translate(0, 0);
            }
        }
        Mover m = new Mover();
        frame.addKeyListener(m);
        frame.setVisible(true);
    }
}