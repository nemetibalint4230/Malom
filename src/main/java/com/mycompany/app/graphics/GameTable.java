package com.mycompany.app.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mycompany.app.gameplay.*;
import com.mycompany.app.gamecontroll.*;

/**
 * Ez az osztály jeleníti meg magát a malomtáblát
 */
public class GameTable {
    static JFrame frame;

    /**
     * Ez a függvény valósítja meg a játéktábla megjelenének grafikus mivoltját. Egy panelben létrehoz egy 7x7-es táblázatot úgymond, és ezeket feltölti a megfelelő gombokkal. Ahol a táblán kör van, ott azoknak a gomboknak beállít egy kört iconként, és csak ezek a gombok lesznek aktívak, A többihez beállítja a megfelelő vonalat(vízszintes vagy függőleges).
     *
     */
    public void showGameTable() {
  
        frame = new JFrame("Malom Játék Tábla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        ImageIcon circleImg = new ImageIcon("circle.png");
        Image circle = circleImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon circleIcon = new ImageIcon(circle);

        ImageIcon verticalLineImg = new ImageIcon("vertical_line.png");
        Image verticalLine = verticalLineImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon verticalLineIcon = new ImageIcon(verticalLine);

        ImageIcon horizontalLineImg = new ImageIcon("horizontal_line.png");
        Image horizontalLine = horizontalLineImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon horizontalLineIcon = new ImageIcon(horizontalLine);


        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(7, 7));

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                JButton cell;
                
                if (isMillPosition(row, col)) {
                    cell = new JButton(circleIcon); // Átméretezett kör ikonnal ellátott gomb
                    cell.setBorderPainted(false);
                    Controller.buttonToField(cell, row, col);

                    cell.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Controller.handleButtonClick(cell);
                        }
                    });

                }else if (isVertical(row, col)) {
                    cell = new JButton(verticalLineIcon); // Vízszintes vonal ikonnal ellátott gomb
                    cell.setBackground(Color.WHITE); // Fehér háttér
                    cell.setBorderPainted(false); // Ne legyen keret
                }else if(row != 3 && col != 3){
                    cell = new JButton(horizontalLineIcon); // Függőleges vonal ikonnal ellátott gomb
                    cell.setBackground(Color.WHITE); // Fehér háttér
                    cell.setBorderPainted(false); // Ne legyen keret
                }else{
                    cell = new JButton(); //Középső gomb, itt nincs semmi
                    cell.setBackground(Color.WHITE); // Fehér háttér
                    cell.setBorderPainted(false); // Ne legyen keret
                }

                boardPanel.add(cell);
            }
        }

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    /**
     * Ez a függvény megvizsgálja, hogy a megadott sor és oszlopparaméterő cella része-e a malomtáblán a köröknek, azaz aktívak lesznek-e
     * @param row a megadott cella sora
     * @param col a megadott cella oszlopa
     * @return visszaadaja, hogy része-e vagy sem
     */
    private static boolean isMillPosition(int row, int col) {
        // Malom játék táblájának mezőpontjai
        int[][] millPositions = {
            {0, 0}, {0, 3}, {0, 6},
            {1, 1}, {1, 3}, {1, 5},
            {2, 2}, {2, 3}, {2, 4},
            {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5}, {3, 6},
            {4, 2}, {4, 3}, {4, 4},
            {5, 1}, {5, 3}, {5, 5},
            {6, 0}, {6, 3}, {6, 6}
        };
        
        // Ellenőrzés, hogy az aktuális pozíció benne van-e a mezőpontokban
        for (int[] pos : millPositions) {
            if (pos[0] == row && pos[1] == col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ez a függvény megvizsgálja, hogy a megadott sor és oszlopparaméterő cella része-e a malomtáblán a függőleges vonalaknak
     * @param row a megadott cella sora
     * @param col a megadott cella oszlopa
     * @return visszaadaja, hogy része-e vagy sem
     */
    private static boolean isVertical(int row, int col){
        int[][] verticalLinePositions = {
            {1, 0}, {2, 0}, {4, 0}, {5, 0}, {2, 1}, {4, 1}, {1, 6}, {2, 6}, {4, 6}, {5, 6}, {2, 5}, {4, 5}
        };

        for (int[] pos : verticalLinePositions) {
            if (pos[0] == row && pos[1] == col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ez a függvény jeleníti meg egy győzelem esetén a győzelmi ablakot. Kiírja, hogy ki nyert, és tartalmaz egy bezárás gombot, amely visszadob a menübe
     * @param winner a győztes játékos
     */
    public static void showVictoryFrame(Player winner){
        JFrame victoryFrame = new JFrame("Győzelem!");
        victoryFrame.setSize(300, 150);
        victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        victoryFrame.setLayout(new BorderLayout());
        victoryFrame.setLocationRelativeTo(null);

        JLabel messageLabel = new JLabel(winner.getName() + " nyert!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton closeButton = new JButton("Bezár!");
        closeButton.addActionListener(e -> {
            victoryFrame.dispose();
            frame.dispose();
            Menu.showMenu();
        });

        victoryFrame.add(messageLabel, BorderLayout.CENTER);
        victoryFrame.add(closeButton, BorderLayout.SOUTH);

        victoryFrame.setVisible(true);
    }
}
