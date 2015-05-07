package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class LevelGenerator {
	

	public static List<Bricks> brick;
	public static List<Bricks> unBreackable;
	private static int maxWidth = 640;
	private static int maxHeight = 480;
	public static int currentLevel = 0;
	
	public static void init() {
		brick =new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
		    	Random rand = new Random();
		        int randomNum = rand.nextInt(3);
		        if(randomNum==1){
		        	brick.add(new Bricks(i,y,50,20));
				}
			}
		}
		unBreackable = new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth -50; i+=100){
			for(int y=50; y < 200; y+=40){
		    	Random rand = new Random();
		        int randomNum = rand.nextInt(7);
		        if(randomNum==1){
		        	unBreackable.add(new Bricks(i,y,50,20));
				}
			}
		}
	}
	
	public static void firstLevel(){
		unBreackable.removeAll(unBreackable);
		brick.removeAll(brick);
		brick =new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
		    	Random rand = new Random();
		        int randomNum = rand.nextInt(3);
		        if(randomNum==1){
		        	brick.add(new Bricks(i,y,50,20));
				}
			}
		}
		unBreackable = new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth -50; i+=100){
			for(int y=50; y < 200; y+=40){
		    	Random rand = new Random();
		        int randomNum = rand.nextInt(7);
		        if(randomNum==1){
		        	unBreackable.add(new Bricks(i,y,50,20));
				}
			}
		}
		
		
	}
	
	
	public static void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Bricks b : brick){
			g.setColor(Color.pink);
			g.fill(b);
		}
		for(Bricks b : unBreackable){
			g.setColor(Color.red);
			g.fill(b);
		}
	}
	

	
	public static void newLevel() {
		
		if(brick.size() == 0){
			brick =new ArrayList<Bricks>();
			for(int i = 50; i<maxWidth -50; i+=50){
				for(int y=50; y < 200; y+=20){
			    	Random rand = new Random();
			        int randomNum = rand.nextInt(2);
			        if(randomNum==1){
			        	brick.add(new Bricks(i,y,50,20));
					}
				}
			}
			
			unBreackable.removeAll(unBreackable);
			unBreackable = new ArrayList<Bricks>();
			for(int i = 50; i<maxWidth -50; i+=100){
				for(int y=50; y < 200; y+=40){
			    	Random rand = new Random();
			        int randomNum = rand.nextInt(7);
			        if(randomNum==1){
			        	unBreackable.add(new Bricks(i,y,50,20));
					}
				}
			}
			currentLevel++;
			
		}
		
	}
	
	
	
	
	
}

