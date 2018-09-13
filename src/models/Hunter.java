package models;

import gui.GamePlayPanel;
import gui.MyContainer;

import java.awt.Image;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import manager.ResourceManager;

public class Hunter extends Human {

    private MyContainer mContainer;
    
    protected Image[][] hunterImgs,playerAttackImg;
    private int currentOrientation;
    private boolean result;
    private boolean isDead;
    private static int level,exp;
    private GamePlayPanel gamePlayPanel;
    public static void setExp(int exp_){exp=exp_;}
    public static int getExp(){ return exp;}
    public static int getLevel(){return level;}
    public static void setLevel(int level_){ level=level_;}

    public Hunter(int x, int y, int width, int height, Image img,
            int orientation, GamePlayPanel gamePlayPanel,int hp,int attack,int defense,int level,int exp) {
        super(x, y, width, height, img, orientation, false,hp,attack,defense);
        this.gamePlayPanel = gamePlayPanel;
        hunterImgs = ResourceManager.getInstance().getHunterImage();
        currentOrientation = orientation;
        this.level=level;
        this.exp=exp;
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }
    public void setImage(int i,int j){
        image = hunterImgs[i][j];
    }
    

    public void isHunted(int bulletAttack) {
        if (!result) {
            int tmp=getHp()-(bulletAttack-getDefense());
            if(tmp>0)
                setHp(tmp);
            else
            {
                Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 6; i++) {
                        try {
                            image = ResourceManager.getInstance()
                                    .getExplosions()[i];
                            Thread.sleep(50);
                            if (i == 5) {
                                ManagerGame.getHumansInstance().remove(0);
                                isDead = true;
                                result = true;
                                showPlayGame();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            PrintWriter out2;
                                    try {
                                        out2 = new PrintWriter("saved.txt");
                                        out2.print("0 0 150");
                                        out2.close();
                                    } catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                    }
            }
        }
    }

    protected void showPlayGame() {
        JOptionPane.showMessageDialog(null, "Game Over!");
        gamePlayPanel.setVisible(false);
    }

    public void birdIsDead() {
        result = true;
        showPlayGame();
    }

    public boolean isDead() {
        return isDead;
    }
}
