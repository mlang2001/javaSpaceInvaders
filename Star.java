import java.awt.Color;
import java.awt.Graphics;
public class Star
{
	Color yellow, blue, red, green;
	int x, y, width, height;
	public Star(int x, int y)
	{
		yellow = new Color(255, 255, 0);
		blue = new Color(0, 230, 255);
		red = new Color(255, 0, 0);
		green = new Color(0, 255, 0);
		this.x = x;
		this.y = y;
		width = (int)(Math.random() * 5 + 1);
		height = width;
	}
	public void drawMe(Graphics g, int level)
	{
		if (level == 1)
		{
			g.setColor(yellow);
			g.fillRect(x, y, width, height);
		}
		if (level == 2)
		{
			g.setColor(blue);
			g.fillRect(x, y, width, height * 5);
		}
		if(level == 3)
		{
			int num = (int)(Math.random() * 4 + 1);
			if(num == 1)
			{
				g.setColor(blue);
				g.fillRect(x, y, width * 5, height * 5);
			}
			else if(num == 2)
			{
				g.setColor(red);
				g.fillRect(x, y, width * 5, height * 5);
			}
			else if(num == 3)
			{
				g.setColor(green);
				g.fillRect(x, y, width * 5, height * 5);
			}
			else if(num == 4)
			{
				g.setColor(yellow);
				g.fillRect(x, y, width * 5, height * 5);
			}
		}
	}
	public void moveDown(int level)
	{
		if (level == 1)
		{
			if(y < 600)
			{
				y++;
			}
			else if(y == 600)
			{
				y = 0;
				x = (int)(Math.random() * 800);
			}
		}
		if (level == 2)
		{
			if(y < 600)
			{
				y = y + 5;
			}
			else if(y == 600)
			{
				y = 0;
				x = (int)(Math.random() * 800);
			}
		}
		if (level == 3)
		{
			if(y < 600)
			{
				y++;
			}
			else if(y == 600)
			{
				y = 0;
				x = (int)(Math.random() * 800);
			}
		}
	}
}