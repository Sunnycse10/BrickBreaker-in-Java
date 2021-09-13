package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;




public class Menu implements MouseListener{
	public boolean draw=true;
	/*public Rectangle playbutton= new Rectangle(Gameplay.WIDTH+250, 150, 100,50);
	public Rectangle helpbutton= new Rectangle(Gameplay.WIDTH+250, 250, 100,50);
	public Rectangle quitbutton= new Rectangle(Gameplay.WIDTH+250, 350, 100,50);*/
	//public HoverHandler hover= new HoverHandler();
	
	//drawing the options of menu, as String
	public void paintComponent(Graphics g)
	{
		
		Font font1= new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g.drawString("Build Brick Game", Gameplay.WIDTH+200, 100);
		
		Font font2= new Font("arial", Font.BOLD, 30);
		
		g.setFont(font2);
		g.drawString("play", Gameplay.WIDTH+260, 180);
		g.drawString("help", Gameplay.WIDTH+260, 280);
		g.drawString("quit", Gameplay.WIDTH+260, 380);
		
		
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//if Play is selected, state will be changed to game state, hence game will start 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX();
		int my=e.getY();
		if(mx>=Gameplay.WIDTH+250 && mx<=Gameplay.WIDTH+350)
		{
			if(my>=150 &&my<=200)
			{
				Gameplay.state=Gameplay.STATE.Game; 
			}
		}
		if(mx>=Gameplay.WIDTH+250 && mx<=Gameplay.WIDTH+350 && my>= 350 && my<=400)
		{
			System.exit(1);
		}

		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}