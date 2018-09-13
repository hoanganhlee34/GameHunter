package models;

import java.awt.Image;

public abstract class GameObjectDinamic2D extends GameObject2D{
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int BULLET = 4;
	public static final int TOTAL_ORIENTATION = 4;
	
	protected int orientation;
	
	public GameObjectDinamic2D(int x, int y, int width, int height, Image image,int orientation) {
		super(x, y, width, height, image);
		this.orientation = orientation;
	}

	protected abstract void move();
}
