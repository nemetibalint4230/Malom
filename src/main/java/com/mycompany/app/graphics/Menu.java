package com.mycompany.app.graphics;

import javax.swing.*;

import com.mycompany.app.gameplay.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;

/**
 * Menu osztály, amely megjeleníti a menüt
 */
public class Menu {
    private static JFrame frame;

    /**
     * Ez a függvény jeleníti meg a menüt. A menű 4 dolgot tartalmaz. Egy szöveget, és 3 gombot. A szöveg kiírja, hogy "Malom játék", ha esetleg valaki nem tuda, hogy mivel játszik. A három gomb a "Játék", amellyel elindítható a játék menete, a "Tabella", amellyel a tabella jeleníthető meg, valamint a "Bezár", amely bezárja a játékot. A menü elkészítéséhez az órán tanultakat használtam, valamint a szépség növelése miatt egyéb dolgokat is használtam, ezeknek forrása megtalálható mellettük
     */
    public static void showMenu(){

        frame = new JFrame("Malom Játék");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Középre helyezi az ablakot, Stack Overflow-ról szedtem az ötletet
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        titlePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel titleLabel = new JLabel("Malom Játék", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Cím középre igazítása
        titlePanel.add(titleLabel);

        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton playButton = new JButton("Játék");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setPreferredSize(new Dimension(150, 40));
        playButton.setMaximumSize(new Dimension(150, 40));
        buttonPanel.add(playButton);

        // Térköz a gombok között, itt is az ötlet a Stack Overfolwról jött
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPlayerMenu();
            }
        });

        JButton leaderboardButton = new JButton("Tabella");
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.setPreferredSize(new Dimension(150, 40));
        leaderboardButton.setMaximumSize(new Dimension(150, 40));
        buttonPanel.add(leaderboardButton);

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreTable.showScoreTable();
            }
        });

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton closeMenuButton = new JButton("Bezár");
        closeMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeMenuButton.setPreferredSize(new Dimension(150, 40));
        closeMenuButton.setMaximumSize(new Dimension(150, 40));
        buttonPanel.add(closeMenuButton);

        closeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlayerContainer.writeToFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        
        frame.setVisible(true);
    }

    /**
     * Ez a függvény arra hivatott, hogy ha valaki a főmenüben a "Játék" opciót választja, akkor itt megadhatja a két játszani kívánt játékos a neveiket.
     */
    public static void showPlayerMenu(){
        JFrame playerFrame = new JFrame("Játékosok nevei");
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setSize(400, 300);
        playerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel player1Label = new JLabel("Játékos 1 neve:");
        JTextField player1Field = new JTextField();
        JLabel player2Label = new JLabel("Játékos 2 neve:");
        JTextField player2Field = new JTextField();

        JButton startGameButton = new JButton("Játék indítása");

        setupPlayButtonListener(startGameButton);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1Name = player1Field.getText();
                String player2Name = player2Field.getText();
                
                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    playerFrame.dispose();
                }

                com.mycompany.app.gamecontroll.Controller.setPlayers(player1Name, player2Name);
            }
        });

        panel.add(player1Label);
        panel.add(player1Field);
        panel.add(player2Label);
        panel.add(player2Field);
        panel.add(startGameButton, BorderLayout.CENTER);
        
        playerFrame.add(panel);
        playerFrame.setVisible(true);
    }

    /**
     * Ez a játékosmenüben lévő "Játék" gomb ActionListener-je. Ha valaki ezt a gombot választja, akkor bezáródik a játékosmenü, és elindul a játék, megjelenik a játék táblája. 
     * @param playButton a "Játék" gomb maga
     */
    private static void setupPlayButtonListener(JButton playButton) {
        GameTable gameTable = new GameTable();

        playButton.addActionListener(e -> {
            frame.dispose();
            gameTable.showGameTable();
        });
    }

}
