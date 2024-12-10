package com.mycompany.app.gameplay;

/**
 * Mező osztály, melyhez majd rendelve lesznek gombok, ezek alkotját a táblát
 */
public class Field {
    private int id;
    private Player player;

    /**
     * A Field osztály konstruktora, beállítja az id-t és a player-t
     *
     * @param id Az adott mező azonosítója
     * @param player az adott mezőhöz tartoző játékos
     */
    public Field(int id, Player player){
        this.id = id;
        this.player = player;
    }

    /**
     * Visszaadja a mező azonosítóját
     * @return a mező azonosítója
     */
    public int getId(){
        return id;
    }

    /**
     * Visszaadja a mezőhöz tartozó játékost
     * @return a mező játékosa
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Beállítja a mezőhöz tartozó játékost
     * @param p a beállítani kívánt játékos
     */
    public void setPlayer(Player p){
        player = p;
    }
}
