import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
public class Drone extends Enemies
{
    private int pointValue;
    private String fileName;
    public Drone(int x, int y)
    {
        super(x, y);
        pointValue = 30;
        fileName = "MiniDrone2.png";
    }    
    public int getValue()
    {
        return pointValue;
    }
    public String getFile()
    {
        return fileName;
    }
}