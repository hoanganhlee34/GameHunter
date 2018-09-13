package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.Random;

import javax.swing.JLabel;

import models.GameObjectDinamic2D;
import models.ManagerGame;
import models.Human;

public class GamePlayPanel extends BaseContainer implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
        private int time=0;
	private ManagerGame managerGame;
	private BitSet bitSet;
        private int orientation;


	public GamePlayPanel(int width, int height) {
		super(width, height);
	}

	@Override
	protected void initPanel() {
		setSize(this.width, this.height);
		setBackground(Color.BLACK);

	}

	@Override
	protected void initComponents() {
		managerGame = new ManagerGame(this);

		bitSet = new BitSet(5);
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				execuseKeyListener(e, true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				execuseKeyListener(e, false);
			}
		};
		addKeyListener(keyAdapter);
		setFocusable(true);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	protected void addComponents() {

	}

	private void execuseKeyListener(KeyEvent e, boolean isPressed) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
                        orientation=0;
			//bitSet.set(GameObjectDinamic2D.LEFT, isPressed);
                        time++;
                        time%=4;
                        managerGame.changeOrientation(GameObjectDinamic2D.LEFT);
                        managerGame.setImg(0,time);
                        managerGame.moveHunter();
			break;
		case KeyEvent.VK_RIGHT:
                    orientation=1;
			//bitSet.set(GameObjectDinamic2D.RIGHT, isPressed);
			time++;
                        time%=4;
                        managerGame.changeOrientation(GameObjectDinamic2D.RIGHT);
                        managerGame.setImg(1,time);
                        managerGame.moveHunter();
                        break;
		case KeyEvent.VK_UP:
                    orientation=2;
			//bitSet.set(GameObjectDinamic2D.UP, isPressed);
                        time++;
                        time%=4;
                        managerGame.changeOrientation(GameObjectDinamic2D.UP);
                        managerGame.setImg(2,time);
                        managerGame.moveHunter();
			break;
		case KeyEvent.VK_DOWN:
                    orientation=3;
			//bitSet.set(GameObjectDinamic2D.DOWN, isPressed);
                        time++;
                        time%=4;
                        managerGame.changeOrientation(GameObjectDinamic2D.DOWN);
                        managerGame.setImg(3,time);
                        managerGame.moveHunter();
			break;
                
		case KeyEvent.VK_SPACE:
			bitSet.set(GameObjectDinamic2D.BULLET, isPressed);
		default:
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		managerGame.drawAll(g2d);

	}

	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
                        if(Human.gotoNextMap!=0){
                            managerGame.nextMap(Human.gotoNextMap);
                        }

			if (rand.nextInt(100) % 10 == 0) {
				managerGame.fireEnemy();
			}
			
			if (bitSet.get(GameObjectDinamic2D.BULLET)) {
				managerGame.fireHunter();
			}
			managerGame.moveAllBullet();
			managerGame.moveAllEnemy();

			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
