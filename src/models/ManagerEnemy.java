package models;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import manager.ResourceManager;
import map.Map;

public class ManagerEnemy {
	private List<Enemy> enemys;
	private int score;
        private Hunter hunter;
	
	public ManagerEnemy(Hunter hunter){
		enemys = new ArrayList<>();
		initHumans();
                this.hunter=hunter;
	}
	
	public void initHumans(){
		Enemy e1 = new Enemy(599,57, Human.HUMAN_SIZE, Human.HUMAN_SIZE, ResourceManager.getInstance().getEnemyImage()[3], GameObjectDinamic2D.DOWN,30,50,50);
		enemys.add(e1);
		Enemy e2 = new Enemy(41,57, Human.HUMAN_SIZE, Human.HUMAN_SIZE, ResourceManager.getInstance().getEnemyImage()[0], GameObjectDinamic2D.LEFT,30,50,50);
		enemys.add(e2);
		Enemy e3 = new Enemy(311,312, Human.HUMAN_SIZE, Human.HUMAN_SIZE, ResourceManager.getInstance().getEnemyImage()[0], GameObjectDinamic2D.LEFT,30,50,50);
		enemys.add(e3);
		Enemy e4 = new Enemy(38,612, Human.HUMAN_SIZE, Human.HUMAN_SIZE, ResourceManager.getInstance().getEnemyImage()[0], GameObjectDinamic2D.LEFT,30,50,50);
		enemys.add(e4);
                Enemy e5 = new Enemy(599,612, Human.HUMAN_SIZE, Human.HUMAN_SIZE, ResourceManager.getInstance().getEnemyImage()[0], GameObjectDinamic2D.LEFT,30,50,50);
		enemys.add(e5);
	}
	
	public void moveAllEnemy(int hunterX,int hunterY){
		for(int i=0;i<enemys.size();i++){
                        enemys.get(i).checkNear(hunterX,hunterY);
			enemys.get(i).move();
		}
	}
	public void drawAllEnemy(Graphics2D g2d){
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).draw(g2d);
                        int a = 0;
                        for(int j = 0; j < enemys.get(i).getHp(); j+=10){
                            g2d.drawImage(ResourceManager.getInstance().getHeart(), enemys.get(i).x + a, enemys.get(i).y - 20, null);
                            a += 10;
                        }
                }
	}
	public void fireAllEnemy(){
		for(int i=0;i<enemys.size();i++){
		enemys.get(i).addBullet();
		}
	}
	public void drawAllBullet(Graphics2D g2d){
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).drawAllBullet(g2d);
		}
	}
	
	public void moveAllBullet(Map map,Hunter hunter,ManagerEnemy managerEnehunter){
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).moveAllBullet(map,hunter,managerEnehunter);
		}
	}

	public List<Enemy> getEnemys() {
		return enemys;
	}

	public void setEnehunters(List<Enemy> enemys) {
		this.enemys = enemys;
	}
	public void removeEnemys(final int i){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int j =0;j<6;j++){
					try {
						enemys.get(i).setImage(ResourceManager.getInstance().getExplosions()[j]);
						Thread.sleep(17);
						if(j==5){
							enemys.remove(i);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				score += 100;
				System.out.println(score);
			}
		});
		thread.start();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
