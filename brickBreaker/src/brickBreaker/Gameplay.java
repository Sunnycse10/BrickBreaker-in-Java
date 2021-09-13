package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener{
	private boolean play=false;
	private boolean highlight=false;
	private boolean highlight1=false;
	private boolean highlight2=false;
	private int score=0;
	private int bonus=5;
	private int totalBricks=21;
	private Timer time;
	private int delay=8;
	private int playerX=310;
	private int ballX=350;
	private int ballY=530;
	private int balldirX=-1;
	private int balldirY=-2;
	private BuildBrick bricks;
	public Rectangle playbutton= new Rectangle(Gameplay.WIDTH+250, 150, 100,50);
	public Rectangle helpbutton= new Rectangle(Gameplay.WIDTH+250, 250, 100,50);
	public Rectangle quitbutton= new Rectangle(Gameplay.WIDTH+250, 350, 100,50);
	//private HoverHandler hover= new HoverHandler();
	private Menu menu= new Menu();// Could've created draw method in menu as static , so wouldn't need to create object of it
	public static enum STATE //this states will define if the game is in menu state or game state
	{
		Menu,
		Game
	};
	public static STATE state= STATE.Menu;
	
	public Gameplay()
	{
		bricks=new BuildBrick(3, 7);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this.menu);
		//addMouseMotionListener(this.menu);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(delay,this);
		time.start();
		
		
	}

	


	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//setBackgroundColor
		
		super.paintComponent(g);
		this.setBackground(Color.black);
		if(state==STATE.Game)
		{
		
		
		bricks.draw((Graphics2D)g);
		
		//drawing the paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 10);
		
		//drawing the ball
		g.setColor(Color.RED);
		g.fillOval(ballX, ballY, 20, 20);
		
		//updating score
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString("score: "+score, 550, 20);
		
		//losing Condition
		if(ballY>570)
		{
			play=false;
			this.setBackground(Color.white);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 30));
			g.drawString("GAME OVER",250 , 300);
			g.drawString("YOUR SCORE: "+score, 200, 330);
			time.stop();
			
		}
		if(totalBricks==0 && ballY<570)
		{
			play=false;
			bricks=new BuildBrick(3, 7);
			playerX=310;
			ballX= 310;
			ballY=530;
			totalBricks=21;
			delay=6;
			bonus=10;
			time.setDelay(delay);
			
		}
		}
		else if(state==STATE.Menu)
		{
			//menu= new Menu();
			g.setColor(Color.WHITE);
			Graphics2D g2d= (Graphics2D)g;
			if(highlight)
			{
				g.setColor(Color.RED);
			}
			g2d.draw(playbutton);
			g.setColor(Color.WHITE);
			if(highlight1)
			{
				g.setColor(Color.RED);
			}
			g2d.draw(helpbutton);
			g.setColor(Color.WHITE);
			if(highlight2)
			{
				g.setColor(Color.RED);
			}
			g2d.draw(quitbutton);
			menu.paintComponent(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(state==STATE.Game)
		{
		if(play)
		{
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 20)))
			{
				balldirY=-balldirY;
			}
			A: for(int i=0;i<bricks.map.length;i++)
				for(int j=0;j<bricks.map[0].length;j++)
				{
					if(bricks.map[i][j]>0)//if the brick exists, will check collision between the ball and brick
					{
						int brickX=bricks.brickwidth;
						int brickY=bricks.brickheight;
						Rectangle BrickRect= new Rectangle(brickX*j+80, brickY*i+50, brickX, brickY);
						Rectangle BallRect= new Rectangle(ballX, ballY, 20, 20);//declaring a ball as rectangle to find if it intersects
						Rectangle refBrick= BrickRect;
						if(BallRect.intersects(BrickRect))
						{
							bricks.setBrickVal(i,j);// if the ball and a brick collide, will set the brick value to 0, hence it won't be drawn later
							score+=bonus;
							totalBricks--;
							if(ballX+19<=refBrick.x||ballX+1>refBrick.x+refBrick.width)//if the ball touches the brick from left or right, it will go reverse
								balldirX=-balldirX;//changing X direction if it collides with brick from left or right
							else
								balldirY=-balldirY;//otherwise, if it collides from top or bottom, changing Y direction
							break A;// after every collision breaking both loops
						}
					}
				}
			ballX+=balldirX;
			ballY+=balldirY;
			if(ballX<0 || ballX>678)
			{
				balldirX=-balldirX;//if the ball hits the wall from left or right, the X direction will be reverse
			}
			if(ballY<0)
			{
				balldirY=-balldirY;//if it hits the wall from top, Y direction will be reverse
			}
		}

		
		repaint();
		Toolkit.getDefaultToolkit().sync();//toolkit.getdefault is used to make the transition smooth
		}
		
	}
	//if left,right keys are presssed, the paddle will go left or right respectively
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(state==STATE.Game)
		{
		int c= e.getKeyCode();
		if(c==KeyEvent.VK_LEFT)
		{

			if(playerX<5)
				playerX=5;
			else
				playerX-=10;
			play=true;
		}
		if(c== KeyEvent.VK_RIGHT)
		{ 	
			if(playerX>595)
				playerX=595;
			else
				playerX+=10;
			play=true;
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



// this method is used to make hoverEffect of menu options as I didn't create them as JButton
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==STATE.Menu)
		{
			int mx=e.getX();
			int my=e.getY();
			if(mx>=Gameplay.WIDTH+250 && mx<=Gameplay.WIDTH+350 &&my>=150 &&my<=200)
			{
				highlight=true;
				repaint();
			}
			else
			{
				highlight=false;
				repaint();
			}
			if(mx>=Gameplay.WIDTH+250 && mx<=Gameplay.WIDTH+350 &&my>=250 &&my<=300)
			{
				highlight1=true;
				repaint();
			}
			else
			{
				highlight1=false;
				repaint();
			}
			if(mx>=Gameplay.WIDTH+250 && mx<=Gameplay.WIDTH+350 &&my>=350 &&my<=400)
			{
				highlight2=true;
				repaint();
			}
			else
			{
				highlight2=false;
				repaint();
			}
		}
		
	}
	

}