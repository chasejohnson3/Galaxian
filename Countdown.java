import javax.swing.*;
import java.awt.event.*;

public class Countdown
{
    public static void main(String[] args)
    {
        System.out.print("Hi, welcome to the FINAL COUNTDOWN!!!!! \n" );
        
        
        //inner class to tell the Timer what to do
        class Counter implements ActionListener
        {
            private int count;
            public Counter()
            {
                count = 10;
            }
            public void actionPerformed(ActionEvent e)
            {
                //Directions
                if (count > 0)
                {
                System.out.println(count);
                count--;
                }
                if (count == 0)
                {
                    System.out.println("The FINAL COUNTDOWN has arrived");
                    count--;
                }
            }
        }
        Counter c = new Counter();
        Timer t = new Timer(1000, c);
        t.start();
    }
}
            