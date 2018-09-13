package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MenuGame extends BaseContainer implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IShowPlayGame iShowPlayGame;
	private JButton btnStart;
	private JButton btnExit;
	private CardLayout cardLayoutParent;
	private Image bg;
	private IShowExit iShowExit;	

	public MenuGame(int width, int height) {
		super(width, height);
		initPanel();
		initComponents();
		addComponents();
	}

	@Override
	protected void initPanel() {
		setSize(width, height);
		setLayout(null);
	}

	@Override
	protected void initComponents() {
		// start = new
		// ImageIcon(getClass().getResource("/images/bt_start1.png")).getImage();
		bg = new ImageIcon(getClass().getResource("/images/icon.jpg"))
				.getImage();
		btnStart = new JButton();
		btnStart.setText("START");
		btnStart.setBounds(350, 250, 150, 50);
		btnStart.addActionListener(this);

		

		btnExit = new JButton();
		btnExit.setText("EXIT");
		btnExit.setBounds(350, 370, 150, 50);
		btnExit.addActionListener(this);

	}

	@Override
	protected void addComponents() {
		add(btnStart);
		add(btnExit);

	}

	public void setCardLayoutParent(CardLayout cardLayoutParent) {
		this.cardLayoutParent = cardLayoutParent;
	}

	public void setiShowPlayGame(IShowPlayGame iShowPlayGame) {
		this.iShowPlayGame = iShowPlayGame;
	}

	

	
	public void setiShowExit(IShowExit iShowExit) {
		this.iShowExit = iShowExit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "START":
			iShowPlayGame.showPlayGame();
			break;
		
		case "EXIT":
//		iShowExit.showExit();
			int result = JOptionPane.showConfirmDialog(null, "Exit Game", "Exit", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION){
				System.exit(0);
			}
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, 0, 0, width, height, null);
	}

	public CardLayout getCardLayoutParent() {
		return cardLayoutParent;
	}

	public Image getBg() {
		return bg;
	}

}
