package com.mycompany.app.graphics;

import com.mycompany.app.gameplay.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreTable {

    /**
     * Ez a függvény jeleníti meg a tabellát. Kilistázza az adatbázisban szereplő játékosok neveit, és győzelmeinek számát. Ezt egy táblázatban jeleníti meg
     */
    public static void showScoreTable() {
        JFrame frame = new JFrame("Tabella");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        ArrayList<Player> players = PlayerContainer.playerlist;

        String[] columnNames = {"Név", "Győzelmek"};
        String[][] data = new String[players.size()][2];

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            data[i][0] = player.getName();
            data[i][1] = String.valueOf(player.getWins());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JButton closeButton = new JButton("Bezár");
        closeButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        table.setDefaultEditor(Object.class, null); //Nem szerkezthető a tábla (a metódust a Stack Overflow oldalán találtam)

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
