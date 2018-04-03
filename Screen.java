import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
 
public class Screen extends JPanel implements KeyListener
{
	Projectile[] projectile;
	Player p1;
	Enemy[] e1;
	Enemy[] e2;
	Background b;
	Star[] s;
	Instructions inst;
	boolean allGone;
	Color red;
	int projNum, score, level, enemyMove;
	Font scoreText, winText;

	public Screen()
	{
		p1 = new Player(375, 500);
		e1 = new Enemy[6];
		e2 = new Enemy[6];
		b = new Background();
		score = 0;
		s = new Star[100];
		projectile = new Projectile[5];
		inst = new Instructions();
		level = 1;

		for(int i = 0; i < e1.length; i++)
		{
			e1[i] = new Enemy(i * 75 + 35, 100, level);
		}
		for(int i = 0; i < e2.length; i++)
		{
			e2[i] = new Enemy(i * 75 + 35, 50, level);
		}

		for(int i = 0; i < s.length; i++)
		{
			s[i] = new Star((int)(Math.random() * 800), (int)(Math.random() * 600));
		}

		for(int i = 0; i < projectile.length; i++)
		{
			projectile[i] = new Projectile(390, 500);;
		}

		enemyMove = 0;
		allGone = false;
		projNum = 0;
        scoreText = new Font("Impact", Font.BOLD, 25);
        winText = new Font("Impact", Font.BOLD, 100);
        red = new Color(255,0,0);


		setFocusable(true);
		addKeyListener(this);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		b.drawMe(g);

		for(int i = 0; i < s.length; i++)
		{
			s[i].drawMe(g, level);
		}

		g.setFont(scoreText);
		g.setColor(red);

		if (level == 1)
		{
			String scoreString = Integer.toString(score);
      		g.drawString("Score: " + scoreString, 700, 50);
		}

		else if (level == 2)
		{
			String scoreString = Integer.toString(score - 1);
      		g.drawString("Score: " + scoreString, 700, 50);
		}

		else if (level == 3)
		{
			String scoreString = Integer.toString(score - 2);
      		g.drawString("Score: " + scoreString, 700, 50);
		}
		

		String livesString = Integer.toString(p1.getLives());
        g.drawString("Lives: " + livesString, 40, 50);

		String levelString = Integer.toString(level);
        g.drawString("Level: " + levelString, 365, 50);

        if(inst.getVisible() == false)
        {
        	for(int i = 0; i < e1.length; i++)
			{
				e1[i].drawMe(g);
				e2[i].drawMe(g);
				if(e1[i].getVisible() == false && e1[i].getScoring() == true)
				{
					score++;
				}
				else if(e2[i].getVisible() == false && e2[i].getScoring() == true)
				{
					score++;
				}
				e1[i].setScoring();	
				e2[i].setScoring();
			}
			for(int i = 0; i < projectile.length; i++)
			{
				projectile[i].drawMe(g);
			}
			p1.drawMe(g);	

			if (score == 6 && level == 1)
			{
				nextLevel();
				score++;
				for(int i = 0; i < projectile.length; i++)
				{
					projectile[i].setVisible();
				}
			}

			else if (score == 19 && level == 2)
			{
				nextLevel();
				score++;
				for(int i = 0; i < projectile.length; i++)
				{
					projectile[i].setVisible();
				}
			}
			if(level == 1)
			{
				for(int i = 0; i < e1.length; i++)
				{
					e2[i].falseVisible();
				}
			}
			for (int i = 0; i < e2.length; i++)
			{
				if(level == 2 && score <= 7)
				{
					e2[i].setVisible();
				}
			}
			if(level == 3)
			{
				for(int i = 0; i < e1.length; i++)
				{
					e1[i].falseVisible();
					e2[i].falseVisible();
				}

				for(int i = 0; i < projectile.length; i++)
				{
					projectile[i].setVisible();
				}

				g.setFont(winText);
				g.setColor(red);
				g.drawString("YOU WIN", 225, 200);
			}
			if(p1.getLives() <= 0)
			{
				for(int i = 0; i < e1.length; i++)
				{
					e1[i].falseVisible();
				}
				for(int i = 0; i < e2.length; i++)
				{
					e2[i].falseVisible();
				}
				g.setFont(winText);
				g.setColor(red);
				g.drawString("YOU LOSE", 225, 200);
			}
        }

		if (inst.getVisible())
		{
			inst.drawMe(g);
		}
	}
	public void animate()
	{
        while(true)
        {
        	for(int i = 0; i < projectile.length; i++)
			{
				projectile[i].moveUp();
			}

        	for(int cell = 0; cell < e1.length; cell++)
        	{
        		for(int i = 0; i < projectile.length; i++)
				{
					e1[cell].checkCollision(projectile[i]);
				}
        	}
        	for(int cell = 0; cell < e2.length; cell++)
        	{
        		for(int i = 0; i < projectile.length; i++)
				{
					e2[cell].checkCollision(projectile[i]);
				}
        	}

        	for(int i = 0; i < e1.length; i++)
			{
				if(p1.checkCollision(e1[i]))
				{
					for(int j = 0; j < e1.length; j++)
					{
						e1[j].reset();
						e1[j].setVisible();
					}
					if(level == 1 && p1.getLives() != 0)
					{
						score = 0;
					}
					else if(level == 2 && p1.getLives() != 0)
					{
						score = 7;
					}
				}
				else if(p1.checkCollision(e2[i]))
				{
					for(int j = 0; j < e2.length; j++)
					{
						e2[j].reset();
						e2[j].setVisible();
					}
					if(level == 1 && p1.getLives() != 0)
					{
						score = 0;
					}
					else if(level == 2 && p1.getLives() != 0)
					{
						score = 7;
					}
				}
			}

        	for(int i = 0; i < projectile.length + 1; i++)
			{
				e1[i].moveRight(level);
				e1[i].moveLeft(level);
				e1[i].moveDown(level);
				e1[i].incrementMove();
			}
			for(int i = 0; i < projectile.length + 1; i++)
			{
				e2[i].moveRight(level);
				e2[i].moveLeft(level);
				e2[i].moveDown(level);
				e2[i].incrementMove();
			}

			for(int i = 0; i < s.length; i++)
			{
				s[i].moveDown(level);
			}
        	
            //wait for .01 second
            try 
            {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
 
            //repaint the graphics drawn
            repaint();
        }
    }

    public void nextLevel()
    {
    	level++;
        for(int i = 0; i < e1.length; i++)
		{
			e1[i].setVisible();
			e1[i].reset();
			e2[i].setVisible();
			e2[i].reset();
		}
        enemyMove = 0;
    }
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 65)
		{
			p1.moveLeft();
		}
		if(e.getKeyCode() == 68)
		{
			p1.moveRight();
		}
		if(e.getKeyCode() == 32)
		{
			projectile[projNum].fire(p1.getX(), p1.getY());
			projNum = projNum + 1;
			if (projNum == 5)
			{
				projNum = 0;
			}

			if(inst.getVisible() == true)
			{
				inst.setVisible();
			}
		}
		if(e.getKeyCode() == 80)
		{
			if(level < 3)
			{
				nextLevel();
				score++;
			}
		}
		repaint();
	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
	
}