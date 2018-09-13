package manager;

import java.awt.Image;

import javax.swing.ImageIcon;

import models.Enemy;
import models.Hunter;

public class ResourceManager {

    private Image bulletImage;
    private Image heart;
    private Image hunterImage[][], playerAttack[][];
    private Image enemyImage[];
    private Image brick, grass, rock, tree, water;
    private Image explosions[];

    private static final ResourceManager INSTANCE = new ResourceManager();

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    private ResourceManager() {
        bulletImage = new ImageIcon(getClass().getResource("/images/bullet.png")).getImage();
        hunterImage = new Image[4][4];
        hunterImage[0][0] = new ImageIcon(Hunter.class.getResource("/images/l0.png")).getImage();
        hunterImage[0][1] = new ImageIcon(Hunter.class.getResource("/images/l1.png")).getImage();
        hunterImage[0][2] = new ImageIcon(Hunter.class.getResource("/images/l2.png")).getImage();
        hunterImage[0][3] = new ImageIcon(Hunter.class.getResource("/images/l3.png")).getImage();
        hunterImage[1][0] = new ImageIcon(Hunter.class.getResource("/images/r0.png")).getImage();
        hunterImage[1][1] = new ImageIcon(Hunter.class.getResource("/images/r1.png")).getImage();
        hunterImage[1][2] = new ImageIcon(Hunter.class.getResource("/images/r2.png")).getImage();
        hunterImage[1][3] = new ImageIcon(Hunter.class.getResource("/images/r3.png")).getImage();
        hunterImage[2][0] = new ImageIcon(Hunter.class.getResource("/images/u0.png")).getImage();
        hunterImage[2][1] = new ImageIcon(Hunter.class.getResource("/images/u1.png")).getImage();
        hunterImage[2][2] = new ImageIcon(Hunter.class.getResource("/images/u2.png")).getImage();
        hunterImage[2][3] = new ImageIcon(Hunter.class.getResource("/images/u3.png")).getImage();
        hunterImage[3][0] = new ImageIcon(Hunter.class.getResource("/images/d0.png")).getImage();
        hunterImage[3][1] = new ImageIcon(Hunter.class.getResource("/images/d1.png")).getImage();
        hunterImage[3][2] = new ImageIcon(Hunter.class.getResource("/images/d2.png")).getImage();
        hunterImage[3][3] = new ImageIcon(Hunter.class.getResource("/images/d3.png")).getImage();
        playerAttack = new Image[4][4];
        playerAttack[0][0] = new ImageIcon(Hunter.class.getResource("/images/la0.png")).getImage();
        playerAttack[0][1] = new ImageIcon(Hunter.class.getResource("/images/la1.png")).getImage();
        playerAttack[0][2] = new ImageIcon(Hunter.class.getResource("/images/la2.png")).getImage();
        playerAttack[0][3] = new ImageIcon(Hunter.class.getResource("/images/la3.png")).getImage();
        playerAttack[1][0] = new ImageIcon(Hunter.class.getResource("/images/ra0.png")).getImage();
        playerAttack[1][1] = new ImageIcon(Hunter.class.getResource("/images/ra1.png")).getImage();
        playerAttack[1][2] = new ImageIcon(Hunter.class.getResource("/images/ra2.png")).getImage();
        playerAttack[1][3] = new ImageIcon(Hunter.class.getResource("/images/ra3.png")).getImage();
        playerAttack[2][0] = new ImageIcon(Hunter.class.getResource("/images/ua0.png")).getImage();
        playerAttack[2][1] = new ImageIcon(Hunter.class.getResource("/images/ua1.png")).getImage();
        playerAttack[2][2] = new ImageIcon(Hunter.class.getResource("/images/ua2.png")).getImage();
        playerAttack[2][3] = new ImageIcon(Hunter.class.getResource("/images/ua3.png")).getImage();
        playerAttack[3][0] = new ImageIcon(Hunter.class.getResource("/images/da0.png")).getImage();
        playerAttack[3][1] = new ImageIcon(Hunter.class.getResource("/images/da1.png")).getImage();
        playerAttack[3][2] = new ImageIcon(Hunter.class.getResource("/images/da2.png")).getImage();
        playerAttack[3][3] = new ImageIcon(Hunter.class.getResource("/images/da3.png")).getImage();

        heart = new ImageIcon(getClass().getResource("/images/heart_boss.png")).getImage();

        enemyImage = new Image[4];
        enemyImage[0] = new ImageIcon(Enemy.class.getResource("/images/bossyellow_left.png")).getImage();
        enemyImage[1] = new ImageIcon(Enemy.class.getResource("/images/bossyellow_right.png")).getImage(); 
        enemyImage[2] = new ImageIcon(Enemy.class.getResource("/images/bossyellow_up.png")).getImage();
        enemyImage[3] = new ImageIcon(Enemy.class.getResource("/images/bossyellow_down.png")).getImage();

        brick = new ImageIcon(getClass().getResource("/images/door.png")).getImage();
        grass = new ImageIcon(getClass().getResource("/images/grass.png")).getImage();
        rock = new ImageIcon(getClass().getResource("/images/rock.png")).getImage();
        tree = new ImageIcon(getClass().getResource("/images/tree.png")).getImage();
        water = new ImageIcon(getClass().getResource("/images/water.png")).getImage();

        explosions = new Image[6];
        explosions[0] = new ImageIcon(getClass().getResource("/images/exp1.png")).getImage();
        explosions[1] = new ImageIcon(getClass().getResource("/images/exp2.png")).getImage();
        explosions[2] = new ImageIcon(getClass().getResource("/images/exp3.png")).getImage();
        explosions[3] = new ImageIcon(getClass().getResource("/images/exp4.png")).getImage();
        explosions[4] = new ImageIcon(getClass().getResource("/images/exp5.png")).getImage();
        explosions[5] = null;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public Image[][] getHunterImage() {
        return hunterImage;
    }

    public Image getHeart() {
        return heart;
    }

    public Image[][] getAttackImage() {
        return playerAttack;
    }

    public Image[] getEnemyImage() {
        return enemyImage;
    }

    public Image[] getExplosions() {
        return explosions;
    }

    public Image getBrick() {
        return brick;
    }

    public Image getGrass() {
        return grass;
    }

    public Image getRock() {
        return rock;
    }

    public Image getTree() {
        return tree;
    }

    public Image getWater() {
        return water;
    }


}
