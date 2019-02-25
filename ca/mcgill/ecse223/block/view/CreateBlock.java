package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.*;
import ca.mcgill.ecse223.block.controller.*;

public class CreateBlock extends JFrame {

    private JTextField BlueColor;
    private JTextField GreenColor;
    private JTextField RedColor;
    private JButton createBlockButton;


    public CreateBlock() { //constructor for creating block??
        RedColor.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        GreenColor.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        BlueColor.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });

        createBlockButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionListener evt) {
                createBlockButtonActionPerformed(evt);

            }
        });
    }

}
