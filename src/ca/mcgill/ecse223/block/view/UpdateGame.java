package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class UpdateGame {
    public void UpdateGame(String name){
        JFrame frame = new JFrame();
        JLabel title = new JLabel();
        JLabel oldNameLabel = new JLabel("Current name: ");
        JTextField oldName = new JTextField(name);
        JLabel newNameLabel = new JLabel("New name: ");
        JTextField newName = new JTextField();
        JLabel nbLevelsLabel = new JLabel("Number of levels: ");
        JTextField nbLevels = new JTextField();
        JLabel nbBlocksPerLevelLabel = new JLabel("Number of blocks per level: ");
        JTextField nbBlocksPerLevel = new JTextField();
        JLabel minBallSpeedXLabel = new JLabel("Minimum ball speed in X: ");
        JTextField minBallSpeedX = new JTextField();
        JLabel minBallSpeedYLabel = new JLabel("Maximum ball speed in Y: ");
        JTextField minBallSpeedY = new JTextField();
        JLabel ballSpeedIncreaseFactorLabel = new JLabel("Ball speed increase factor: ");
        JTextField ballSpeedIncreaseFactor = new JTextField();
        JLabel maxPaddleLengthLabel = new JLabel("Maximum paddle length: ");
        JTextField maxPaddleLength = new JTextField();
        JLabel minPaddleLengthLabel = new JLabel("Minimum paddle length: ");
        JTextField minPaddleLength = new JTextField();
        JButton confirm = new JButton();

        frame.setSize(500,400);
        frame.setLayout(null);
        frame.setVisible(true);

//TODO Anne-Julie
        title.setText("Update Game");
        title.setBounds(0,0,400,30);
        frame.add(title);

        oldNameLabel.setBounds(0, 51, 100, 30);
        frame.add(oldNameLabel);
        oldName.setBounds(170, 51, 200, 30);
        oldName.setEditable(false);
        frame.add(oldName);

        newNameLabel.setBounds(0, 82, 200, 30);
        frame.add(newNameLabel);
        newName.setBounds(170, 82, 200, 30);
        frame.add(newName);
        
        nbLevelsLabel.setBounds(0, 113, 200, 30);
        frame.add(nbLevels);
        nbLevels.setBounds(170, 113, 200, 30);
        frame.add(nbLevelsLabel);

        nbBlocksPerLevelLabel.setBounds(0, 144, 200, 30);
        frame.add(nbBlocksPerLevelLabel);
        nbBlocksPerLevel.setBounds(170, 144, 200, 30);
        frame.add(nbBlocksPerLevel);

        minBallSpeedXLabel.setBounds(0, 175, 200, 30);
        frame.add(minBallSpeedXLabel);
        minBallSpeedX.setBounds(170, 175, 200, 30);
        frame.add(minBallSpeedX);

        minBallSpeedYLabel.setBounds(0, 206, 200, 30);
        frame.add(minBallSpeedYLabel);
        minBallSpeedY.setBounds(170, 206, 200, 30);
        frame.add(minBallSpeedY);

        ballSpeedIncreaseFactorLabel.setBounds(0, 237, 200, 30);
        frame.add(ballSpeedIncreaseFactorLabel);
        ballSpeedIncreaseFactor.setBounds(170, 237, 200, 30);
        frame.add(ballSpeedIncreaseFactor);

        maxPaddleLengthLabel.setBounds(0, 268, 200, 30);
        frame.add(maxPaddleLengthLabel);
        maxPaddleLength.setBounds(170, 268, 200, 30);
        frame.add(maxPaddleLength);

        minPaddleLengthLabel.setBounds(0, 296, 200, 30);
        frame.add(minPaddleLengthLabel);
        minPaddleLength.setBounds(170, 296, 200, 30);
        frame.add(minPaddleLength);
        
        confirm.setText("Confirm");
        confirm.setBounds(150, 330, 120, 30);
        frame.add(confirm);
    }
}
