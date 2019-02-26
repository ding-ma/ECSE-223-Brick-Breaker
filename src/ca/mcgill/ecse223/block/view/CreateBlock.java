package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateBlock extends JFrame {
    public CreateBlock(){

    }
    private JTextField RedValue = new JTextField();
    private JTextField GreenValue = new JTextField();
    private JTextField BlueValue = new JTextField();
    private JTextField PointValue = new JTextField();
    private JButton CreateButton = new JButton();
    private JFrame frame = new JFrame();

    public void BlockCreation (){


        CreateButton.setBounds(250,600,200,50);
        CreateButton.setText("Create Block");
        //  CreateButton.setFont(main.font);

        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SRed = RedValue.getText();
                int Red = Integer.parseInt(SRed);

                String SBlue = BlueValue.getText();
                int Blue = Integer.parseInt(SBlue);

                String SGreen = GreenValue.getText();
                int Green = Integer.parseInt(SGreen);

                String SPoints = PointValue.getText();
                int Points = Integer.parseInt(SPoints);

                System.out.println("Blue = " + Blue);
                System.out.println("Red = " + Red);
                System.out.println("Green = " + Green);
                System.out.println("Points = " + Points);
            }
        });
        frame.add(CreateButton);

        RedValue.setBounds(250,100,200,50);
        RedValue.setText("Enter Red Value");
        //    RedValue.setFont(ui.font);
        RedValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RedValue.setText("");
            }
        });
        frame.add(RedValue);

        GreenValue.setBounds(250,200,200,50);
        GreenValue.setText("Enter Green Value");
        //  GreenValue.setFont(ui.font);
        GreenValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GreenValue.setText("");
            }
        });
        frame.add(GreenValue);

        BlueValue.setBounds(250,300,200,50);
        BlueValue.setText("Enter Blue Value");
        //     BlueValue.setFont(ui.font);
        BlueValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BlueValue.setText("");
            }
        });
        frame.add(BlueValue);

        PointValue.setBounds(250,400,200,50);
        PointValue.setText("Enter Points Value");
        //    PointValue.setFont(ui.font);
        PointValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PointValue.setText("");
            }
        });
        frame.add(PointValue);

        frame.setSize(1000,800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
