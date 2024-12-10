package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.app.gamecontroll.*;
import com.mycompany.app.gameplay.*;
import com.mycompany.app.graphics.*;

public class AppTest {

    @BeforeEach
    public void setup() {
        PlayerContainer.playerlist.clear();
        Controller.buttonFieldMap.clear();
        Controller.a = null;
        Controller.b = null;
        Controller.akt = null;
    }

    @Test
    public void playerWin() {
        Player player1 = new Player("Pista", 2, null);
        Player player2 = new Player("Sanyi", 4, null);
        player1.playerWon();
        player2.playerWon();
        player2.playerWon();
        assertEquals(3, player1.getWins());
        assertEquals(6, player2.getWins());
    }

    @Test
    public void playerSetName(){
        Player player1 = new Player("Pista", 0, null);
        player1.setName("István");
        assertEquals(player1.getName(), "István");
    }

    @Test
    public void readFromFile() throws IOException{
        PlayerContainer.readFromFile();
        assertEquals(5, PlayerContainer.playerlist.size());
    }

    @Test
    public void setPlayersForPlay(){
        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);
        PlayerContainer.playerlist.add(player1);
        PlayerContainer.playerlist.add(player2);

        Controller.setPlayers("Sanyi", "Juli");

        assertEquals(Controller.a.getName(), "Sanyi");
        assertEquals(Controller.b.getName(), "Juli");
        assertEquals(Controller.akt.getName(), "Sanyi");
    }

    @Test
    public void canChangeFields(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,player2);
        Field field3 = new Field(6,player1);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);

        Controller.setPlayers("Sanyi", "Juli");

        assertTrue(Controller.canChange(button1, button2));
        assertFalse(Controller.canChange(button2, button3));
        assertFalse(Controller.canChange(button1, button3));
    }

    @Test
    public void changePieces(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,null);
        Field field3 = new Field(6,player2);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);
        
        Controller.setPlayers("Sanyi", "Juli");

        assertEquals(field1.getPlayer().getName(),player1.getName());

        Controller.changePiecePosition(button1, button2);

        assertEquals(field2.getPlayer().getName(),player1.getName());

        Controller.changePiecePosition(button2, button1);

        assertEquals(field1.getPlayer().getName(),player1.getName());        

    }

    @Test
    public void changePLayertest(){
        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Feri", 0, null);
        Player player3 = new Player("Jani", 0, null);
        Player aktual;

        PlayerContainer.playerlist.add(player1);
        PlayerContainer.playerlist.add(player2);
        PlayerContainer.playerlist.add(player3);

        Controller.setPlayers("Sanyi", "Feri");

        assertEquals(Controller.akt.getName(), "Sanyi");

        aktual = Controller.akt;

        Controller.changePlayer(player1, player2);

        assertEquals(Controller.akt.getName(), "Feri");


    }

    @Test
    public void getFieldTest(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,null);
        Field field3 = new Field(6,player2);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);

        assertEquals(Controller.getField(button3).getPlayer().getName(), "Juli");
        assertEquals(Controller.getField(button1).getPlayer().getName(), "Sanyi");
    }

    @Test
    public void removePieceTest(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,null);
        Field field3 = new Field(6,player2);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);

        Controller.removePiece(button3);
        assertNull(Controller.getField(button3).getPlayer());
    }

    @Test
    public void isMalomTest(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);
        Player player2 = new Player("Juli", 2, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,player1);
        Field field3 = new Field(3,player1);
        Field field4 = new Field(12,player1);
        Field field5 = new Field(22,player1);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);

        assertTrue(Controller.isMalom());

        Controller.removePiece(button2);

        assertFalse(Controller.isMalom());

        Controller.buttonFieldMap.put(button4, field4);
        Controller.buttonFieldMap.put(button5, field5);

        assertTrue(Controller.isMalom());
    }

    @Test
    public void fieldIsInMalom(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();


        Player player1 = new Player("Sanyi", 3, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,player1);
        Field field3 = new Field(3,player1);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);

        assertTrue(Controller.isMalom());

        assertTrue(Controller.isInMalom(field2));

        Controller.removePiece(button2);

        assertFalse(Controller.isMalom());

        assertFalse(Controller.isInMalom(field2));
    }

    @Test
    public void hasFreeNeigthbor(){
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();

        Player player1 = new Player("Sanyi", 3, null);

        Field field1 = new Field(1,player1);
        Field field2 = new Field(2,player1);
        Field field3 = new Field(3,player1);
        Field field4 = new Field(8,player1);

        Controller.buttonFieldMap.put(button1, field1);
        Controller.buttonFieldMap.put(button2, field2);
        Controller.buttonFieldMap.put(button3, field3);
        Controller.buttonFieldMap.put(button4, field4);

        assertFalse(Controller.hasFreeNeigthbort(field1));

        field4.setPlayer(null);

        assertTrue(Controller.hasFreeNeigthbort(field1));
        
    }
}
