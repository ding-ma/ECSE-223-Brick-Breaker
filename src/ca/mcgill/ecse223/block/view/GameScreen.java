package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen {
    public void GameScreen(){
        JFrame FGameScreem = new JFrame();
        JButton BDelete = new JButton();
        JButton BSettings = new JButton();
        JButton BAdd = new JButton();
        JButton BLogout = new JButton();
        JLabel label = new JLabel();
        JButton BSave = new JButton();

        String names[]={"game name","number of level"};
        String data[][]={{"game 1","32"},
                {"game 2","12"}
        };
        JTable GameTable = new JTable(data,names);
        JScrollPane sp = new JScrollPane(GameTable);

        //TODO add column header name
        //TODO show all games
        //TODO add action listeners to columns
        //TODO change column content to appropriate

        label.setText("Game screen");
        label.setBounds(0,0,200,200);
        FGameScreem.add(label);

        BDelete.setText("Delete Game");
        BDelete.setBounds(20,20,100,50);
        BDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteGame deleteGame = new DeleteGame();
                deleteGame.DeleteGame("game1");
            }
        });
        FGameScreem.add(BDelete);

        BSettings.setText("Settings");
        BSettings.setBounds(200,20,100,50);
        BSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSettings GS = new GameSettings();
                GS.GameSettings();
            }
        });
        FGameScreem.add(BSettings);

        BAdd.setText("Update a game");
        BAdd.setBounds(400,20,100,50);
        BAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateGame updateGame = new UpdateGame();
                updateGame.UpdateGame();
            }
        });
        FGameScreem.add(BAdd);

        BLogout.setText("Logout");
        BLogout.setBounds(500,300,100,50);
        BLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO log out @Mairead

            }
        });
        FGameScreem.add(BLogout);


        GameTable.setBounds(0,200,800,600);

        FGameScreem.add(sp);
        FGameScreem.add(GameTable);
        BSave.setText("Save Game");
        BSave.setBounds(550,20,100,50);
        BSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO save game @Mairead
            }
        });
        FGameScreem.add(BSave);

        FGameScreem.setSize(800,600);
        FGameScreem.setLayout(null);
        FGameScreem.setVisible(true);
    }
}
