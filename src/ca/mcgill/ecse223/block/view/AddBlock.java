package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBlock {
	


	private String error = null;
	private JLabel errorMessage;

	private JTextField RedValue = new JTextField();
	private JTextField GreenValue = new JTextField();
	private JTextField BlueValue = new JTextField();
	private JTextField PointValue = new JTextField();
	private JButton CreateButton = new JButton();
	private JButton previewBlock = new JButton();
	private JFrame frame = new JFrame();
	private JLabel redValue;
	private JLabel greenValue;
	private JLabel blueValue;
	private JLabel pointsValue;
	private JLabel addBlock;
	private JPanel rectangle = new JPanel();


	public void AddBlock() {

		addBlock = new JLabel();
		addBlock.setBounds(10, 0, 300, 50);
		addBlock.setFont (addBlock.getFont ().deriveFont (25.0f));
		addBlock.setForeground(Color.BLACK);
		addBlock.setText("Create Block");
		frame.add(addBlock);

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

		frame.add(redValue);
		frame.add(greenValue);
		frame.add(blueValue);
		frame.add(pointsValue);
		frame.add(previewBlock);



		errorMessage = new JLabel();
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 350, 440, 100);

		CreateButton.setBounds(120,200,200,50);
		CreateButton.setText("Create Block");
		
		previewBlock.setBounds(120,250,200,50);
		previewBlock.setText("Preview Block");

		previewBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previewBlockButtonActionPerformed(evt);
			}
		});

		
		

		previewBlock.setBounds(120, 250, 200, 50);
		previewBlock.setText("Preview Block");

		previewBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previewBlockButtonActionPerformed(evt);
			}
		});




		CreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String SRed = RedValue.getText();
				int red = Integer.parseInt(SRed);

				String SBlue = BlueValue.getText();
				int blue = Integer.parseInt(SBlue);

				String SGreen = GreenValue.getText();
				int green = Integer.parseInt(SGreen);

				String SPoints = PointValue.getText();
				int points = Integer.parseInt(SPoints);

				try {
					Block223Controller.addBlock(red, green, blue, points);
					BlockScreen blockScreen = new BlockScreen();
					BlockScreen.refreshData();
					frame.dispose();
				}
				catch (InvalidInputException a){
					error =  a.getMessage();
				}
				refreshData();
			}


		});
		frame.add(CreateButton);

		RedValue.setBounds(50,80,150,30);
		//    RedValue.setFont(ui.font);
		RedValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RedValue.setText("");
			}
		});
		frame.add(RedValue);

		GreenValue.setBounds(250,80,150,30);
		//  GreenValue.setFont(ui.font);
		GreenValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GreenValue.setText("");
			}
		});
		frame.add(GreenValue);

		BlueValue.setBounds(50,160,150,30);
		//     BlueValue.setFont(ui.font);
		BlueValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlueValue.setText("");
			}
		});
		frame.add(BlueValue);

		PointValue.setBounds(250,160,150,30);
		//    PointValue.setFont(ui.font);
		PointValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PointValue.setText("");
			}
		});
		frame.add(PointValue);
		frame.add(errorMessage);

		frame.setSize(450, 450);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		frame.setVisible(true);
		CreateButton.setBounds(120,200,200,50);
		CreateButton.setText("Create Block");

		CreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (RedValue.getText().equals("") || BlueValue.getText().equals("") || GreenValue.getText().equals("") || PointValue.getText().equals("")) {
					error = "One or more of the fields are empty.";
					refreshData();
				} else {
					String SRed = RedValue.getText();
					int red = Integer.parseInt(SRed);

					String SBlue = BlueValue.getText();
					int blue = Integer.parseInt(SBlue);

					String SGreen = GreenValue.getText();
					int green = Integer.parseInt(SGreen);

					String SPoints = PointValue.getText();
					int points = Integer.parseInt(SPoints);

					try {
						Block223Controller.addBlock(red, green, blue, points);
						BlockScreen blockScreen = new BlockScreen();
						BlockScreen.refreshData();
						frame.dispose();
					} catch (InvalidInputException a) {
						error = a.getMessage();
					}
					refreshData();
				}
			}

		});
		frame.add(CreateButton);

		RedValue.setBounds(50,80,150,30);
		//    RedValue.setFont(ui.font);
		RedValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RedValue.setText("");
			}
		});
		frame.add(RedValue);

		GreenValue.setBounds(250,80,150,30);
		//  GreenValue.setFont(ui.font);
		GreenValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GreenValue.setText("");
			}
		});
		frame.add(GreenValue);

		BlueValue.setBounds(50,160,150,30);
		//     BlueValue.setFont(ui.font);
		BlueValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlueValue.setText("");
			}
		});
		frame.add(BlueValue);

		PointValue.setBounds(250,160,150,30);
		//    PointValue.setFont(ui.font);
		PointValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PointValue.setText("");
			}
		});
		frame.add(PointValue);
		frame.add(errorMessage);
		frame.add(rectangle);




		frame.setSize(450, 450);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		frame.setVisible(true);


	}

	private void previewBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (RedValue.getText().equals("") || BlueValue.getText().equals("") || GreenValue.getText().equals("")) {
			error = "One or more of the fields are empty.";
			refreshData();
		} else {
			String SRed = RedValue.getText();
			int red = Integer.parseInt(SRed);

			String SBlue = BlueValue.getText();
			int blue = Integer.parseInt(SBlue);

			String SGreen = GreenValue.getText();
			int green = Integer.parseInt(SGreen);

			String SPoints = PointValue.getText();

			rectangle.setBounds(200, 320, 100, 100);
			rectangle.setBackground(new Color(red, green, blue));
			rectangle.setSize(42, 40);
			frame.add(rectangle);
			refreshData();
		}


	}
	private void refreshData() {
		errorMessage.setText(error);
		frame.add(rectangle);
	}

}
