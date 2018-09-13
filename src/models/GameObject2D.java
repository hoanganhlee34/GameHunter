package models;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class GameObject2D {
	protected int x,y,width,height;
	protected Image image;
	
	public GameObject2D(int x,int y,int width,int height,Image image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	public void draw(Graphics2D g2d){
			g2d.drawImage(image,x,y,width,height,null);	
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
