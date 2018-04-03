import java.awt.Color;
import java.awt.Graphics;
public class Background
{
	Color navy;
	public Background()
	{
		navy = new Color(0, 0, 35);
	}
	public void drawMe(Graphics g)
	{
		g.setColor(navy);
		g.fillRect(0, 0, 800, 600);
	}
}