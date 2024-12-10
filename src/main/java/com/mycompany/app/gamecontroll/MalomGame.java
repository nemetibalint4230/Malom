package com.mycompany.app.gamecontroll;
import com.mycompany.app.gameplay.*;

import java.io.IOException;

import com.mycompany.app.graphics.*;

/**
 * Innen indítható maga a játék
 */
public class MalomGame {

    /**
     * Ez az alkalmazás main függvénye, hogy lehessen futtatni az egészet
     * @param args alapparaméter
     */
    public static void main(String[] args) {
        try {
            PlayerContainer.readFromFile();
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasása közben: " + e.getMessage());
        }
        Menu menu = new Menu();
        menu.showMenu();
    }
}