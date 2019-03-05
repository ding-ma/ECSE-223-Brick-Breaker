package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.model.*;
import javax.swing.*;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateGame {

    Game currentGame = Block223Application.getCurrentGame();
    String currentName = currentGame.getName();
    String error = null;

    private static JLabel errorMessage;

    public void UpdateGame(){
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        errorMessage.setBounds(125, 250, 200, 200);

        JFrame frame = new JFrame();
        JLabel title = new JLabel();
        JLabel instruction = new JLabel();

        JLabel oldNameLabel = new JLabel("Current name: ");
        JTextField oldName = new JTextField(currentName);

        JLabel newNameLabel = new JLabel("New name: ");
        JTextField newName = new JTextField();

        JLabel nbLevelsLabel = new JLabel("Number of levels: ");
        JTextField nbLevels = new JTextField(String.valueOf(currentGame.numberOfLevels()));

        JLabel nbBlocksPerLevelLabel = new JLabel("Number of blocks per level: ");
        JTextField nbBlocksPerLevel = new JTextField(String.valueOf(currentGame.getNrBlocksPerLevel()));

        JLabel minBallSpeedXLabel = new JLabel("Minimum ball speed in X: ");
        JTextField minBallSpeedX = new JTextField(String.valueOf(currentGame.getBall().getMinBallSpeedX()));
        JLabel minBallSpeedYLabel = new JLabel("Maximum ball speed in Y: ");
        JTextField minBallSpeedY = new JTextField(String.valueOf(currentGame.getBall().getMinBallSpeedY()));
        JLabel ballSpeedIncreaseFactorLabel = new JLabel("Ball speed increase factor: ");
        JTextField ballSpeedIncreaseFactor = new JTextField(String.valueOf(currentGame.getBall().getBallSpeedIncreaseFactor()));
        JLabel maxPaddleLengthLabel = new JLabel("Maximum paddle length: ");
        JTextField maxPaddleLength = new JTextField(String.valueOf(currentGame.getPaddle().getMaxPaddleLength()));
        JLabel minPaddleLengthLabel = new JLabel("Minimum paddle length: ");
        JTextField minPaddleLength = new JTextField(String.valueOf(currentGame.getPaddle().getMinPaddleLength()));
        JButton confirm = new JButton();

        frame.setSize(500,400);
        frame.setLayout(null);
        frame.setVisible(true);

//TODO Anne-Julie
        title.setText("Update Game");
        title.setBounds(10,0,400,30);
        frame.add(title);

        instruction.setText("Change the values you wish to update.");
        instruction.setBounds(10, 31, 400, 30);
        frame.add(instruction);

        oldNameLabel.setBounds(5, 51, 100, 30);
        frame.add(oldNameLabel);
        oldName.setBounds(180, 51, 200, 30);
        oldName.setEditable(false);
        frame.add(oldName);

        newNameLabel.setBounds(5, 82, 200, 30);
        frame.add(newNameLabel);
        newName.setBounds(180, 82, 200, 30);
        frame.add(newName);
        nbLevelsLabel.setBounds(5, 113, 200, 30);
        frame.add(nbLevels);
        nbLevels.setBounds(180, 113, 200, 30);
        frame.add(nbLevelsLabel);

        nbBlocksPerLevelLabel.setBounds(5, 144, 200, 30);
        frame.add(nbBlocksPerLevelLabel);
        nbBlocksPerLevel.setBounds(180, 144, 200, 30);
        frame.add(nbBlocksPerLevel);

        minBallSpeedXLabel.setBounds(5, 175, 200, 30);
        frame.add(minBallSpeedXLabel);
        minBallSpeedX.setBounds(180, 175, 200, 30);
        frame.add(minBallSpeedX);

        minBallSpeedYLabel.setBounds(5, 206, 200, 30);
        frame.add(minBallSpeedYLabel);
        minBallSpeedY.setBounds(180, 206, 200, 30);
        frame.add(minBallSpeedY);

        ballSpeedIncreaseFactorLabel.setBounds(5, 237, 200, 30);
        frame.add(ballSpeedIncreaseFactorLabel);
        ballSpeedIncreaseFactor.setBounds(180, 237, 200, 30);
        frame.add(ballSpeedIncreaseFactor);

        maxPaddleLengthLabel.setBounds(5, 268, 200, 30);
        frame.add(maxPaddleLengthLabel);
        maxPaddleLength.setBounds(180, 268, 200, 30);
        frame.add(maxPaddleLength);

        minPaddleLengthLabel.setBounds(5, 296, 200, 30);
        frame.add(minPaddleLengthLabel);
        minPaddleLength.setBounds(180, 296, 200, 30);
        frame.add(minPaddleLength);
        confirm.setText("Confirm");
        confirm.setBounds(150, 330, 120, 30);
        frame.add(confirm);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nrLevels = Integer.parseInt(nbLevels.getText());
                int nrBlocksPerLevel = Integer.parseInt(nbBlocksPerLevel.getText());
                int minSpeedX = Integer.parseInt(minBallSpeedX.getText());
                int minSpeedY = Integer.parseInt(minBallSpeedY.getText());
                double ballSpeedIncrease = Double.parseDouble(ballSpeedIncreaseFactor.getText());
                int maxPadLen = Integer.parseInt(maxPaddleLength.getText());
                int minPadLen = Integer.parseInt(minPaddleLength.getText());

                try {
                    Block223Controller.updateGame(newName.getText(), nrLevels, nrBlocksPerLevel,
                            minSpeedX, minSpeedY, ballSpeedIncrease, maxPadLen, minPadLen);
                    GameScreen gameScreen = new GameScreen();
                    gameScreen.refreshData();
                    frame.dispose();
                } catch (InvalidInputException a){
                    error =  a.getMessage();
                }
                refreshData();
            }
        });
    }
    private void refreshData() {
        // error
        errorMessage.setText(error);

    }
}
