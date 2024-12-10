package com.mycompany.app.gameplay;

public class Player {
    private String name;
    private int wins;
    private String color;
    private int pieces;
    private int maxpieces;
    
    /**
     * A Player osztály konstruktora, beállítja az id-t és a player-t
     *
     * @param n Az adott játékos neve
     * @param w az adott játékos győzelmeinek száma
     * @param c az adott játékos színe
     */
    public Player(String n, int w, String c){
        name = n;
        wins = w;
        color = c;
        maxpieces = 9;
    }

    /**
     * Visszaadja a játékos nevét
     *
     * @return Aktuális játékos neve
     */
    public String getName(){
        return name;
    }

    /**
     * Beállítja a játékos nevét
     * @param n a beállítani kívánt név
     */
    public void setName(String n){
        name = n;
    }
    
    /**
     * Visszaadja a játékos győzelmeinek számát
     *
     * @return Aktuális játékos győzelmeinek száma
     */
    public int getWins(){
        return wins;
    }

    /**
     * Beállítja a játékos győzelmeinek számát
     * @param w a beállítani kívánt győzelmek száma
     */
    public void setWins(int w){
        wins = w;
    }

    /**
     * Visszaadja a játékos színét
     *
     * @return Aktuális játékos színe
     */
    public String getColor(){
        return color;
    }

    /**
     * Beállítja a játékos színét
     * @param c a beállítani kívánt szín
     */
    public void setColor(String c){
        color = c;
    }

    /**
     * Visszaadja a játékos bábúinak számát
     *
     * @return Aktuális játékos bábúinak száma
     */
    public int getPieces(){
        return pieces;
    }

    /**
     * Hozzáad egyet a játékos bábúihoz
     */
    public void addOnePiece(){
        pieces += 1;
    }

    /**
     * Hozzáad egyet a játékos győzelmeinek számához
     */
    public void playerWon(){
        wins = wins + 1;
    }

    /**
     * Visszaadja a játékos maximális bábúinak számát
     *
     * @return Aktuális játékos maximálisan letehető bábúinak száma
     */
    public int getMaxPieces(){
        return maxpieces;
    }

    /**
     * Eggyel csökkenti a játékos maximum bábúinak számát eggyel
     *
     */
    public void minusMaxPieces(){
        maxpieces -= 1;
    }

    /**
     * Eggyel csökkenti a játékos bábúinak számát eggyel
     *
     */
    public void minusPieces(){
        pieces -= 1;
    } 
}
