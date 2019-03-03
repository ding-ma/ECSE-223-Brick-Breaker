package ca.mcgill.ecse223.block.view;
import ca.mcgill.ecse223.block.controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

<<<<<<< HEAD:src/ca/mcgill/ecse223/block/view/CreateBlock.java
public class CreateBlock extends JFrame {
    public CreateBlock(){
//adasda
    }
=======
public class AddBlock {
>>>>>>> 35786d9d672d3a6943c79164da6900bb0b3d1a4c:src/ca/mcgill/ecse223/block/view/AddBlock.java
    private JTextField RedValue = new JTextField();
    private JTextField GreenValue = new JTextField();
    private JTextField BlueValue = new JTextField();
    private JTextField PointValue = new JTextField();
    private JButton CreateButton = new JButton();
    private JFrame frame = new JFrame();

    public void AddBlock() {

        CreateButton.setBounds(150, 400, 200, 50);
        CreateButton.setText("Create Block");
        //  CreateButton.setFont(main.font);

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

                System.out.println("points = " + points);
                System.out.println("green = " + green);
                System.out.println("blue = " + blue);
                System.out.println("red = " + red);

                try {
                    Block223Controller.addBlock(red, green, blue, points);
                    BlockScreen blockScreen = new BlockScreen();
                  //  blockScreen.RefreshBlockScreen();

                }
                catch (InvalidInputException a){
                    a.printStackTrace();
                }
            }
        });
        frame.add(CreateButton);

        RedValue.setBounds(150, 50, 200, 50);
        RedValue.setText("Enter Red Value");
        //    RedValue.setFont(ui.font);
        RedValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RedValue.setText("");
            }
        });
        frame.add(RedValue);

        GreenValue.setBounds(150, 125, 200, 50);
        GreenValue.setText("Enter Green Value");
        //  GreenValue.setFont(ui.font);
        GreenValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GreenValue.setText("");
            }
        });
        frame.add(GreenValue);

        BlueValue.setBounds(150, 200, 200, 50);
        BlueValue.setText("Enter Blue Value");
        //     BlueValue.setFont(ui.font);
        BlueValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BlueValue.setText("");
            }
        });
        frame.add(BlueValue);

        PointValue.setBounds(150, 275, 200, 50);
        PointValue.setText("Enter Points Value");
        //    PointValue.setFont(ui.font);
        PointValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PointValue.setText("");
            }
        });
        frame.add(PointValue);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}