package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import com.sun.javaws.exceptions.ExitException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteGame extends JFrame {
        //JButton
        private JButton delete;

        //JLabel
        private JLabel title;
        private JLabel thisGameLabel;
        
        public DeleteGame(Controller controller, MasterUI ui, String game) {
            super(controller, page);
            this.setLayout(null);

            title = new JLabel("Delete Game", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 50));
            title.setBounds(50, 0, 650, 200);

            thisGameLabel = new JLabel("Are you sure you want to delete the game " 
                                        + game + " ?", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.PLAIN, 50));
            title.setBounds(0, 400, 750, 50);

            delete = new JButton("confirm delete");
            delete.setFont(new Font ("Arial", Font.BOLD, 20));
            delete.setBounds(200, 600, 150, 50);
            submit.addActionListener(this);

            updateView();

            this.setVisible(true);

        }

        public void updateView() {
            //TODO
            revalidate();
            repaint();
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommant().equals("confirm delete")) {
                try {
                    controller.deleteGame(game); //game is the passed value
                    updateView();
                } catch (InvalidInputException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                    exception.printStackTrace();
                }
            }
        }
}
