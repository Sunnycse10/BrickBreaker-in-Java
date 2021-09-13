package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BuildBrick {
	public int map[][];
	public int brickheight;
	public int brickwidth;
	//This Constructor will make a 3*7 bricks and set their values to 1
	//I will draw them if their value is 1
	// after every collision, the collided brick will be set to 0, hence it won't be drawn
	public BuildBrick(int row,int col)
	{
		map=new int[row][col];
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[0].length;j++)
				map[i][j]=1;
		brickheight=150/row;
		brickwidth=540/col;
	}
	// this draw method will draw the brick
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.YELLOW);
					g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
					g.setStroke(new BasicStroke(4));//set stroke is used to draw borderline between the bricks
					g.setColor(Color.BLACK);
					g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
				}
			}
	}
	public void setBrickVal(int row, int col)
	{
		map[row][col]=0;
	}
	

}