package gui;

import java.awt.CardLayout;

public class MyContainer extends BaseContainer implements IShowPlayGame,IShowExit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENU = "MENU";
	public static final String START = "START";
	public static final String EXIT = "EXIT";
	
	private CardLayout cardLayout;
	private MenuGame menuGame;
	private GamePlayPanel gamePlayPanel;

	
	public MyContainer(int width, int height) {
		super(width, height);
//		initPanel();
//		initComponents();
//		addComponents();
	}


	@Override
	protected void initPanel() {
		cardLayout = new CardLayout();
		setSize(this.width, this.height);
		setLayout(cardLayout);
	}

	@Override
	protected void initComponents() {
		menuGame = new MenuGame(width, height);
		menuGame.setCardLayoutParent(cardLayout);
		menuGame.setiShowPlayGame(this);
	}

	@Override
	protected void addComponents() {
		add(menuGame, MENU);
		cardLayout.show(this, MENU);
	}
        
        public void showMenuGame(){
            add(menuGame, MENU);
            cardLayout.show(this, MENU);
        }

	@Override
	public void showPlayGame() {
		gamePlayPanel = new GamePlayPanel(width, height);
		add(gamePlayPanel, START);
		cardLayout.show(this, START);
	    gamePlayPanel.requestFocus();
	}
	
	


	@Override
	public void showExit() {
		cardLayout.show(this, EXIT);
		
	}

}