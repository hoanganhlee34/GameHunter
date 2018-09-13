package map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import manager.ResourceManager;



public class Map {
    //private final static String IMAGE_DIR = "maps/";
	public static final int MAP_SIZE = 28;
	public static final int ITEM_SIZE = 24; 
	public static final int GATE = 1;
	public static final int WATER = 2;
	public static final int GRASS = 4;
	public static final int TREE = 3;
	public static final int ROCK = 5;
	private static int items[][] = new int [MAP_SIZE][MAP_SIZE];
	private String map;
	private static List<Rectangle> RECTANGLES;
	private RandomAccessFile randomAccessFile;
	
	public Map(String map){
		this.map = map;
		readFile();
	}
	
	public static List<Rectangle> getRectangleInstance(){
		if(!RECTANGLES.isEmpty()){
			return RECTANGLES;
		}
		return null;
	}
	private int readFile(){
		RECTANGLES = new ArrayList<>();
			try {
                            BufferedReader br = new BufferedReader( new FileReader("maps/"+ map+".txt"));
				//randomAccessFile = new RandomAccessFile(getClass().getResource("/maps/"+ map+".txt").getPath(),"rw");
				int lineIndex = 0;
                                String line;
                                while((line = br.readLine()) != null)
				{
					//String line = randomAccessFile.readLine();
                                        
					for(int i=0;i<line.length();i++){
						items[lineIndex][i] = Integer.parseInt(line.charAt(i)+"");
					}
					lineIndex++;
				}
				for(int i=0;i<MAP_SIZE;i++){
					for(int j=0;j<MAP_SIZE;j++){
						int index = 0;
						if(items[i][j]==0||items[i][j]==4){
							
						}
						else{
							RECTANGLES.add(index,new Rectangle(j*ITEM_SIZE,i*ITEM_SIZE,ITEM_SIZE,ITEM_SIZE));
							index++;
						}				
					}
					System.out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return 0;
	}
	public void draw(Graphics2D g2d){
		Image image;
		ResourceManager resourceManager = ResourceManager.getInstance();
		for(int i= 0;i<MAP_SIZE;i++){
			for(int j=0;j<MAP_SIZE;j++){
				switch (items[i][j]) {
				case GATE:
					image = resourceManager.getBrick();
					break;
				case WATER:
					image = resourceManager.getWater();
					break;
				case TREE:
					image = resourceManager.getTree();
					break;
				case GRASS:
					image = resourceManager.getGrass();
					break;
				case ROCK:
					image = resourceManager.getRock();
					break;
				
				default:
					image = null;
					break;
				}
				if(image!=null){
					g2d.drawImage(image,j*ITEM_SIZE,i*ITEM_SIZE,ITEM_SIZE,ITEM_SIZE,null);
				}
			}
		}
		for(int i=0;i<RECTANGLES.size();i++){
			
		}
	}

	public List<Rectangle> getRectangles() {
		return RECTANGLES;
	}

	
	public int[][] getItems() {
		return items;
	}
        public static int[][] getItemsOfInstance() {
		return items;
	}
	
	
}
