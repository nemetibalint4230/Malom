package com.mycompany.app.gameplay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Tárolja a játékosokat, és megvalósítja a lstába való írást és olvasását
 */
public class PlayerContainer {

    /**
     * Tárolja a játékosokat egy listában
     */
    public static ArrayList<Player> playerlist = new ArrayList<>();

    /**
     * Ez a függvény valósítja meg az adatok beolvasásáat egy file-ból. A függvény az adatokat egy datas.txt nevű file-ból olvassa ki, és menti egy egy ArrayListbe, amelyet ez az osztály tartalmaz
     * @throws IOException hibát dob, ha nem található a file
     */
    public static void readFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("datas.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0].trim();
            int wins = Integer.parseInt(parts[1].trim());
            Player newPlayer = new Player(name, wins, null);
            playerlist.add(newPlayer);
        }
        reader.close();
    }

    /**
     * Ez a függvény valósítja meg az adatok kiírásást egy file-ba. A kiírás egy datas.txt nevű file-ba történik. Az adatokat az osztály playerlist nevű listájából írja ki a metódus
     * @throws IOException hibát dob, ha nem található a file
     */
    public static void writeToFile() throws IOException {
        FileWriter writer = new FileWriter("datas.txt");
        for(Player tempPlayer : playerlist){
            writer.write(tempPlayer.getName());
            writer.write(",");
            writer.write(Integer.toString(tempPlayer.getWins()));
            writer.write("\n");
        }
        writer.close();
    }
}
