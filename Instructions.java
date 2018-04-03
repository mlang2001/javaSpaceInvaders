import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Instructions
{
	boolean visible = true;
	Color blue = new Color(0, 0, 150);
	Color red = new Color(255, 0, 0);
	Color white = new Color(255, 255, 255);
	Font scoreText = new Font("Impact", Font.BOLD, 25);
	public void drawMe(Graphics g)
	{
		g.setColor(blue);
		g.fillRect(100, 100, 600, 300);

		g.setColor(white);
		g.drawRect(100, 100, 600, 300);

		g.setColor(red);

		g.drawString("Use 'A' and 'D' to control your player.", 150, 150);
		g.drawString("Press 'Space' to shoot", 150, 200);
		g.drawString("You cannot hold the buttons", 150, 250);
		g.drawString("Good Luck!", 150, 300);
		g.drawString("Press 'Space' to start", 150, 350);
	}
	public boolean getVisible()
	{
		return visible;
	}
	public void setVisible()
	{
		visible = false;
	}
}
