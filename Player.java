import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class Player
{
	int x, y;
	int[] xArray, yArray; 
	int width, height;
	int lives, direction;
	Color blue, red, green;
	public BufferedImage playerImg;
	public BufferedImage playerImg2;
	
	public Player(int x, int y)
	{
		xArray = new int[3];
		yArray = new int[3];
		this.x = x;
		this.y = y;
		width  = 50;
		height = 50;
		lives = 3;
		blue = new Color(0, 0, 255);
		red = new Color(255, 0, 0);
		try {
	            playerImg = ImageIO.read(new File("PlanetExpress.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    try {
	            playerImg2 = ImageIO.read(new File("PlanetExpress2.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public void drawMe(Graphics g)
	{
		if(direction == 1)
		{
			g.drawImage(playerImg, x, y, null);
		}
		else if(direction == 0)
		{
			g.drawImage(playerImg2, x, y, null);
		}
	}
	public void moveLeft()
	{
		direction = 0;
		x = x - 10;
	}
	public void moveRight()
	{
		direction = 1;
		x = x + 10;
	}
    public int getX()
    {
        return x;
    } 
    public int getY()
    {
        return y;
    }
    public int getLives()
    {
    	return lives;
    }
    public void loseLives()
    {
    	lives = lives - 1;
    }
    public boolean checkCollision(Enemy e)
	{
		if (e.getVisible())
		{
			if( e.getX() + e.getWidth() >= x  &&  e.getX() <= x + width  &&  e.getY() + e.getHeight() >= y  &&  e.getY() <= y + height )
			{
				loseLives();
				e.setVisible();
				return true;
			}
		}
		return false;	
	}
}