import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import java.awt.Point;

public class SuperAdvancedMouse
{
    private static Rectangle rect = new Rectangle(0,0,0,0);
    
    Random r = new Random();
    static int x1 = 0;
    static int y1 = 0;
    static int x2 = 0;
    static int y2 = 0;
    private static Point xy1 = new Point(0,0);
    private static Point xy2 = new Point(0,0);
    public static void main(String[] args)
    {
        final JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        class SamComp extends JComponent
        {
            Random r = new Random();
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                g2.setColor(c); 
                g2.fill(rect);
            }
        }
        SamComp sc = new SamComp();
        frame.add(sc);
        class Maker extends MouseAdapter
        {
            public void mousePressed(MouseEvent e)
            {
                xy1 = e.getPoint();
                
                frame.repaint();
            }
            public void mouseDragged(MouseEvent e)
            {
                xy2 = e.getPoint();
                
                rect = new Rectangle((int) xy1.getX(),(int) xy1.getY(),(int) xy2.getX() - (int) xy1.getX(),(int) xy2.getY() - (int) xy1.getY());
                frame.repaint();
            }
            public void mouseReleased(MouseEvent e)
            {
                xy2 = e.getPoint();
                
                rect = new Rectangle((int) xy1.getX(),(int) xy1.getY(),(int) xy2.getX() - (int) xy1.getX(),(int) xy2.getY() - (int) xy1.getY());
                frame.repaint();
            }
        }
        Maker m = new Maker();
        sc.addMouseMotionListener(m);
        sc.addMouseListener(m);
        frame.setVisible(true);
    }
}
        