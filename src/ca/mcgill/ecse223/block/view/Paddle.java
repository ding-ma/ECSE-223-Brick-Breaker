package ca.mcgill.ecse223.block.view;


import java.awt.*;




public class Paddle {

    public static final int RECT_WIDTH = 20;
    public static final int RECT_HEIGHT = 10;
    public int x = 175;
    public int y = 340;
    private int xVelocity;
    private int yVelocity;

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, RECT_WIDTH, RECT_HEIGHT);
    }
    
    public void addVelocity(int xVelocity, int yVelocity) {
        this.x += xVelocity;
        this.y += yVelocity;
    }

    public void tick() {
        x += xVelocity;
        y += yVelocity;
        
        if (x > 390) {
            x = -RECT_WIDTH;
        } else if (x < -RECT_WIDTH) {
            x = 390;
        }
        
        if (y > 390) {
            y = -RECT_HEIGHT;
        } else if (y < -RECT_HEIGHT) {
            y = 390;
        }
    }
}