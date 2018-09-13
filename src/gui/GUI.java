 package gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame implements WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH_FRAME = 800;
	private static final int HEIGHT_FRAME = 700;
	private WindowAdapter windowAdapter;
	private MyContainer myContainer;
	
	public GUI(){
		initGUI();
		initComponents();
		addComponents();
		windowAdapter = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Exit game", "Exit", JOptionPane.YES_NO_OPTION);
				//null cho phep hien tren toan bo cai screen
				//this chi hien thi tren nen cua giao dien thoi
				if(result == JOptionPane.YES_OPTION){
						System.exit(0);//cau lenh thoat toan bo chuong trinh
					}
				}
		};
		addWindowListener(windowAdapter);
	}

	private void initGUI() {
		setTitle("Zombie Hunter");
		setLayout(new CardLayout());
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void initComponents() {
		myContainer = new MyContainer(WIDTH_FRAME, HEIGHT_FRAME);
	}
	
	private void addComponents() {
		add(myContainer);
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
