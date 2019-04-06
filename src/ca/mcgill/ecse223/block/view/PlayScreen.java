package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class PlayScreen extends JPanel {
    JFrame frame = new JFrame();
    //TODO change to game variables
    int paddleLength = 100;
    int paddleXPosition = 10;
    int paddleYPosition = 360;
    private HashMap<Integer, Integer> hof;
    private JPanel paddle = new JPanel();
    private volatile String userinputs = "";

    public synchronized void keyPressed(KeyEvent e) {
        try {
            keyInputs(e);
        } catch (InvalidInputException e1) {
            System.out.print(e1);
        }
    }

    private synchronized String keyInputs(KeyEvent e) throws InvalidInputException {
        int location = e.getKeyCode();
        if (location == KeyEvent.VK_LEFT) {
            userinputs += "l";
        } else if (location == KeyEvent.VK_RIGHT) {
            userinputs += "r";
        } else if (location == KeyEvent.VK_SPACE) {
            userinputs += " ";
        } else {
        }
        return userinputs;
    }

    public void PlayScreen() {
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        JPanel p = new JPanel();
        p.setBounds(500, 0, 200, 500);
        p.setBackground(Color.LIGHT_GRAY);
        paddle.setBackground(Color.black);
        paddle.setBounds(paddleXPosition, paddleYPosition, paddleLength, 10);
        paddle.setVisible(true);
        paddle.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    paddleXPosition = paddleXPosition - 10;
                    paddle.repaint();
                }
                if (key == KeyEvent.VK_RIGHT) {
                    paddleXPosition = paddleXPosition + 10;
                    paddle.repaint();
                }
            }
        });
        //TODO start game method
        // Block223Controller.updatePaddlePosition(userinputs);

        frame.add(paddle);

        //hall of fame
        JPanel t = new JPanel();
        t.setBounds(0, 0, 500, 50);
        frame.add(t);
        JLabel title = new JLabel("Block 223!");
        title.setFont(new Font("Verdana", 1, 20));
        t.add(title);
        t.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
        String[] data = {"one", "two", "three", "four"};
        JList<String> myList = new JList<String>(data);
        myList.setSize(200, 500);
        myList.setLocation(500, 0);

        //get
        frame.setVisible(true);


        //add list to panel 
        p.add(myList);

        frame.add(p);
    }


    //display hall of fame


}
 
