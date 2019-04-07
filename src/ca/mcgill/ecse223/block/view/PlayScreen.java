package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class PlayScreen extends JFrame implements Block223PlayModeInterface {
    JFrame frame = new JFrame();
    public PaddleBallBlock paddleBallBlock = new PaddleBallBlock();
    int paddleXPosition = 10;
    int paddleYPosition = 360;
    private HashMap<Integer, Integer> hof;
    private JPanel paddle = new JPanel();
    private JButton startGame;
    private PaddleListener bp;
    //TODO change to game variables
    int paddleLength = 100;
    JTextArea gameArea = new JTextArea();

    public void PlayScreen() {
        JPanel playingArea = new JPanel();
        playingArea.setLayout(new BorderLayout());
        playingArea.setBackground(Color.pink);
        playingArea.setPreferredSize(new Dimension(390, 390));
        playingArea.setBorder(new EmptyBorder(10, 10, 10, 0));

        paddleBallBlock.setMinimumSize(new Dimension(390, 390));
        playingArea.add(paddleBallBlock);

        gameArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameArea);
        scrollPane.setPreferredSize(new Dimension(1000, 100));
        frame.add(scrollPane);
        frame.setFocusable(true);
//        paddle.setFocusable(false);
//        paddle.setBounds(paddleXPosition, paddleYPosition, paddleLength, 10);
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
                        gameArea.addKeyListener(bp);
                        gameArea.requestFocus();

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

        //   frame.add(paddle);

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
        System.out.println(Block223Application.getCurrentPlayableGame().getCurrentPaddleLength());
        System.out.println(Block223Application.getCurrentPlayableGame().getCurrentPaddleX());
        paddleBallBlock.repaint();
    }
}
