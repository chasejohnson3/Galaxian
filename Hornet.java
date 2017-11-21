import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
public class Hornet extends Enemies
{
    private int pointValue;
    private String fileName;
    public Hornet(int x, int y)
    {
        super(x, y);
        pointValue = 50;
        fileName = "MiniHornet.png";
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