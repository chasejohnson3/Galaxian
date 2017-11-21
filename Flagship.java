import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
public class Flagship extends Enemies
{
    private int pointValue;
    private String fileName;
    public Flagship(int x, int y)
    {
        super(x, y);
        pointValue = 60;
        fileName = "MiniFlagship.png";
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