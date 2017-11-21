import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
public class GalaxianGame
{
    private static Rectangle ship;
    private static Rectangle  rect2;
    private static Rectangle bullet;
    private static Ellipse2D.Double e2;
    private static boolean right;
    private static boolean up;
    final int deg30 = 30;
    private static int framey;
    private static int score1;
    private static int speed;
    private static int hits;
    private static boolean play;
    private static boolean intro;
    private static Font font;
    private static Font font2;
    private static Font font3;
    private static Font font4;
    private static boolean bulletPresent;
    private static int xFired;
    private static Enemies[][] aliens;
    private static Rectangle[][] alienBody;
    private static Point shipMid;
    private static Player shipSeen;
    private static Rectangle surround;
    private static boolean goRight;
    private static Graphics graphics;
    private static Graphics2D enemyGraph;
    private static int time;
    private static int time2;
    private static Random random = new Random();
    private static int iVal;
    private static int jVal;
    private static int iVal2;
    private static int jVal2;
    private static int kamakazi;
    private static int kamakazi2;
    private static boolean attacking;
    private static boolean attacking2;
    private static boolean dead;
    private static boolean gameOver;
    private static boolean win;
    private static int deadCount;
    private static int onlyOnce;
    private static int onlyAgain;
    private static int onlyAgain2;
    private static int onlyAgain3;
    private static int onlyAgain4;
    private static int bulletTimer;
    private static int bulletTimer2;
    private static boolean bulletGo11;
    private static boolean bulletGo12;
    private static boolean bulletGo13;
    private static boolean bulletGo21;
    private static boolean bulletGo22;
    private static boolean bulletGo23;
    private static int k1;
    private static int k2;
    private static int count;
    public static void main(String[] args)
    {
        time = 0;
        attacking = false;
        onlyOnce = 0;
        win = false;
        onlyAgain = 0;
        onlyAgain2 = 0;
        bulletTimer = 0;
        bulletTimer2 = 0;
        k1 = 0;
        k2 = 0;
        final JFrame frame = new JFrame();
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ship = new Rectangle(371, frame.getHeight() - 70, 30, 40);
        shipSeen = new Player(365, frame.getHeight() - 75, 5);      
        bullet = new Rectangle(384, frame.getHeight() - 83, 4, 10);
        
        font = new Font("", Font.BOLD, 14);
        font2 = new Font("", Font.BOLD, 28);
        font3 = new Font("", Font.BOLD, 52);
        font4 = new Font("", Font.BOLD, 16);
        //point for the bullet to go
        shipMid = new Point(0, 0);
        //area surrounding the enemies'
        surround = new Rectangle(110, 110, 580, 270);
        deadCount = 0;
        dead = false;
        gameOver = false;
        goRight = true;
        
        aliens = new Enemies[10][6];
        alienBody = new Rectangle[10][6];
        
        //fill the array with the enemies looming above the player
        for (int i = 0; i < aliens.length; i++)
        {
            for (int j = 0; j < aliens[i].length; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (j == 0 && i == 4 || i == 5)
                    {
                        aliens[i][j] = new Flagship(110 + 60*i, 100 + 50*j);
                        alienBody[i][j] = new Rectangle(117 + 60*i, 117 + 50*j, 35, 25);
                    }
                    if (j >= 3)
                    {
                        aliens[i][j] = new Drone(110 + 60*i, 100 +50*j);
                        alienBody[i][j] = new Rectangle(110 + 60*i, 100 + 50*j, 35, 25);
                    }
                    if (j == 2 && i != 0 && i != 9)
                    {
                        aliens[i][j] = new Emissary(110 + 60*i, 100 + 50*j);
                        alienBody[i][j] = new Rectangle(110 + 60*i, 100 + 50*j, 35, 25);
                    }
                    if (j == 1 && i != 0 && i != 9 && i != 8 && i != 1)
                    {
                        aliens[i][j] = new Hornet(110 + 60*i, 100 + 50*j);
                        alienBody[i][j] = new Rectangle(120 + 60*i, 115 + 50*j, 35, 25);
                    }
                }
            }
        }
        bulletPresent = true;
        right = true;
        up = true;
        speed = 0;
        play = false;
        intro = true;
            //          1 = new Rectangle(0, 225, 15, 12.5);
            //          2 = new Rectangle(0, 225, 15, 12.5);
            //          3 = new Rectangle(0, 225, 15, 12.5);
            //          4 = new Rectangle(0, 225, 15, 12.5);
        class PongComp extends JComponent
        {
            public void paintComponent(Graphics g)
            {
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                Rectangle background = new Rectangle(800, 700);
                g2.fill(background);
                g2.setColor(Color.WHITE);
                graphics = g;
                shipSeen.draw(g);
                
                
                //g2.fill(surround);
                
                for (int i = 0; i < aliens.length; i++)
                {
                    for (int j = 0; j < aliens[i].length; j++)
                    {
                        if (aliens[i][j] != null)
                        {
                            Enemies e = aliens[i][j];
                            e.draw(g, e.getFile());
                            Rectangle r2 = alienBody[i][j];
                            for (int k = 0; k < 3; k++)
                            {
                                Rectangle r3 = aliens[i][j].getRect(i, j, k);
                                g2.fill(r3);
                            }
                            //g2.fill(r2);
                        }
                    }
                }
                
                g2.fill(bullet);
                g2.drawString("Score: " + score1, 20, 20);
                g2.drawString("Lives: " + shipSeen.getLives(), 350, 20);
                if (intro)
                {
                    g2.setFont(font);
                    g2.drawString("Use Arrow Keys to Move", 400, 35);
                    g2.drawString("Use Up Arrow to Fire", 400, 50);
                    g2.drawString("Welcome to Galaxian", 178, 35);
                   //g2.drawString("First to 5 Wins", 190, 50);
                    g2.drawString("Press Space to Start", 180, 50);
                    
                }
                if (dead)
                {
                    g2.setFont(font2);
                    g2.setColor(Color.RED);
                    g2.drawString("DEAD!", 350, 350);
                }
                if (gameOver)
                {
                    g2.setFont(font3);
                    g2.setColor(Color.RED);
                    g2.drawString("Game Over", 275, 500);
                }        
                if (win)
                {
                    g2.setFont(font3);
                    g2.setColor(Color.GREEN);
                    g2.drawString("You Win!", 270, 500);
                }
            }            
        }
        final PongComp pc = new PongComp();
        frame.add(pc);
        class Recter implements KeyListener
        {
            public void keyPressed(KeyEvent e)
            {


                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    bulletPresent = false;
                    xFired = (int) ship.getX() + 13;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameOver && ship.getX() < 760 && ship.getX() > 0)
                {
                    ship.translate(20, 0);
                    shipSeen = new Player(shipSeen.getX() + 20, shipSeen.getY(), shipSeen.getLives());
                    if (bulletPresent)
                        bullet.translate(20, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameOver && ship.getX() < 760 && ship.getX() > 0)
                {
                    ship.translate(-20, 0); 
                    shipSeen = new Player(shipSeen.getX() - 20, shipSeen.getY(), shipSeen.getLives());
                    if (bulletPresent)
                        bullet.translate(-20, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE && onlyOnce == 0)
                {
                    play = true;
                    intro = false;
                    onlyOnce++;
                }
                
                frame.repaint();
            }
            public void keyTyped(KeyEvent e)
            {
                
            }
            public void keyReleased(KeyEvent e)
            {
                
            }
        }
        class Mover implements ActionListener
         {
             public void actionPerformed(ActionEvent e)
             {
                 if (surround.getX() > 180)
                 {
                     goRight = false;
                 }
                 if (surround.getX() < 20)
                 {
                     goRight = true;
                 }
                 //to keep the ship from going outside the playing area
                 if (ship.getX() > 760)
                 {
                     ship.setLocation(759, frame.getHeight() - 70);
                     shipSeen = new Player(753, frame.getHeight() - 75, shipSeen.getLives());
                     bullet.setLocation(772, frame.getHeight() - 83);
                 }
                 if (ship.getX() < 0)
                 {
                     ship.setLocation(1, frame.getHeight() - 70);
                     shipSeen = new Player(-5, frame.getHeight() - 75, shipSeen.getLives());
                     bullet.setLocation(14, frame.getHeight() - 83);
                 }
                 
                 if (play)
                 {  
                     if (bullet.getY() < 0)
                     {
                         shipMid = new Point((int) ship.getX() + 13, (int) ship.getY() - 10);
                         bullet.setLocation(shipMid);
                         bulletPresent = true;
                         frame.repaint();
                     }
                     if (!bulletPresent)
                     {
                         bullet.translate(0, -20);
                         frame.repaint();
                     }
                     //if an enemy is hit, it disappears
                     for (int i = 0; i < alienBody.length; i++)
                     {
                        for (int j = 0; j < alienBody[i].length; j++)
                        {
                            if (alienBody[i][j] != null)
                            {
                                if (bullet.intersects(alienBody[i][j]) && !bulletPresent)
                                {
                                    Enemies en = aliens[i][j];
                                    shipMid = new Point((int) ship.getX() + 13, (int) ship.getY() - 10);
                                    alienBody[i][j] = null;
                                    aliens[i][j] = null;
                                    bulletPresent = true;
                                    bullet.setLocation(shipMid);
                                    int score = en.getValue();
                                    score1 += score;
                                    frame.repaint();
                                }
                            }
                        }
                     }
                    //The enemies go left and right
                     if (goRight)
                    {
                         for (int i = 0; i < alienBody.length; i++)
                         {
                            for (int j = 0; j < alienBody[i].length; j++)
                            {
                                if (alienBody[i][j] != null)
                                {
//                                         Enemies en = aliens[i][j];
//                                         aliens[i][j] = new Enemies(en.getX() + 3, en.getY());
                                        alienBody[i][j].translate(3, 0);
                                        aliens[i][j].moveRight();
                                        for (int k = 0; k < 3; k ++)
                                        {
                                            aliens[i][j].getRect(i,j, k).translate(3, 0);
                                        }
                                }
                            }
                         }
                         surround.translate(3, 0);
                         frame.repaint();
                    }
                    //in this case, the enemies go left
                    if (!goRight)
                    {
                         for (int i = 0; i < alienBody.length; i++)
                         {
                            for (int j = 0; j < alienBody[i].length; j++)
                            {
                                if (alienBody[i][j] != null)
                                {
//                                         Enemies en = aliens[i][j];
//                                         aliens[i][j] = new Enemies(en.getX() - 3, en.getY());
//                                         Enemies ene = aliens[i][j];
                                        alienBody[i][j].translate(-3, 0);      
                                        aliens[i][j].moveLeft();
                                        for (int k = 0; k < 3; k ++)
                                        {
                                            aliens[i][j].getRect(i,j, k).translate(-3, 0);
                                        }
                                }
                            }
                         }
                         surround.translate(-3, 0);
                         frame.repaint();
                    }                    
                    if (!attacking)
                    {
                        time += 1;
                        //kamakazi = random.nextInt(2) + 1;
                        kamakazi = 2;
                        if (kamakazi == time)
                        {
                            jVal = random.nextInt(6);
                            iVal = random.nextInt(10);
                            time = 0;
                            attacking = true;
                        }                        
                    }
                        else
                        {
                            while (aliens[iVal][jVal] == null)
                            {
                                if (iVal < 9)
                                    iVal++;
                                else if (jVal < 6)
                                {
                                    iVal = 0;
                                    jVal++;
                                }
                                else if (jVal == 6)
                                {
                                    iVal = 0;
                                    jVal = 0;
                                }
                                else
                                {
                                    win = true;
                                    play = false;
                                }
                            }
                            if (kamakazi2 == time2)
                            {
                                jVal2 = random.nextInt(6);
                                iVal2 = random.nextInt(10);
                                time2 = 0;
                                attacking2 = true;
                            }
                            if (aliens[iVal][jVal] != null)
                            {
                                aliens[iVal][jVal].attack();
                                
                                if (aliens[iVal][jVal].goingRight())
                                {
                                    alienBody[iVal][jVal].translate(7, 7);   
                                    for (int k = 0; k < 3; k ++)
                                        {
                                            aliens[iVal][jVal].getRect(iVal,jVal, k).translate(7, 7);
                                        }
                                        if (alienBody[iVal][jVal].getX() > 700 && onlyAgain < 1)
                                    {
                                        
                                        for (int k = 0; k < 3; k++)
                                        {
                                            if (aliens[iVal][jVal].getRect(iVal, jVal, k) != null)
                                                aliens[iVal][jVal].getRect(iVal, jVal, k).translate(15, 0);
                                        }
                                        onlyAgain++;
                                    }
                                    if (aliens[iVal][jVal].getX() < 700)
                                    {
                                        onlyAgain = 0;
                                    }
                                }
                                if (!aliens[iVal][jVal].goingRight())
                                {
                                    alienBody[iVal][jVal].translate(-7, 7);   
                                    for (int k = 0; k < 3; k ++)
                                        {
                                            aliens[iVal][jVal].getRect(iVal,jVal, k).translate(-7, 7);
                                        }
                                        if (alienBody[iVal][jVal].getX() < 30 && onlyAgain2 < 1)
                                    {
                                        
                                        for (int k = 0; k < 3; k++)
                                        {
                                            if (aliens[iVal][jVal].getRect(iVal, jVal, k) != null)
                                                aliens[iVal][jVal].getRect(iVal, jVal, k).translate(-15, 0);
                                        }
                                        onlyAgain2++;
                                    }
                                    if (aliens[iVal][jVal].getX() > 30)
                                    {
                                        onlyAgain2 = 0;
                                    }
                                    
                                }
                                if (aliens[iVal][jVal].getY() > 700)
                                {
                                    attacking = false;
                                    if (jVal == 0 && iVal == 4 || iVal == 5)
                                    {
                                        aliens[iVal][jVal] = new Flagship((int) surround.getX() + 60*iVal, 100 + 50*jVal);
                                        alienBody[iVal][jVal] = new Rectangle((int) surround.getX() + 60*iVal, 117 + 50*jVal, 35, 25);
                                    }
                                    if (jVal >= 3)
                                    {
                                        aliens[iVal][jVal] = new Drone((int) surround.getX() + 60*iVal, 100 +50*jVal);
                                        alienBody[iVal][jVal] = new Rectangle((int) surround.getX() + 60*iVal, 100 + 50*jVal, 35, 25);
                                    }
                                    if (jVal == 2 && iVal != 0 && iVal != 9)
                                    {
                                        aliens[iVal][jVal] = new Emissary((int) surround.getX() + 60*iVal, 100 + 50*jVal);
                                        alienBody[iVal][jVal] = new Rectangle((int) surround.getX() + 60*iVal, 100 + 50*jVal, 35, 25);
                                    }
                                    if (jVal == 1 && iVal != 0 && iVal != 9 && iVal != 8 && iVal != 1)
                                    {
                                        aliens[iVal][jVal] = new Hornet((int) surround.getX() + 60*iVal, 100 + 50*jVal);
                                        alienBody[iVal][jVal] = new Rectangle((int) surround.getX() + 60*iVal, 115 + 50*jVal, 35, 25);
                                    }
                                }
                                if (bullet.intersects(alienBody[iVal][jVal]) && !bulletPresent)
                                {   
                                    attacking = false;
                                    alienBody[iVal][jVal] = null;
                                    aliens[iVal][jVal] = null;
                                }
                            }
                            if (aliens[iVal][jVal] != null)
                            {
                                if (alienBody[iVal][jVal].intersects(ship))
                                {
                                    shipSeen.loseLife();
                                    alienBody[iVal][jVal] = null;
                                    aliens[iVal][jVal] = null;
                                    if (shipSeen.getLives() > 0)
                                    {
                                        dead = true;
                                        
                                    }                         
                                    if (shipSeen.getLives() == 0)
                                    {
                                        gameOver = true;
                                        play = false;
                                    }
                                }
                            }                          
                        }
                        if (aliens[iVal][jVal] != null)
                            {
                                if (aliens[iVal][jVal].getY() > 400)
                                {                               
                                    
                                    if (aliens[iVal][jVal].getRect(iVal, jVal, 0) != null && k1 == 0)// && k1 == bulletTimer)
                                    {
                                        bulletGo11 = true;
                                    }
                                     if (aliens[iVal][jVal].getRect(iVal, jVal, 1) != null && k1 == 4)// && k1 == bulletTimer)
                                    {   
                                        bulletGo12 = true;
                                    }
                                    if (aliens[iVal][jVal].getRect(iVal, jVal, 2) != null && k1 == 8)// && k1 == bulletTimer)
                                    {
                                            bulletGo13 = true;
                                    }                                   
                                    if (bulletGo11)
                                    {
                                        aliens[iVal][jVal].getRect(iVal, jVal, 0).translate(0, 20);
                                        //System.out.println("Bullet 1");
                                    }
                                    if (bulletGo12)
                                    {
                                        aliens[iVal][jVal].getRect(iVal, jVal, 1).translate(0, 20);
                                        //System.out.println("Bullet 2");
                                    }
                                    if (bulletGo13)
                                    {
                                        aliens[iVal][jVal].getRect(iVal, jVal, 2).translate(0, 20);
                                        //System.out.println("Bullet 3");
                                    }
                                    k1++;
                                    bulletTimer++;
                                        if (aliens[iVal][jVal].getY() > 700)
                                    {      
                                        bulletGo11 = false;
                                        bulletGo12 = false;
                                        bulletGo13 = false;
                                        //bulletTimer = 0;
                                        k1 = 0;
                                    }
                                }
                                
                            }                               
                            for (int k = 0; k < 3; k++)
                            {
                                if (aliens[iVal][jVal] != null)
                                {
                                    if (aliens[iVal][jVal].getRect(iVal, jVal, k).intersects(ship))
                                    {
                                        shipSeen.loseLife();
                                        alienBody[iVal][jVal] = null;
                                        aliens[iVal][jVal] = null;
                                        if (shipSeen.getLives() > 0)
                                        {
                                            dead = true;
                                            play = false;
                                        }                         
                                        if (shipSeen.getLives() == 0)
                                        {
                                            gameOver = true;
                                            play = false;
                                        }
                                    }
                                }
                            }
                 }
                 if (!attacking2)
                    {
                        time2++;
                        //kamakazi = random.nextInt(2) + 1;
                        kamakazi2 = 5;
                        if (kamakazi2 == time2)
                        {
                            jVal2 = random.nextInt(6);
                            iVal2 = random.nextInt(10);
                            time2 = 0;
                            attacking2 = true;
                        }
                        
                    }
                        else
                        {
                            while (aliens[iVal2][jVal2] == null)
                            {
                                if (iVal2 < 9)
                                    iVal2++;
                                else if (jVal2 > 0)
                                {
                                    iVal2 = 0;
                                    jVal2++;
                                }
                                else if (jVal2 == 0)
                                {
                                    iVal2 = 0;
                                    jVal2 = 0;
                                }
                                else
                                {
                                    win = true;
                                    play = false;
                                }
                            }
                            if (kamakazi2 == time2)
                            {
                                jVal2 = random.nextInt(6);
                                iVal2 = random.nextInt(10);
                                time2 = 0;
                                attacking2 = true;
                            }
                            if (aliens[iVal2][jVal2] != null)
                            {
                                aliens[iVal2][jVal2].attack();
                                if (aliens[iVal2][jVal2].goingRight())
                                {
                                    alienBody[iVal2][jVal2].translate(7, 7);   
                                    for (int k = 0; k < 3; k++)
                                        {
                                            aliens[iVal2][jVal2].getRect(iVal2,jVal2, k).translate(7, 7);
                                        }
                                            if (alienBody[iVal2][jVal2].getX() > 700 && onlyAgain3 < 1)
                                    {
                                        
                                        for (int k = 0; k < 3; k++)
                                        {
                                            if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, k) != null)
                                                aliens[iVal2][jVal2].getRect(iVal2, jVal2, k).translate(15, 0);
                                        }
                                        onlyAgain3++;
                                    }
                                    if (aliens[iVal2][jVal2].getX() < 700)
                                    {
                                        onlyAgain3 = 0;
                                    }
                                }
                                if (!aliens[iVal2][jVal2].goingRight())
                                {
                                    alienBody[iVal2][jVal2].translate(-7, 7);
                                    for (int k = 0; k < 3; k ++)
                                        {
                                            aliens[iVal2][jVal2].getRect(iVal2,jVal2, k).translate(-7, 7);
                                        }
                                            
                                        //accounts for bullets moving when the ship hits the wall
                                        if (alienBody[iVal2][jVal2].getX() < 30 && onlyAgain4 < 1)
                                    {
                                        
                                        for (int k = 0; k < 3; k++)
                                        {
                                            if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, k) != null)
                                                aliens[iVal2][jVal2].getRect(iVal2, jVal2, k).translate(-15, 0);
                                        }
                                        onlyAgain4++;
                                    }
                                    if (aliens[iVal2][jVal2].getX() > 30)
                                    {
                                        onlyAgain4 = 0;
                                    }
                                }
                                if (aliens[iVal2][jVal2].getY() > 700)
                                {
                                    attacking2 = false;
                                    if (jVal2 == 0 && iVal2 == 4 || iVal2 == 5)
                                    {
                                        aliens[iVal2][jVal2] = new Flagship((int) surround.getX() + 60*iVal2, 100 + 50*jVal2);
                                        alienBody[iVal2][jVal2] = new Rectangle((int) surround.getX() + 60*iVal2, 117 + 50*jVal2, 35, 25);
                                    }
                                    if (jVal2 >= 3)
                                    {
                                        aliens[iVal2][jVal2] = new Drone((int) surround.getX() + 60*iVal2, 100 +50*jVal2);
                                        alienBody[iVal2][jVal2] = new Rectangle((int) surround.getX() + 60*iVal2, 100 + 50*jVal2, 35, 25);
                                    }
                                    if (jVal2 == 2 && iVal2 != 0 && iVal2 != 9)
                                    {
                                        aliens[iVal2][jVal2] = new Emissary((int) surround.getX() + 60*iVal2, 100 + 50*jVal2);
                                        alienBody[iVal2][jVal2] = new Rectangle((int) surround.getX() + 60*iVal2, 100 + 50*jVal2, 35, 25);
                                    }
                                    if (jVal2 == 1 && iVal2 != 0 && iVal2 != 9 && iVal2 != 8 && iVal2 != 1)
                                    {
                                        aliens[iVal2][jVal2] = new Hornet((int) surround.getX() + 60*iVal2, 100 + 50*jVal2);
                                        alienBody[iVal2][jVal2] = new Rectangle((int) surround.getX() + 60*iVal2, 115 + 50*jVal2, 35, 25);
                                    }
                                }
                                if (bullet.intersects(alienBody[iVal2][jVal2]) && !bulletPresent)
                                {   
                                    attacking2 = false;
                                    alienBody[iVal2][jVal2] = null;
                                    aliens[iVal2][jVal2] = null;
                                }
                            }
                            if (aliens[iVal2][jVal2] != null)
                            {
                                if (alienBody[iVal2][jVal2].intersects(ship))
                                {
                                    shipSeen.loseLife();
                                    alienBody[iVal2][jVal2] = null;
          
                                    aliens[iVal2][jVal2] = null;
                                    if (shipSeen.getLives() > 0)
                                    {
                                        dead = true;
                                    }                         
                                    if (shipSeen.getLives() == 0)
                                    {
                                        gameOver = true;
                                        play = false;
                                    }
                                }
                            } 
                            if (aliens[iVal2][jVal2] != null)
                            {
                                if (aliens[iVal2][jVal2].getY() > 400)
                                {                               
                                                                                
                                        if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, 0) != null && k2 == 0)
                                        {
                                            bulletGo21 = true;
                                        }
                                        if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, 1) != null && k2 == 4)// && k1 == bulletTimer)
                                        {   
                                            bulletGo22 = true;
                                        }
                                        if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, 2) != null && k2 == 8)// && k1 == bulletTimer)
                                        {
                                            bulletGo23 = true;
                                        }
                                        if (bulletGo21)
                                        {
                                            aliens[iVal2][jVal2].getRect(iVal2, jVal2, 0).translate(0, 20);
                                            //System.out.println("Bullet 1");
                                        }
                                        if (bulletGo22)
                                        {
                                            aliens[iVal2][jVal2].getRect(iVal2, jVal2, 1).translate(0, 20);
                                            //System.out.println("Bullet 2");
                                        }
                                        if (bulletGo23)
                                        {
                                            aliens[iVal2][jVal2].getRect(iVal2, jVal2, 2).translate(0, 20);
                                            //System.out.println("Bullet 3");
                                        }
                                            //bulletTimer2++;
                                        k2++;
                                        if (aliens[iVal2][jVal2].getY() > 700)
                                        {      
                                            bulletGo21 = false;
                                            bulletGo22 = false;
                                            bulletGo23 = false;
                                            //bulletTimer2 = 0;
                                            k2 = 0;
                                        }
                                }
                                
                            }              
                            for (int k = 0; k < 3; k++)
                                    {
                                        if (aliens[iVal2][jVal2] != null)
                                        {
                                        if (aliens[iVal2][jVal2].getRect(iVal2, jVal2, k).intersects(ship))
                                        {
                                        shipSeen.loseLife();
                                        alienBody[iVal2][jVal2] = null;
                                        aliens[iVal2][jVal2] = null;
                                        if (shipSeen.getLives() > 0)
                                        {
                                            dead = true;
                                        }                         
                                        if (shipSeen.getLives() == 0)
                                        {
                                            gameOver = true;
                                            play = false;
                                        }
                                    }
                                }
                            }
                 }
                 if (dead && shipSeen.getLives() > 0)
                 {
                     deadCount++;
                 }
                 if (deadCount == 10 && shipSeen.getLives() > 0)
                 {
                     dead = false;
                     play = true;
                     ship = new Rectangle(371, frame.getHeight() - 70, 30, 40);
                     shipSeen = new Player(365, frame.getHeight() - 75, shipSeen.getLives());
                     bullet = new Rectangle(384, frame.getHeight() - 83, 4, 10);
                     deadCount = 0;
                 }  

            }
            
           
        
        
        }
        Recter r = new Recter();
        frame.addKeyListener(r);
        Mover m = new Mover();
        Timer t = new Timer(100, m);
        t.start();
        frame.setVisible(true);
    }
}