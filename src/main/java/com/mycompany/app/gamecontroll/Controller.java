package com.mycompany.app.gamecontroll;

import com.mycompany.app.graphics.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import com.mycompany.app.gameplay.*;

/**
 * Mivel MVC architechtúrára törekedtem, ezért ez az osztály fogja úgymond összekötni a grafikus felületet az adatok kezelését és tárolását biztosító osztályokkal
 */
public class Controller {

    /**
     * Eltárolja a megfelelő gomb és mező kombinációkat
     */
    public static Map<JButton, Field> buttonFieldMap = new HashMap<>();

    /**
     * Az egyik játékos
     */
    public static Player a;

    /**
     * Az másik játékos
     */
    public static Player b;

    /**
     * Az éppen aktuális játékos
     */
    public static Player akt;

    /**
     * Az állapotgéphez szükséges, hogy tároljam a kattintások számát
     */
    private static int click = 0;

    /**
     * Az állapotgéphez szükséges, hogy tároljam az első állapot paraméterát
     */
    private static JButton temp;

    private static boolean remove = false;

    /**
     * Tárolnom kell, hogy melyek azok a sorszámok, amelyek szerepelnek malomban
     */
    private static ArrayList<String> malomvolt = new ArrayList<String>();
    
    /**
     * Beállítja az éppen akuálisan játszó játékosoknak, hogy melyik színnel lesznek, valamint, hogy ha a két játékos még nincs benne az adatbázisban, akkor felvesszük őket
     *
     * @param name1 első játékos neve
     * @param name2 második játékos neve
     */
    public static void setPlayers(String name1, String name2){
        for(Player player : PlayerContainer.playerlist){
            if(name1.equals(player.getName())){
                a = player;
                a.setColor("blue");
            }else if(name2.equals(player.getName())){
                b = player;
                b.setColor("red");
            }
        }
        if(a == null){
            Player newPlayer1 = new Player(name1, 0, "blue");
            a = newPlayer1;
        }
        if(b == null){
            Player newPlayer2 = new Player(name2, 0, "red");
            b = newPlayer2;
        }
        akt = a;
    }

    /**
     * Ez a metódus elhelyezi a Mapban a gomb-mező párosokat.
     *
     * @param button a bekapott gomb, amelyhez hozzárendelünk egy Fieldet
     * @param field a bekapott mező, amihez hozzárendeljük a gombot
     */
    public static void mapButtonToField(JButton button, Field field) {
        buttonFieldMap.put(button, field);
    }

    /**
     * Ez a metódus beállítja a minden fieldnek a saját egyedi azonosítóját, és elhelyezi egy Map-ban a mefelelő button-field párosokat. Első ranézésre nem néz ki szépen, azonban több száz sort és több száz ciklust meg tudtam vele spórolni, ezért maradtam ennél a használatnál.
     *
     * @param b a bekapott gomb, amelyhez hozzárendelünk egy Fieldet
     * @param row a sor, ahol a gomb van
     * @param cell az oszlop, ahol a gomb van
     */
    public static void buttonToField(JButton b, int row, int cell){
        if(row==0 && cell==0){
            Field f = new Field(1, null);
            mapButtonToField(b, f);
        }else if(row==0 && cell==3){
            Field f = new Field(2, null);
            mapButtonToField(b, f);
        }else if(row==0 && cell==6){
            Field f = new Field(3, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==6){
            Field f = new Field(4, null);
            mapButtonToField(b, f);
        }else if(row==6 && cell==6){
            Field f = new Field(5, null);
            mapButtonToField(b, f);
        }else if(row==6 && cell==3){
            Field f = new Field(6, null);
            mapButtonToField(b, f);
        }else if(row==6 && cell==0){
            Field f = new Field(7, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==0){
            Field f = new Field(8, null);
            mapButtonToField(b, f);
        }
        else if(row==1 && cell==1){
            Field f = new Field(11, null);
            mapButtonToField(b, f);
        }else if(row==1 && cell==3){
            Field f = new Field(12, null);
            mapButtonToField(b, f);
        }else if(row==1 && cell==5){
            Field f = new Field(13, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==5){
            Field f = new Field(14, null);
            mapButtonToField(b, f);
        }else if(row==5 && cell==5){
            Field f = new Field(15, null);
            mapButtonToField(b, f);
        }else if(row==5 && cell==3){
            Field f = new Field(16, null);
            mapButtonToField(b, f);
        }else if(row==5 && cell==1){
            Field f = new Field(17, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==1){
            Field f = new Field(18, null);
            mapButtonToField(b, f);
        }
        else if(row==2 && cell==2){
            Field f = new Field(21, null);
            mapButtonToField(b, f);
        }else if(row==2 && cell==3){
            Field f = new Field(22, null);
            mapButtonToField(b, f);
        }else if(row==2 && cell==4){
            Field f = new Field(23, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==4){
            Field f = new Field(24, null);
            mapButtonToField(b, f);
        }else if(row==4 && cell==4){
            Field f = new Field(25, null);
            mapButtonToField(b, f);
        }else if(row==4 && cell==3){
            Field f = new Field(26, null);
            mapButtonToField(b, f);
        }else if(row==4 && cell==2){
            Field f = new Field(27, null);
            mapButtonToField(b, f);
        }else if(row==3 && cell==2){
            Field f = new Field(28, null);
            mapButtonToField(b, f);
        }
    }

    /**
     * Beállítja a bekapott gombnak a paraméterül kapott képet. Ezt úgy teszi meg, hogy létrehoz egy ImageIcon, majd azt beállítja a gomb Icon-jának
     *
     * @param button a bekapott gomb, amelynek beállítjuk a megfelelő képet
     * @param img a kép neve, ahogyan el van mentve a háttértárolón
     */
    public void setButtonImg(JButton button, String img){
        ImageIcon temp = new ImageIcon(img);
        button.setIcon(temp);
    }

    /**
     * Megnézi, hogy amikor már lépni kell egy játékosnak, akkor léphet-e egyik mezőről a másikra. Ezt a malomnak megfelelően dönti el. Mivel nem lehet olyan mezőre lépni, aho már áll valaki, ezért csak akkor tér vissza igazzal, ha olyan mezőre akarunk lépni, ahol a mező játékos paramétere null.
     *
     * @param b1 erről a mezőről szerenénk lépni
     * @param b2 erre a mezőre szeretnénk lépni
     * @return lehetséges-e a megadott lépés
     */
    public static boolean canChange(JButton b1, JButton b2){
        Field f1 = null;
        Field f2 = null;
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            JButton button = entry.getKey();
            Field field = entry.getValue();

            if(button == b1){
                f1 = field;
            }else if(button == b2){
                f2 = field;
            }
        }
        if((Math.abs(f1.getId()-f2.getId())==1) || ((Math.abs(f1.getId()-f2.getId())==10) && (f1.getId()%2==0)) || 
        (f1.getId()==1 && f2.getId()==8) ||
        (f1.getId()==8 && f2.getId()==1) ||
        (f1.getId()==11 && f2.getId()==18) ||
        (f1.getId()==18 && f2.getId()==11) ||
        (f1.getId()==21 && f2.getId()==28) ||
        (f1.getId()==28 && f2.getId()==21)){
            return true;
        }
        
        if(akt.getMaxPieces() == 3){
            return true;
        }else{
            return false;
        }
    } 

    /**
     * Ha lehetséges a csere két mező között, akkor a hozzájuk tartozó gombok képét kicseréli, valamint a hozzájuk tartozó játékosokat is. Ezt egy átmeneti Icon bevezetésével teszi lehetővé.
     *
     * @param b1 erről a mezőről szerenénk lépni
     * @param b2 erre a mezőre szeretnénk lépni
     */
    public static void changePiecePosition(JButton b1, JButton b2){
        if(canChange(b1,b2)){
            Icon temp = b1.getIcon();
            ImageIcon img = new ImageIcon("circle.png");
            Image circle = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon circleIcon = new ImageIcon(circle);
            b1.setIcon(circleIcon);
            b2.setIcon(temp);
            Player p1 = null;
            Player p2 = null;
            for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
                JButton button = entry.getKey();
                Field field = entry.getValue();

                if(button == b1){
                    p1 = field.getPlayer();
                }else if(button == b2){
                    p2 = field.getPlayer();
                }
            }

            Player tempPlayer = null;
            boolean setTemp = false;

            for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
                JButton button = entry.getKey();
                Field field = entry.getValue();
                
                if(button == b1){
                    if(!setTemp){
                        tempPlayer = p1;
                        setTemp = true;
                        field.setPlayer(p2);
                    }else{
                        field.setPlayer(tempPlayer);
                    }
                }else if(button == b2){
                    if(!setTemp){
                        tempPlayer = p2;
                        setTemp = true;
                        field.setPlayer(p1);
                    }else{
                        field.setPlayer(tempPlayer);
                    }
                }
            }
        }
    }

    /**
     * Mivel minden körben a másik játékos jön, ezért ez a függvény beállítja minden kör elején hogy ki az éppen aktuális játékos. Az éppen aktuális játékos mindig az ellenkező, mint az előző kör aktuális játékosa
     *
     * @param a egyik játékos
     * @param b másik játékos
     * @return aktuálsi paraméter
     */
    public static Player changePlayer(Player a, Player b){
        if(akt==a){
            akt = b;
        }else{
            akt = a;
        }
        return akt;
    }

    /**
     * Ha egy játékos rálép egy mezőre, akkor ez a metódus beállítja a gombnak megfelelő mező játékos paraméterét a megfelelő játékosra
     *
     * @param b erre a mezőre lépett a játékos
     * @param a ez a játékos szeretne rálépni a mezőre
     */
    public static void changePlayerOnField(JButton b, Player a){
        String name = a.getName();
        System.out.println(a.getName());
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            JButton button = entry.getKey();
            Field field = entry.getValue();

            if(button==b){
                field.setPlayer(a);
            }
        }
    }

    /**
     * Megnézi, hogy a mezőn már áll-e játékos, mivel ha igen, akkor oda nem léphez játékos
     *
     * @param b Ehhez a gombhoz tartozó mezőhöz nézi meg, hogy van-e játékos hozzárendelve
     * @return visszaadja, hogy az adott gombhoz tartozó mező foglalt-e
     */
    public static boolean isFieldOccupied(JButton b){
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            JButton button = entry.getKey();
            Field field = entry.getValue();

            if(button==b){
                if(field.getPlayer()==null){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Ha minden megfelelő, akkor ez a függvény végrehajtja azt, hogy egy játékos elfoglaljon egy mezőt, ha az a mező még nem foglalt
     *
     * @param aktual a játékos, aki elfoglalja a mezőt
     * @param b ezt a mezőt foglalja el a játékos
     */
    public static void putPiece(Player aktual, JButton b){
        if(!isFieldOccupied(b)){
            if(aktual.getColor().equals("red")){
                ImageIcon temp = new ImageIcon("red_circle.png");
                Image tempImg = temp.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                ImageIcon newIcom = new ImageIcon(tempImg);
                b.setIcon(newIcom);
                b.repaint();
            }else{
                ImageIcon temp = new ImageIcon("blue_circle.png");
                Image tempImg = temp.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                ImageIcon newIcom = new ImageIcon(tempImg);
                b.setIcon(newIcom);
                b.repaint();
            }
            changePlayerOnField(b, akt);
        }
    }



    /**
     * Ez a gombok actionlistenerje. Lefuttatja a plya metódust, ami kezeli központilag az egészet.
     *
     * @param button a gomb amire kattintottak
     */
    public static void handleButtonClick(JButton button) {
        Field field = buttonFieldMap.get(button); // Kapcsolódó mező lekérése
        if (field != null) {
            play(a, b, button);
        }
    }

    /**
     * Megnézi, hogy a kapot 3 mezőnek ugyan az-e a játékosa. Ez majd azért lesz szükséges, hogy lássuk, hogyha malom alakul ki. Ezt úgy teszi meg, hogy megnézi, hogy a megadott 3 mező játékos paramétere ugyan az-e
     *
     * @param f1 első mező
     * @param f2 második mező
     * @param f3 harmadik mező
     * @return igaz, ha ugyan az a játékos paraméterük, hamis ha nem
     */
    public static boolean isAdjected(Field f1, Field f2, Field f3){
        boolean adjected = false;
        if(f1.getPlayer() != null && f1.getPlayer()==f2.getPlayer() && f2.getPlayer()==f3.getPlayer()){
            adjected = true;
        }
        return adjected;
    }

    /**
     * Megnézi, hogy a malomvolt listában szereplő malomszituációk még mindig malom-e, ugyanis ha nem, akkor kikerül belőle
     *@param malom lehetséges malom szituációk
     */
    public static void checksMaloms(int[][] malom){
        Field f1 = null;
        Field f2 = null;
        Field f3 = null;
        for (int i = 0; i < malom.length; i++) {
            for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
                if(entry.getValue().getId()==malom[i][0]){
                    f1 = entry.getValue();
                }else if(entry.getValue().getId()==malom[i][1]){
                    f2 = entry.getValue();
                }else if(entry.getValue().getId()==malom[i][2]){
                    f3 = entry.getValue();
                }
            }
            if(!isAdjected(f1, f2, f3) && malomvolt.contains(Integer.toString(i))){
                malomvolt.remove(Integer.toString(i));
            }
        }
    }

    /**
     * Megnézi, hogy malom van-e. Mivel beállítottuk a mezők indexei, ezért kigyűjtöttem a megfelelő index hármasokat, és ezeket végignézve megállapítjuk.
     *
     * @return visszaadja, hogy kialakult-e a malom helyzet
     */
    public static boolean isMalom(){
        Field f1 = null;
        Field f2 = null;
        Field f3 = null;
        int[][] malom = {
            {1,2,3}, {3,4,5}, {5,6,7}, {7,8,1}, 
            {11,12,13}, {13,14,15}, {15,16,17}, {17,18,11},
            {21,22,23}, {23,24,25}, {25,26,27}, {27,28,21},
            {2,12,22}, {4,14,24}, {6,16,26}, {8,18,28}
        };
        checksMaloms(malom);
        for (int i = 0; i < malom.length; i++) {
            for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
                if(entry.getValue().getId()==malom[i][0]){
                    f1 = entry.getValue();
                }else if(entry.getValue().getId()==malom[i][1]){
                    f2 = entry.getValue();
                }else if(entry.getValue().getId()==malom[i][2]){
                    f3 = entry.getValue();
                }
            }

            if(isAdjected(f1, f2, f3) && !malomvolt.contains(Integer.toString(i))){
                malomvolt.add(Integer.toString(i));
                return true;
            }
        }
        return false;
    }

    /**
     * Visszaadja a megfelelő gombhoz tartozó mezőt. Ehhez bejárja a Mapot úgy, hogy a gomb a kulcs.
     *
     * @param button gomb, amihez tartozó mezőt keressük
     * @return a megadott gombhoz tartozó mező
     */
    public static Field getField(JButton button){
        Field f = null;
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            JButton b = entry.getKey();
            Field field = entry.getValue();
            if(b==button){
                f = field;
            }
        }

        return f;
    }

    /**
     * Ha malom alakul ki, akkor a malmot kialakító eltávolíthatja az egyik mezőről a másik egyik bábúját.
     *
     * @param button gomb, ahonnan ki akarjuk törölni a játékost
     */
    public static void removePiece(JButton button){
        Field f = getField(button);
        if(f.getPlayer()!=null){
            f.setPlayer(null);
            ImageIcon img = new ImageIcon("circle.png");
            Image circle = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon circleIcon = new ImageIcon(circle);
            button.setIcon(circleIcon);
        }
    }

    /**
     * Megnézi, hogy a kapot mező egy malom része-e, ugyanis akkor nem törölhető ki az.
     *
     * @param f Megadott mező, ahol ellenőrizzük, hogy egy malom része-e
     * @return igaz, ha a mező egy malom része
     */
    public static boolean isInMalom(Field f){
        int[][] malom = {
            {1,2,3}, {3,4,5}, {5,6,7}, {7,8,1}, 
            {11,12,13}, {13,14,15}, {15,16,17}, {17,18,11},
            {21,22,23}, {23,24,25}, {25,26,27}, {27,28,21},
            {2,12,22}, {4,14,24}, {6,16,26}, {8,18,28}
        };
        for (int i = 0; i < malom.length; i++) {
            if(malomvolt.contains(Integer.toString(i))){
                for(int j = 0; j < 3; j++){
                    if(f.getId()==malom[i][j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Ha valaki nyert, akkor újratölti a Mapot, azaz beállítja az összes mező játékosát null-ra
     *
     */
    public static void reloadTable(){
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            Field field = entry.getValue();
            field.setPlayer(null);
        }
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            Field field = entry.getValue();
            int i = 0;
            if(field.getPlayer()!=null){
                i++;
            }
        }
    }

    /**
     * Megnézi, hogy a megadott játékosnak van-e olyan mezője, ahonnan törölhető
     *
     * @param p A játékos, akinek megnézzük, hogy van-e olyan mezője ami törölhető
     * @return igaz, ha van ilyen mezője
     */
    public static boolean hasPlayerFreePiece(Player p){
        boolean freePiece = false;
        int[][] malom = {
            {1,2,3}, {3,4,5}, {5,6,7}, {7,8,1}, 
            {11,12,13}, {13,14,15}, {15,16,17}, {17,18,11},
            {21,22,23}, {23,24,25}, {25,26,27}, {27,28,21},
            {2,12,22}, {4,14,24}, {6,16,26}, {8,18,28}
        };
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            Field field = entry.getValue();
            if(field.getPlayer()==p){
                boolean van = false;
                for(int i = 0; i < malom.length; i++){
                    if(malomvolt.contains(Integer.toString(i))){
                        for(int j = 0; j < 3; j++){
                            if(field.getId()==malom[i][j]){
                                van = true;
                            }
                        }
                    }    
                }
                if(!van){
                    freePiece = true;
                }
            }
        }

        return freePiece;
    }

    /**
     * Megvizsgálja, hogy a paraméterül kapott mezőnek van-e olyan szomszédja, ami szabad
     * @param f paraméterül kapott paraméter
     * @return ha van szabad szomszédos mezője, akkor igaz, ha nincs, akkor hamis
     */
    public static boolean hasFreeNeigthbort(Field f){
        boolean hasFree = false;
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            Field field = entry.getValue();
            int i = f.getId();
            int j = field.getId();
            System.out.println(i+j);
            if((f.getId() % 2 == 0) && (f.getId() != 8) && (f.getId() != 18) && (f.getId() != 28)){
                if((Math.abs(field.getId()-f.getId())==1 || Math.abs(field.getId()-f.getId())==10) && field.getPlayer() == null){
                    hasFree = true;
                }
            }else if((f.getId() == 8) || (f.getId() == 18) || (f.getId() == 28)){
                if(((f.getId()-field.getId())==1 || (f.getId()-field.getId())==7 || Math.abs(field.getId()-f.getId())==10) && field.getPlayer() == null){
                    hasFree = true;
                }
            }else if((f.getId() % 2 == 1) && (f.getId() != 1) && (f.getId() != 11) && (f.getId() != 21)){
                if((Math.abs(field.getId()-f.getId())==1) && field.getPlayer() == null){
                    hasFree = true;
                }
            }else if((f.getId() == 1) || (f.getId() == 11) || (f.getId() == 21)){
                if(((Math.abs(field.getId()-f.getId())==1) || (field.getId()-f.getId()==7))&& field.getPlayer() == null){
                    hasFree = true;
                }
            }
        }
        return hasFree;
    }

    /**
     * Megvizsgálja, hogy az éppen aktuális játékos tud-e lépni
     * @return ha tud, akkor hamis, he nem, akkor igaz
     */
    public static boolean canNotStep(){
        boolean canStep = false;
        boolean allfree = true;
        for (Map.Entry<JButton, Field> entry : buttonFieldMap.entrySet()) {
            Field field = entry.getValue();
            if(field.getPlayer()==akt){
                canStep = hasFreeNeigthbort(field);
                allfree = false;
            }
            if(canStep){
                return false;
            }
        }
        if(allfree){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Ha lenyomódik egy gomb, akkor ez a függvény fut le. Megvizsgálja, hogy milyen lépés lehetséges az aktuálsi játékosnak, és meghívja az ennek megfelő függvényeket, valamint ellenőrzi, hogy az adott játékos tud-e még lépni, vagy veszített. Ez a játék központi egysége úgymond, ez kezel minden fajta lépést. Ha egy játékos tud lerakni még játékost, akkor lerak, ha már nem, akkor léphet vele. A lépést állapotgépként oldottam meg.
     *
     * @param a egyik játékos
     * @param b másik játékos
     * @param button éppen aktuálisan kattintott gomb
     */
    public static void play(Player a, Player b, JButton button){
        boolean success = false;
        boolean removeOnePiece = remove;
        if((akt.getMaxPieces() < 3) || canNotStep()){
            if(canNotStep()){
                akt.playerWon();
            }else{
                if(akt==a){
                    b.playerWon();
                }else{
                    a.playerWon();
                }
            }
            refresPlayers();
            Player winner;
            if(akt==a){
                winner = b;
            }else{
                winner = a;
            }
            reloadTable();
            GameTable.showVictoryFrame(winner);
        }else{
            if(akt.getPieces() < akt.getMaxPieces() && !removeOnePiece && getField(button).getPlayer()==null){
                putPiece(akt, button);
                akt.addOnePiece();
                akt = changePlayer(a, b);
                success = true;
            }else if(click==0 && !removeOnePiece){
                if(getField(button).getPlayer()!=null && getField(button).getPlayer()==akt){
                    temp = button;
                    click++;
                    success = true;
                }
            }else if(click==1 && getField(button).getPlayer()==null && !removeOnePiece && canChange(temp, button)){
                changePiecePosition(temp, button);
                click = 0;
                akt = changePlayer(a, b);
                success = true;
            }
            if(isMalom()){
                remove = true;
            }
            if(removeOnePiece && (getField(button).getPlayer() == akt) && !isInMalom(getField(button)) && hasPlayerFreePiece(akt)){
                removePiece(button);
                akt.minusMaxPieces();
                akt.minusPieces();
                removeOnePiece = false;
                remove = false;
                success = true;
            }else if(removeOnePiece && !hasPlayerFreePiece(akt)){
                removeOnePiece = false;
                remove = false;
                success = true;
            }
            if(!success){
                click = 0;
            }
        }
    }

    /**
     * Ha valaki nyert, akkor megnézi, hogy a két játékos már benne van-e az adatbázisban, és ha nincs, akkor felveszi őket
     *
     */
    public static void refresPlayers(){
        if(!PlayerContainer.playerlist.contains(a)){
            PlayerContainer.playerlist.add(a);
        }
        if(!PlayerContainer.playerlist.contains(b)){
            PlayerContainer.playerlist.add(b);
        }
    }
}
