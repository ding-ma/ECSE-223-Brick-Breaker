package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlayScreen extends JFrame implements Block223PlayModeInterface {
    JFrame frame = new JFrame();
    //TODO change to game variables
    int paddleLength = 5;
    int paddleXPosition = 10;
    int paddleYPosition = 360;
    private HashMap<Integer, Integer> hof;
    private JPanel paddle = new JPanel();
    private JButton startGame;
    private PaddleListener bp;

    public void PlayScreen() {
        frame.setFocusable(true);
        paddle.setFocusable(false);
        paddle.setBounds(paddleXPosition, paddleYPosition, paddleLength, 10);
        startGame = new JButton();
        startGame.setBounds(0, 0, 100, 20);
        startGame.setText("Start Game");
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.setFocusable(true);
                startGame.setVisible(false);
                // initiating a thread to start listening to keyboard inputs
                bp = new PaddleListener();
                Runnable r1 = new Runnable() {
                    @Override
                    public void run() {
                        // in the actual game, add keyListener to the game window
                        frame.addKeyListener(bp);
                    }
                };
                Thread t1 = new Thread(r1);
                t1.start();
                // to be on the safe side use join to start executing thread t1 before executing
                // the next thread
                try {
                    t1.join();
                } catch (InterruptedException e1) {
                }

                // initiating a thread to start the game loop
                Runnable r2 = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Block223Controller.startGame(PlayScreen.this);
                            startGame.setVisible(true);
                        } catch (InvalidInputException e) {
                        }
                    }
                };
                Thread t2 = new Thread(r2);
                t2.start();
            }
        });
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        JPanel p = new JPanel();
        p.setBounds(500, 0, 200, 500);
        p.setBackground(Color.LIGHT_GRAY);
        paddle.setBackground(Color.black);

        paddle.setVisible(true);

        frame.add(paddle);

        //hall of fame
//        JPanel t = new JPanel();
//        t.setBounds(0, 0, 500, 50);
//        frame.add(t);
//        JLabel title = new JLabel("Block 223!");
//        title.setFont(new Font("Verdana", 1, 20));
//        t.add(title);
//        t.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
//        String[] data = {"one", "two", "three", "four"};
//        JList<String> myList = new JList<String>(data);
//        myList.setSize(200, 500);
//        myList.setLocation(500, 0);
//        p.add(myList);
        frame.setVisible(true);
        frame.add(p);
        frame.add(startGame);
    }

    @Override
    public String takeInputs() {
        if (bp == null) {
            return "";
        }
        return bp.takeInputs();
    }

    @Override
    public void refresh() {

        paddle.setBounds(paddleXPosition, paddleYPosition, paddleLength, 10);
        frame.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
 
