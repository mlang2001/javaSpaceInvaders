import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Score
{
	int score;
	Color red;
	Font scoreText;
	public Score()
	{
		score = 0;
		red = new Color(255,0,0);
        scoreText = new Font("Impact", Font.BOLD, 25);
	}
	public void drawMe(Graphics g)
	{
		g.setFont(scoreText);
		g.setColor(red);
        g.drawString("Score: " + score, 700, 50);
	}
	public void increase()
	{
		score = score + 1;
	}
}