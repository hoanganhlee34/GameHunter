package models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import manager.ResourceManager;
import map.Map;
import java.io.PrintWriter;

public abstract class Human extends GameObjectDinamic2D {

    public static final int HUMAN_SIZE = 30;
    public static int gotoNextMap = 0;
    protected int orientation;
    private ManagerBullet managerBullet;
    private boolean isFired = false;
    protected boolean isStucked = false;
    private int xBullet;
    private int yBullet;
    private List<Rectangle> rectangles;
    private List<Rectangle> humanRectangles;
    private int hp, attack, defense;
    private boolean isEnemy;

    public Human(int x, int y, int width, int height, Image img,
            int orientation, boolean isEnemy, int hp, int attack, int defense) {
        super(x, y, width, height, img, orientation);

        managerBullet = new ManagerBullet(isEnemy);
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.isEnemy = isEnemy;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int df) {
        this.defense = df;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    public void setOrientation(String orientation) {
        switch(orientation){
            case "NORTH":
                this.orientation = 2;
                break;
            case "EAST":
                this.orientation = 1;
                break;
            case "WEST":
                this.orientation = 0;
                break;
            case "SOUTH":
                this.orientation = 3;
                break;
        }
    }

    public void addBullet() {
        if (!isFired) {
            xBullet = x;
            yBullet = y;
            int sizeBullet = 14;
            switch (orientation) {
                case LEFT:
                    xBullet = x - sizeBullet;
                    yBullet = y + (height - sizeBullet) / 2;
                    break;
                case RIGHT:
                    xBullet = x + width;
                    yBullet = y + (height - sizeBullet) / 2;
                    break;
                case UP:
                    xBullet = x + (height - sizeBullet) / 2;
                    yBullet = y - sizeBullet;
                    break;
                case DOWN:
                    xBullet = x + (height - sizeBullet) / 2;
                    yBullet = y + height;
                    break;
                default:
                    break;
            }
            Bullet bullet = new Bullet(xBullet, yBullet, sizeBullet,
                    sizeBullet, ResourceManager.getInstance().getBulletImage(),
                    orientation, this.attack);
            managerBullet.addBullet(bullet);
            isFired = true;
        } else if (managerBullet.getBullets().size() == 0) {
            isFired = false;
        }
    }

    public void drawAllBullet(Graphics2D g2d) {
        managerBullet.drawAllBullet(g2d);
    }

    public void moveAllBullet(Map map, Hunter hunter,
            ManagerEnemy managerEnemy) {
        managerBullet.moveAllBullet(map, hunter, managerEnemy);
    }
    public int calNextMap(int x1,int y1){
        if(x1<100) return -1;
        if(x1>500) return 1;
        if(y1<100) return 3;
        return -3;
    }
    @Override
    protected void move() {
        Rectangle humanRect = new Rectangle(x, y, width, height);
        humanRectangles = new ArrayList<>();
        for (int i = 0; i < ManagerGame.getHumansInstance().size(); i++) {
            Rectangle recItem = new Rectangle(ManagerGame.getHumansInstance()
                    .get(i).x, ManagerGame.getHumansInstance().get(i).y,
                    HUMAN_SIZE, HUMAN_SIZE);
            if (!recItem.intersects(humanRect)) {
                humanRectangles.add(recItem);
            }
        }
        rectangles = Map.getRectangleInstance();
        switch (orientation) {
            case LEFT:
                if (isEnemy) {
                    x-= 2;
                } else {
                    x -= 4;
                }
                humanRect = new Rectangle(x, y, width, height);
                for (int i = 0; i < humanRectangles.size(); i++) {
                    if (humanRectangles.get(i).intersects(humanRect)) {
                        if (isEnemy) {
                            x+= 2;
                        } else {
                            x += 4;
                        }
                        isStucked = true;
                        break;
                    }
                }
                if (rectangles != null) {
                    for (int i = 0; i < rectangles.size(); i++) {
                        if (rectangles.get(i).intersects(humanRect)) {
                            int x1 = rectangles.get(i).y / Map.ITEM_SIZE;
                            int y1 = rectangles.get(i).x / Map.ITEM_SIZE;
                            if (Map.getItemsOfInstance()[x1][y1] == Map.GATE) {
                                if (!isEnemy) {
                                    PrintWriter out2;
                                    try {
                                        out2 = new PrintWriter("saved.txt");
                                        out2.print(Hunter.getLevel()+" "+Hunter.getExp()+" "+getHp());
                                        out2.close();
                                    } catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                    }
                                    this.gotoNextMap = calNextMap(x,y);                     
                                }
                            }
                            if (isEnemy) {
                                x+= 2;
                            } else {
                                x += 4;
                            }
                            isStucked = true;
                            break;
                        }
                    }
                }

                break;
            case RIGHT:
                if (isEnemy) {
                    x+= 2;
                } else {
                    x += 4;
                }
                humanRect = new Rectangle(x, y, width, height);
                for (int i = 0; i < humanRectangles.size(); i++) {
                    if (humanRectangles.get(i).intersects(humanRect)) {
                        if (isEnemy) {
                            x-= 2;
                        } else {
                            x -= 4;
                        }
                        isStucked = true;
                        break;
                    }
                }
                if (rectangles != null) {
                    for (int i = 0; i < rectangles.size(); i++) {
                        if (rectangles.get(i).intersects(humanRect)) {
                            int x1 = rectangles.get(i).y / Map.ITEM_SIZE;
                            int y1 = rectangles.get(i).x / Map.ITEM_SIZE;
                            if (Map.getItemsOfInstance()[x1][y1] == Map.GATE) {
                                if (!isEnemy) {
                                    PrintWriter out2;
                                    try {
                                        out2 = new PrintWriter("saved.txt");
                                        out2.print(Hunter.getLevel()+" "+Hunter.getExp()+" "+getHp());
                                        out2.close();
                                    } catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                    }
                                    this.gotoNextMap = calNextMap(x,y);
                                }
                            }
                            if (isEnemy) {
                                x-= 2;
                            } else {
                                x -= 4;
                            }
                            isStucked = true;
                            break;
                        }
                    }
                }

                break;
            case UP:
                if (isEnemy) {
                    y-= 2;
                } else {
                    y -= 4;
                }
                humanRect = new Rectangle(x, y, width, height);
                for (int i = 0; i < humanRectangles.size(); i++) {
                    if (humanRectangles.get(i).intersects(humanRect)) {
                        if (isEnemy) {
                            y+= 2;
                        } else {
                            y += 4;
                        }
                        isStucked = true;
                        break;
                    }
                }
                if (rectangles != null) {
                    for (int i = 0; i < rectangles.size(); i++) {
                        if (rectangles.get(i).intersects(humanRect)) {
                            int x1 = rectangles.get(i).y / Map.ITEM_SIZE;
                            int y1 = rectangles.get(i).x / Map.ITEM_SIZE;
                            if (Map.getItemsOfInstance()[x1][y1] == Map.GATE) {
                                if (!isEnemy) {
                                    PrintWriter out2;
                                    try {
                                        out2 = new PrintWriter("saved.txt");
                                        out2.print(Hunter.getLevel()+" "+Hunter.getExp()+" "+getHp());
                                        out2.close();
                                    } catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                    }
                                    this.gotoNextMap = calNextMap(x,y);
                                }
                            }
                            if (isEnemy) {
                                y+=2;
                            } else {
                                y += 4;
                            }
                            isStucked = true;
                            break;
                        }
                    }
                }
                break;
            case DOWN:
                if (isEnemy) {
                    y+= 2;
                } else {
                    y += 4;
                }
                humanRect = new Rectangle(x, y, width, height);
                for (int i = 0; i < humanRectangles.size(); i++) {
                    if (humanRectangles.get(i).intersects(humanRect)) {
                        if (isEnemy) {
                            y-= 2;
                        } else {
                            y -= 4;
                        }
                        isStucked = true;
                        break;
                    }
                }
                if (rectangles != null) {
                    for (int i = 0; i < rectangles.size(); i++) {
                        if (rectangles.get(i).intersects(humanRect)) {
                            int x1 = rectangles.get(i).y / Map.ITEM_SIZE;
                            int y1 = rectangles.get(i).x / Map.ITEM_SIZE;
                            if (Map.getItemsOfInstance()[x1][y1] == Map.GATE) {
                                if (!isEnemy) {
                                    PrintWriter out2;
                                    try {
                                        out2 = new PrintWriter("saved.txt");
                                        out2.print(Hunter.getLevel()+" "+Hunter.getExp()+" "+getHp());
                                        out2.close();
                                    } catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                    }
                                    this.gotoNextMap = calNextMap(x,y);
                                }
                            }
                            if (isEnemy) {
                                y-=2;
                            } else {
                                y -= 4;
                            }
                            isStucked = true;
                            break;
                        }
                    }
                }

                break;
            default:
                break;
        }
    }

}
