package models;

import java.awt.Image;
import java.util.Random;

import manager.ResourceManager;

public class Enemy extends Human {

    private int currentOrientation;
    private int dx,dy;
    protected Image[] enemyImgs;
    private boolean isNear=false;

    public Enemy(int x, int y, int width, int height, Image img, int orientation, int hp, int attack, int defense) {
        super(x, y, width, height, img, orientation, true, hp, attack, defense);
        currentOrientation = orientation;
        enemyImgs = ResourceManager.getInstance().getEnemyImage();
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        if (currentOrientation != orientation) {
            image = enemyImgs[orientation];
            currentOrientation = orientation;
        }
    }
    public void printHunterCord(Hunter hunter){
        System.out.println(hunter.x+" toa do "+hunter.y);
    }
    public void checkNear(int hunterX,int hunterY){        
        dx = (x - hunterX);
        dy = (y - hunterY);
    }
    public boolean creatOrientation(){
        
        if (Math.abs(dx) < 100 && Math.abs(dy) < 100) {
            if (dx != 0) {
                if (dx < 0) {
                    setOrientation(1);
                } else {
                    setOrientation(0);
                }
            } else if (dy < 0) {
                setOrientation(3);
            } else {
                setOrientation(2);
            }
            isNear=true;
            return true;

        } else {
            Random rand = new Random();
            int newOrientation;
            if (isStucked) {
                newOrientation = rand.nextInt(4);
                if (newOrientation != orientation) {
                    setOrientation(newOrientation);
                    isStucked = false;
                }
            } else if (rand.nextInt(10000) % 999 == 0) {
                newOrientation = rand.nextInt(4);
                if (newOrientation != orientation) {
                    setOrientation(newOrientation);
                    isStucked = false;
                } else {
                    setOrientation((newOrientation + 1) % 4);
                }
            }
            isNear=false;
            return false;
        }
    }
    public boolean getIsNear(){ return isNear;}
    @Override
    protected void move() {
        creatOrientation();
        super.move();
        
    }

}
