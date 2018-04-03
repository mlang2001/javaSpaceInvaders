import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class Enemy
{
	int x, y, originalX, originalY, width, height, level, enemyMove;
	Color white, red;
	boolean visible, scoring;
	public BufferedImage enemyImg;
	public Enemy(int x, int y, int level)
	{
		this.x = x;
		this.y = y;
		originalX = x;
		originalY = y;
		this.level = level;
		width  = 50;
		height = 30;
		white = new Color(255, 255, 255);
		red = new Color(255, 0, 0);
		scoring = false;
		visible = true;
		enemyMove = 0;
		try {
	            enemyImg = ImageIO.read(new File("enemy.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public void drawMe(Graphics g)
	{
		if(visible)
		{
			g.drawImage(enemyImg, x, y, null);
		}	
	}
	public void incrementMove()
	{
		enemyMove++;
	}
	public void reset()
	{
		enemyMove = 0;
		x = originalX;
		y = originalY;
	}
	public void moveLeft(int level)
	{
		if(level == 1)
		{
			if(enemyMove > 300 && enemyMove <= 600)
			{
		        x = x - 1;
		        if (enemyMove == 600)
		        {
		        	enemyMove = 0;
		        }
		    }
		}
		else if(level == 2)
		{
			if(enemyMove > 60 && enemyMove <= 120)
			{
		        x = x - 5;
		        
		    }
		    if(enemyMove > 120)
		    {
		        enemyMove = 0;
		   	}
		}
	}
	public void moveRight(int level)
	{
		if(level == 1)
		{
			if(enemyMove <= 300)
			{
		        x = x + 1;
		    }
		}
		else if(level == 2)
		{
			if(enemyMove <= 60)
			{
				x = x + 5;
		    }
		}
	}
	public void moveDown(int level)
	{
		if (level == 1)
		{
			if(enemyMove == 300)
		    {
			    y = y + 10;
		    }
		    else if(enemyMove == 0)
		    {
		    	y = y + 10;
		    }
		}
		else if (level == 2)
		{
			if(enemyMove == 60)
		    {
			    y = y + 20;
		    }
		    else if(enemyMove == 0)
		    {
		    	y = y + 20;
		    }
		}
		
	}
	public void playSoundDeath() 
	{
        try 
        {
            URL url = this.getClass().getClassLoader().getResource("Sound/death.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) 
        {
            exc.printStackTrace(System.out);
        }
    }
	public void checkCollision(Projectile p)
	{
		if (visible && p.getVisible())
		{
			if( p.getX() + p.getWidth() >= x  &&  p.getX() <= x + width  &&  p.getY() + p.getHeight() >= y  &&  p.getY() <= y + height )
			{
				visible = false;	
				scoring = true;	
				playSoundDeath();
				p.setVisible();	
			}
			scoring = false;
		}	
	}
	
	public boolean getScoring()
	{
		return scoring;
	}

	public boolean getVisible()
	{
		return visible;
	}

	public void setVisible()
	{
		visible = true;
	}

	public void falseVisible()
	{
		visible = false;
	}

	public void setScoring()
	{
		scoring = false;
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
}