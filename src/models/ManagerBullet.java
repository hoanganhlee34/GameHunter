package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import map.Map;

public class ManagerBullet {
	private List<Bullet> bullets;
	private List<Rectangle> rectangles;
	private List<Rectangle> humanRectangles;
	private final boolean value;

	public ManagerBullet(boolean value) {
		this.value = value;
		bullets = new ArrayList<>();
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void drawAllBullet(Graphics2D g2d) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g2d);
		}

	}
        public int checkLevel(int exp, Hunter myTank) {
        int i = 4;
        int level[][] = ManagerGame.getAllLevel();
        for (i = 4; i >= 0; i--) {
            if (exp >= level[i][1]) {
                if (myTank.getLevel() < level[i][0]) 
                {
                    myTank.setHp(level[i][2]);
                    myTank.setAttack(level[i][3]);
                    myTank.setDefense(level[i][4]);
                }
                return i;
            }
        }
        return i;
    }
	public void moveAllBullet(Map map, Hunter myTank,
			ManagerEnemy enemyManager) {
		boolean isBreak = false;

		rectangles = Map.getRectangleInstance();
		humanRectangles = new ArrayList<>();
		if (!ManagerGame.getHumansInstance().isEmpty()) {
			for (int i = 0; i < ManagerGame.getHumansInstance().size(); i++) {
				humanRectangles.add(new Rectangle(ManagerGame.getHumansInstance()
						.get(i).x, ManagerGame.getHumansInstance().get(i).y,
						Human.HUMAN_SIZE, Human.HUMAN_SIZE));
			}

		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
			Rectangle bulletRect = new Rectangle(bullets.get(i).x,
					bullets.get(i).y, bullets.get(i).width,
					bullets.get(i).height);
			for (int j = 0; j < humanRectangles.size(); j++) {
				if (humanRectangles.get(j).intersects(bulletRect)) {
					if (j == 0) {
						myTank.isHunted(bullets.get(i).getAttack());
					} else {
						try {
							if (value) {

							} else {
                                                            enemyManager.getEnemys().get(j - 1).setHp(enemyManager.getEnemys().get(j-1).getHp() - myTank.getAttack());
                                                            if(enemyManager.getEnemys().get(j - 1).getHp() > 0){                                                                
                                                                
                                                            }else{
                                                                Hunter.setExp(Hunter.getExp()+10);
                                                                Hunter.setLevel(checkLevel(Hunter.getExp(),myTank));
								humanRectangles.remove(j);
								enemyManager.removeEnemys(j - 1);
								ManagerGame.getHumansInstance().remove(j);
                                                            }
							}
						} catch (IndexOutOfBoundsException e) {
							e.printStackTrace();
						}
					}
					bullets.remove(i);
					isBreak = true;
					break;
				}
			}
			if (!isBreak && rectangles != null) {
				for (int j = 0; j < rectangles.size(); j++) {
					if (rectangles.get(j).intersects(bulletRect)) {
						int x = rectangles.get(j).y / Map.ITEM_SIZE;
						int y = rectangles.get(j).x / Map.ITEM_SIZE;
						if (map.getItems()[x][y] != Map.WATER) {
							bullets.remove(i);
							
							break;
						}

					}
				}
			}
		}

	}

	public List<Rectangle> getTankRectangles() {
		return humanRectangles;
	}

	public void setTankRectangles(List<Rectangle> humanRectangles) {
		this.humanRectangles = humanRectangles;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

}
