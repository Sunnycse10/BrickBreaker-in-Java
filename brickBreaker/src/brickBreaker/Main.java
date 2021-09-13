package brickBreaker;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj= new JFrame();
		Gameplay gamePlay= new Gameplay();
		obj.add(gamePlay);
		//Menu menu = new Menu();
	 	//obj.setLayout(null);
		//obj.getContentPane().setBackground(Color.black);
		obj.setBounds(10, 10, 700, 600);
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setTitle("Brick Breaker");
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//obj.add(menu);

	}

}