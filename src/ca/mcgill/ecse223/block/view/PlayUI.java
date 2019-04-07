package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.application.Block223Application;

public class PlayUI extends JPanel{
	
	private Rectangle2D paddle;
	//System.out.println("name: "+ (Block223Application.getCurrentPlayableGame().getName()));
	private final double PADDLE_WIDTH = Block223Application.getCurrentPlayableGame().getCurrentPaddleLength(); // paddle length
	private final int PADDLE_HEIGHT = 5; // paddle height
	
	public PlayUI (){
		super();
		init();
	}
	
	private void init() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(390, 390));
		repaint();
	}
	
    public void doPaint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Double paddleX = Block223Application.getCurrentPlayableGame().getCurrentPaddleX();
		Double paddleY = Block223Application.getCurrentPlayableGame().getCurrentPaddleY();
		Rectangle2D paddle = new Rectangle2D.Double(paddleX, 355, Block223Application.getCurrentPlayableGame().getCurrentPaddleLength(), PADDLE_HEIGHT); // 350
		g2d.fill(paddle);
		g2d.setColor(Color.BLUE);		
		g2d.draw(paddle);
       
    }
    
    @Override
	public void paintComponent(Graphics g) {
		if (!(Block223Application.getCurrentPlayableGame() == null)) {
			super.paintComponent(g);
			doPaint(g);
		}
	}
}