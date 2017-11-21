import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
import java.util.Random;
public class Enemies
{
    private boolean alive;
    private int xVal;
    private int yVal;
    private boolean goRight;
    private String fileName;
    private Rectangle[][][] bullets = new Rectangle[10][6][3];
    private int iVal;
    private int jVal;
    Random r = new Random();
    int rand = r.nextInt(2);     
    
    public Enemies(int x, int y)
    {

        xVal = x;
        yVal = y;
        Random r = new Random();
        int rand = r.nextInt(2);
        for (int iVal = 0; iVal < 10; iVal++)
        {
            for (int jVal = 0; jVal < 6; jVal++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (jVal == 0 && iVal == 4 || iVal == 5)
                    {
                        bullets[iVal][jVal][k] = new Rectangle(xVal + 22, yVal + 37, 4, 10);
                    }
                    if (jVal >= 3)
                    {
                        bullets[iVal][jVal][k] = new Rectangle(xVal + 19, yVal + 17, 4, 10);
                    }
                    if (jVal == 2 && iVal != 0 && iVal != 9)
                    {
                        bullets[iVal][jVal][k] = new Rectangle(xVal + 17, yVal + 17, 4, 10);
                    }
                    if (jVal == 1 && iVal != 0 && iVal != 9 && iVal != 8 && iVal != 1)
                    {
                        bullets[iVal][jVal][k] = new Rectangle(xVal + 24, yVal + 30, 4, 10);
                    }                    
                }
            }
        }
                    
        if (rand == 0)
        {
            goRight = true;
        }
        if (rand == 1)
        {
            goRight = false;
        }
        fileName = "";
    }
    public void draw(Graphics g, String file)
    {
        BufferedImage img = null;
        Graphics2D g2 = (Graphics2D) g;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
        }
        g2.drawImage(img, xVal, yVal, null);
    }
    
    public boolean getAlive()
    {
        return alive;
    }
    public void die()
    {
        alive = false;
    }
    public int getX()
    {
        return xVal;
    }
    public int getY()
    {
        return yVal;
    }
    public String getFile()
    {
        return fileName;
    }
    public int getValue()
    {
        return 0;
    }
    public void moveLeft()
    {
        xVal += -3;
    }
    public void moveRight()
    {
        xVal += 3;
    }
    public void attack()
    {
        if (goRight)
        {
            xVal += 7;
            yVal += 7;
            
//             for (int k = 0; k < 3; k++)
//             {
//                 if (bullets[iVal][jVal][k] != null)
//                     bullets[iVal][jVal][k].translate(10, 0);
//             }
        }
        else
        {   
            xVal -= 7;
            yVal += 7;
//             for (int k = 0; k < 3; k++)
//             {
//                 if (bullets[iVal][jVal][k] != null)
//                     bullets[iVal][jVal][k].translate(-20, 20);
//             }
        }
            if (xVal > 740)
            {
                goRight = false;
                for (int k = 0; k < 3; k++)
                {
                    if (bullets[iVal][jVal][k] != null)
                        bullets[iVal][jVal][k].translate(5, 100);
                }
            }
            if (xVal < 0)
            {   
                goRight = true;
                for (int k = 0; k < 3; k++)
                {
                    if (bullets[iVal][jVal][k] != null)
                        bullets[iVal][jVal][k].translate(-5, 100);
                }
            }       
    }
    public boolean goingRight()
    {
        return goRight;
    }
    public Rectangle getRect(int i, int j, int k)
    {
        return bullets[i][j][k];
    }
}