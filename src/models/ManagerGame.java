package models;

import gui.GamePlayPanel;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import manager.ResourceManager;
import map.Map;

public class ManagerGame {

    private Hunter hunter;
    private ManagerEnemy managerEnemy;
    private static List<Human> HUMANS;
    boolean result = false;
    private Map map;
    private int playerLevel, playerExp,playerHp;
    private static int currentMap = 5;
    private GamePlayPanel gamePlayPanel;
    private int playerNewX = 200, playerNewY = 600;
    private static int level[][];
    public static int[][] getAllLevel(){return level;}

    public ManagerGame(GamePlayPanel gamePlayPanel) {
        this.gamePlayPanel = gamePlayPanel;
        init();
    }

    public static List<Human> getHumansInstance() {
        if (!HUMANS.isEmpty()) {
            return HUMANS;
        } else {
            return null;
        }
    }

    private int readFile() {
        int lineNum = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("level/level.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                int stt = 0;
                if (line.charAt(0) == '/') {
                    continue;
                }
                String tmp[] = line.split(" ");
                for (int k = 0; k < tmp.length; k++) {
                    level[lineNum][k] = Integer.parseInt(tmp[k]);
                }
                lineNum++;
            }
            br = new BufferedReader(new FileReader("saved.txt"));
            line = br.readLine();
            String tmp[] = line.split(" ");
            playerLevel = Integer.parseInt(tmp[0]);
            playerExp = Integer.parseInt(tmp[1]);
            playerHp=Integer.parseInt(tmp[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void init() {
        HUMANS = new ArrayList<>();
        level = new int[5][5];
        readFile();
        int pHp, pAttack, pDefense;
        pHp = playerHp;
        pAttack = level[playerLevel][3];
        pDefense = level[playerLevel][4];
        hunter = new Hunter(playerNewX, playerNewY, Human.HUMAN_SIZE, Human.HUMAN_SIZE,
                ResourceManager.getInstance().getHunterImage()[0][0],
                GameObjectDinamic2D.LEFT, gamePlayPanel, pHp, pAttack, pDefense, playerLevel, playerExp);
        managerEnemy = new ManagerEnemy(hunter);
        setListHuman();
        map = new Map("map" + currentMap);
    }

    public void drawAll(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("HP:", 680, 140);
        g2d.drawString(""+hunter.getHp(), 760, 140);
        
        g2d.drawString("exp:", 680, 120);
        g2d.drawString(""+hunter.getExp(), 760, 120);
        
        g2d.drawString("attack:", 680, 160);
        g2d.drawString(""+hunter.getAttack(), 760, 160);
        
        g2d.drawString("defense:", 680, 180);
        g2d.drawString(""+hunter.getDefense(), 760, 180);
        
        g2d.drawString("level: ", 700, 100);
        g2d.drawString(""+hunter.getLevel(), 760, 100);       
                
        drawAllObject(g2d);


    }
    public void setPlayerNewCord(int x1,int y1){
        playerNewX=x1;
        playerNewY=y1;
    }
    public void nextMap(int val) {
        if(val==1)
        setPlayerNewCord(56,324);
        if(val==-1)
        setPlayerNewCord(590,324);
        if(val==3)
        setPlayerNewCord(296,618);
        if(val==-3)
        setPlayerNewCord(299,33);
        currentMap+=val;
        init();
        Human.gotoNextMap = 0;
    }

    public void drawAllObject(Graphics2D g2d) {
        drawHunter(g2d);
        drawAllEnemy(g2d);
        drawMap(g2d);
        drawBullet(g2d);
    }

    public void drawMap(Graphics2D g2d) {
        map.draw(g2d);
    }

    public void drawHunter(Graphics2D g2d) {
        if (!hunter.isDead()) {
            int heart = hunter.getHp();
            int a = 0;
            for (int i = 0; i < (heart - 1) / 50 + 1; i++) {
                g2d.drawImage(ResourceManager.getInstance().getHeart(), hunter.x + a, hunter.y - 20, null);
                a += 10;
            }
            hunter.draw(g2d);
        }
    }

    public void changeOrientation(int orientation) {
        hunter.setOrientation(orientation);
    }

    public void setImg(int i, int j) {
        hunter.setImage(i, j);
    }

    

    public void moveHunter() {
        if (!hunter.isDead()) {
            hunter.move();            
        }
    }

    public void fireHunter() {
        if (!hunter.isDead()) {
            hunter.addBullet();
        }
    }

    private void setListHuman() {
        HUMANS.add(hunter);
        for (int i = 0; i < managerEnemy.getEnemys().size(); i++) {
            HUMANS.add(managerEnemy.getEnemys().get(i));
        }
    }

    public void moveAllBullet() {
        hunter.moveAllBullet(map, hunter, managerEnemy);
        managerEnemy.moveAllBullet(map, hunter, managerEnemy);
    }

    public void drawBullet(Graphics2D g2d) {
        hunter.drawAllBullet(g2d);
        managerEnemy.drawAllBullet(g2d);
    }

    public void drawAllEnemy(Graphics2D g2d) {
        managerEnemy.drawAllEnemy(g2d);
    }

    public void moveAllEnemy() {
        managerEnemy.moveAllEnemy(hunter.x, hunter.y);
    }

    public void fireEnemy() {
        managerEnemy.fireAllEnemy();
    }
}
