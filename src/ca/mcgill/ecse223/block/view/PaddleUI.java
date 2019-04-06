package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Graphics;

import ca.mcgill.ecse223.block.controller.Block223Controller;

public class PaddleUI {
	
    public double paddleW= 40;
    public double paddleH = 5;
    public double x = (390 - paddleW) / 2;
    public double y = (390 - paddleH) / 2;
    private double xVelocity;
    private double yVelocity;

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, (int)paddleW, (int)paddleH);
    }
    
    public void addVelocity(double xVelocity, double yVelocity) {
        this.xVelocity += xVelocity;
        this.yVelocity += yVelocity;
    }

    public void tick() {
        x += xVelocity;
        y += yVelocity;
        
        if (x > 390) {
            x = -paddleW;
        } else if (x < -paddleW) {
            x = 390;
        }
        
        if (y > 390) {
            y = -paddleH;
        } else if (y < -paddleH) {
            y = 390;
        }
    }
}