package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetGameSettings {


	private String error = null;
	private JLabel errorMessage;

	private JLabel title;

	private JLabel nrLevels;
	private JLabel nrBlocksPerLevel;
	private JLabel minBallSpeedX;
	private JLabel minBallSpeedY;
	private JLabel ballSpeedIncreaseFactor;
	private JLabel maxPaddleLength;
	private JLabel minPaddleLength;

	private JTextField nrLevelsField;
	private JTextField nrBlocksPerLevelField;
	private JTextField minBallSpeedXField;
	private JTextField minBallSpeedYField;
	private JTextField ballSpeedIncreaseFactorField;
	private JTextField maxPaddleLengthField;
	private JTextField minPaddleLengthField;

	private JButton updateGame;

	private JFrame frame;

	public void SetGameSettings(){
		frame = new JFrame();
		errorMessage = new JLabel();
		updateGame = new JButton();
		//////////////////////////////////////////////////////////////////
		nrLevels = new JLabel();

		nrBlocksPerLevel = new JLabel();
		minBallSpeedX = new JLabel();
		minBallSpeedY = new JLabel();
		ballSpeedIncreaseFactor = new JLabel();
		maxPaddleLength = new JLabel();
		minPaddleLength = new JLabel();
		//////////////////////////////////////////////////////////////////
		nrLevelsField = new JTextField();

		nrBlocksPerLevelField = new JTextField();
		minBallSpeedXField = new JTextField();
		minBallSpeedYField = new JTextField();
		ballSpeedIncreaseFactorField = new JTextField();
		maxPaddleLengthField = new JTextField();
		minPaddleLengthField = new JTextField();
		//////////////////////////////////////////////////////////////////

		title = new JLabel();

		title.setBounds(10, 0, 300, 50);
		title.setFont (title.getFont ().deriveFont (25.0f));
		title.setForeground(Color.BLACK);
		title.setText("Set Game Details");
		//////////////////////////////////////////////////////////////////
		nrLevels = new JLabel();

		nrLevels.setText("Number of Levels: ");
		nrLevels.setBounds(10, 50, 300, 50);
		nrLevels.setFont (nrLevels.getFont ().deriveFont (15.0f));
		nrLevels.setForeground(Color.BLACK);

		nrBlocksPerLevel = new JLabel();
		nrBlocksPerLevel.setText("Number of Blocks per Level: ");
		nrBlocksPerLevel.setBounds(10, 90, 300, 50);
		nrBlocksPerLevel.setFont (nrLevels.getFont ().deriveFont (15.0f));
		nrBlocksPerLevel.setForeground(Color.BLACK);

		minBallSpeedX = new JLabel();
		minBallSpeedX.setText("Minimum Ball X Velocity: ");
		minBallSpeedX.setBounds(10, 130, 300, 50);
		minBallSpeedX.setFont (nrLevels.getFont ().deriveFont (15.0f));
		minBallSpeedX.setForeground(Color.BLACK);

		minBallSpeedY = new JLabel();
		minBallSpeedY.setText("Minimum Ball Y Velocity: ");
		minBallSpeedY.setBounds(10, 170, 300, 50);
		minBallSpeedY.setFont (nrLevels.getFont ().deriveFont (15.0f));
		minBallSpeedY.setForeground(Color.BLACK);

		ballSpeedIncreaseFactor = new JLabel();
		ballSpeedIncreaseFactor.setText("Ball Speed Increase Factor: ");
		ballSpeedIncreaseFactor.setBounds(10, 210, 300, 50);
		ballSpeedIncreaseFactor.setFont (nrLevels.getFont ().deriveFont (15.0f));
		ballSpeedIncreaseFactor.setForeground(Color.BLACK);

		maxPaddleLength = new JLabel();
		maxPaddleLength.setText("Maximum Paddle Length: ");
		maxPaddleLength.setBounds(10, 250, 300, 50);
		maxPaddleLength.setFont (nrLevels.getFont ().deriveFont (15.0f));
		maxPaddleLength.setForeground(Color.BLACK);

		minPaddleLength = new JLabel();
		minPaddleLength.setText("Minimum Paddle Length: ");
		minPaddleLength.setBounds(10, 290, 300, 50);
		minPaddleLength.setFont (nrLevels.getFont ().deriveFont (15.0f));
		minPaddleLength.setForeground(Color.BLACK);

		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 330, 400, 100);
		//////////////////////////////////////////////////////////////////
		nrLevelsField = new JTextField();
		nrLevelsField.setBounds(215, 60, 100, 30);
		nrLevelsField.setText("1");

		nrBlocksPerLevelField = new JTextField();
		nrBlocksPerLevelField.setBounds(215, 100, 100, 30);
		nrBlocksPerLevelField.setText("1");

		minBallSpeedXField = new JTextField();
		minBallSpeedXField.setBounds(215, 140, 100, 30);
		minBallSpeedXField.setText("1");

		minBallSpeedYField = new JTextField();
		minBallSpeedYField.setBounds(215, 180, 100, 30);
		minBallSpeedYField.setText("1");

		ballSpeedIncreaseFactorField = new JTextField();
		ballSpeedIncreaseFactorField.setBounds(215, 220, 100, 30);
		ballSpeedIncreaseFactorField.setText("1");

		maxPaddleLengthField = new JTextField();
		maxPaddleLengthField.setBounds(215, 260, 100, 30);
		maxPaddleLengthField.setText("10");

		minPaddleLengthField = new JTextField();
		minPaddleLengthField.setBounds(215, 300, 100, 30);
		minPaddleLengthField.setText("10");
		//////////////////////////////////////////////////////////////////
		updateGame.setText("Set Settings");
		updateGame.setBounds(215, 380, 100, 50);
		updateGame.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			updateGameButtonActionPerformed(evt);
			
		}				
    });
    
		//////////////////////////////////////////////////////////////////
		frame.setSize(400, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);	    
		errorMessage.setText(error);
		frame.add(nrBlocksPerLevelField);
		frame.add(nrLevels);
		frame.add(nrLevelsField);
		frame.add(title);
		frame.add(nrBlocksPerLevel);
		frame.add(minBallSpeedX);
		frame.add(minBallSpeedXField);
		frame.add(minBallSpeedY);
		frame.add(minBallSpeedYField);
		frame.add(ballSpeedIncreaseFactor);
		frame.add(ballSpeedIncreaseFactorField);
		frame.add(minPaddleLength);
		frame.add(maxPaddleLength);
		frame.add(maxPaddleLengthField);
		frame.add(minPaddleLengthField);
		frame.add(updateGame);
		frame.add(errorMessage);

	}
	 private void refreshData() {
			// error
			errorMessage.setText(error);
	 }
	 private void updateGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
			error = "";
			
			
			String nrLevels = nrLevelsField.getText();
	       
	        
	        String nrBlocksPerLevel = nrBlocksPerLevelField.getText();
	        
	        
	        String minBallSpeedX = minBallSpeedXField.getText();
	        
	        
	        String minBallSpeedY = minBallSpeedYField.getText();
	        
	        
	        String ballSpeedIncreaseFactor = ballSpeedIncreaseFactorField.getText();
	        
	        
	        String minPaddleLength = minPaddleLengthField.getText();
	       
	        String maxPaddleLength = maxPaddleLengthField.getText();
	        
	        
	        if (nrLevels == null || nrBlocksPerLevel == null || minBallSpeedX == null || minBallSpeedY == null) {
	        	error = "One or more of the boxes is empty";
	        }
	        
	        if (error.length() == 0 || error == "") {
	        	int anrLevelsd = Integer.parseInt(nrLevels);
	        	int anrBlocksPerLevel = Integer.parseInt(nrBlocksPerLevel);
	        	int aminBallSpeedX = Integer.parseInt(minBallSpeedX);
	        	int aminBallSpeedY = Integer.parseInt(minBallSpeedY);
	        	int aminPaddleLength = Integer.parseInt(minPaddleLength);
	        	int amaxPaddleLength = Integer.parseInt(maxPaddleLength);
	        	double aballSpeedIncreaseFactor = Double.parseDouble(ballSpeedIncreaseFactor);
				try {
				Block223Controller.setGameDetails(anrLevelsd,anrBlocksPerLevel,aminBallSpeedX,aminBallSpeedY,aballSpeedIncreaseFactor,amaxPaddleLength,aminPaddleLength );
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				
	        }
	        refreshData();
	        
	        GameScreen.refreshData();
	        
	        if (error.length() == 0 || error == "") {
	        	frame.dispose();
	        }
	        
	        
	 }
	 
}
