import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
public class Player
{
    private int lives;
    private String fileName;
    private int xVal;
    private int yVal;
    public Player(int x, int y, int numLives)
    {
        xVal = x;
        yVal = y;
        lives = numLives;
        fileName = "MiniPlayer.png";
    }    
    public void draw(Graphics g)
    {
        BufferedImage img = null;
        Graphics2D g2 = (Graphics2D) g;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
        }
        g2.drawImage(img, xVal, yVal, null);
    }
    public void loseLife()
    {
        lives--;
    }
    public void gainLife()
    {
        lives++;
    }
    public int getLives()
    {
        return lives;
    }
    public String getFile()
    {
        return fileName;
    }
    public int getX()
    {
        return xVal;
    }
    public int getY()
    {
        return yVal;
    }
}