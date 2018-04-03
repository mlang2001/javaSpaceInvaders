import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
public class Projectile
{
    int x, y;
    int width, height;
     
    Color red;
    boolean visible;
     
    public Projectile(int x, int y)
    {
         
        this.x = x;
        this.y = y;
         
        this.width = 7;
        this.height = 30;
         
        this.red = new Color(255,0,0);

        visible = false;
 
    }

    public void playSoundShoot() 
    {
        try 
        {
            URL url = this.getClass().getClassLoader().getResource("Sound/shoot.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) 
        {
            exc.printStackTrace(System.out);
        }
    }

    public void drawMe(Graphics g)
    {
        if (visible)
        {
            g.setColor(red);
            g.fillOval(x,y,width,height);
        } 

    }

    public void moveUp()
    {
        if (visible)
        {
            y = y - 2;
            if (y < 0)
            {
                visible = false;
            }
        }
    }

    public int getX()
    {
        return x;
    }
     
    public int getY()
    {
        return y;
    }
     
    public int getWidth()
    {
        return width;
    }
     
    public int getHeight()
    {
        return height;
    }
    public boolean getVisible()
    {
        return visible;
    }
    public void setVisible()
    {
        visible = false;
    }
    public void fire(int x, int y)
    {
        if (!visible)
        {
            this.x = x + 20;
            this.y = y;
            visible = true;
            playSoundShoot();
        }
    }
}