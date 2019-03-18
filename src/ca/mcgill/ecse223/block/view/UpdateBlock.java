package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class UpdateBlock {

	private String error = null;
	private JLabel errorMessage;

	private JTextField redField;
	private JTextField greenField;
	private JTextField blueField;
	private JTextField pointsField;

	private JLabel updateBlockLabel;

	private JLabel redValue;
	private JLabel greenValue;
	private JLabel blueValue;
	private JLabel pointsValue;
	
	private JButton updateButton;
	private JFrame frame;


	public void UpdateBlock() {
		frame = new JFrame();



		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 250, 400, 200);
		
		updateBlockLabel = new JLabel();
		updateBlockLabel.setText("Update Block");
		updateBlockLabel.setBounds(180, 0, 200, 50);
 		
		redField = new JTextField();
		redField.setBounds(50, 80, 150, 30);
		
		greenField = new JTextField();
		greenField.setBounds(250, 80, 150, 30);
		
		blueField = new JTextField();
		blueField.setBounds(50,160,150,30);
		
		pointsField = new JTextField();
		pointsField.setBounds(250, 160,150,30);

		redValue = new JLabel();
		redValue.setText("Set the New Red Value: ");
		redValue.setBounds(50, 40,200,50);
		
		greenValue = new JLabel();
		greenValue.setText("Set the New Green Value: ");
		greenValue.setBounds(250, 40,200,50);
		
		blueValue = new JLabel();
		blueValue.setText("Set the New Blue Value: ");
		blueValue.setBounds(50,120, 200, 50);
		
		pointsValue = new JLabel();
		pointsValue.setText("Set the New Points Value: ");
		pointsValue.setBounds(250, 120, 200, 50);
		
		updateButton = new JButton();
		updateButton.setBounds(120,200,200,50);
		updateButton.setText("Update");
		
		updateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBlockButtonActionPerformed(evt);
			}
		});
		


		frame.setSize(450, 450);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);

		frame.add(errorMessage);
		
		frame.add(redField);
		frame.add(greenField);
		frame.add(blueField);
		frame.add(pointsField);
		
		frame.add(updateBlockLabel);
		
		frame.add(redValue);
		frame.add(greenValue);
		frame.add(blueValue);
		frame.add(pointsValue);
		frame.add(updateButton);
	}
	private void refreshData() {
		// error
		errorMessage.setText(error);

	}
	private void updateBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int id = BlockScreen.getid();
		String SRed = redField.getText();
        int red = Integer.parseInt(SRed);

        if (SRed == null || red < 0 || red > 255) {
        	error = "Red must be between 0 and 255. ";
        	refreshData();
        }

        String SBlue = blueField.getText();
        int blue = Integer.parseInt(SBlue);
        
        if(SBlue == null || blue <0 || blue > 255) {
        	error += "Blue must be between 0 and 255. ";
        	refreshData();
        }

        String SGreen = greenField.getText();
        int green = Integer.parseInt(SGreen);
        
        if(SGreen == null || green <0 || green > 255) {
        	error += "Green must be between 0 and 255. ";
        	refreshData();
        }

        String SPoints = pointsField.getText();
        int points = Integer.parseInt(SPoints);
        
        if(SGreen == null || green <0 || green > 255) {
        	error += "Green must be between 0 and 255. ";
        	refreshData();
        }
        if (error == null) {

            try {
                Block223Controller.updateBlock(id, red, green, blue, points);

            }
            catch (InvalidInputException a){
              error =  a.getMessage();
              refreshData();
            } 
        }
        if (error == null) {
		 frame.dispose();	
		 BlockScreen.refreshData();
		}
	}
}