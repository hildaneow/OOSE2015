package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class LevelGenerator {
	
	//declares list of breakable bricks
	public static List<Bricks> brick;
	//declares list of unbreakable bricks
	public static List<Bricks> unBreackable;
	//Declares and initializes width of screen size
	private static int maxWidth = 640;
	//Declares and initializes first level
	public static int currentLevel = 0;
	
	
	public static void init() {
		//list for bricks
		brick =new ArrayList<Bricks>();
		//Double for loop that assigns breakable bricks in x and y-axis
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
		    	Random rand = new Random();
		    	//Makes sure that only 1/3 breakable brick is given a place
		        int randomNum = rand.nextInt(3);
		        if(randomNum==1){
		        	brick.add(new Bricks(i,y,50,20));
				}
			}
		}
		//Array list of unbreakable bricks
		unBreackable = new ArrayList<Bricks>();
		//Double for loop that assigns breakable bricks in x and y-axis
		for(int i = 50; i<maxWidth -50; i+=100){
			for(int y=50; y < 200; y+=40){
		    	Random rand = new Random();
		    	//Makes sure that only 1/7 unbreakable brick is given a place
		        int randomNum = rand.nextInt(7);
		        if(randomNum==1){
		        	unBreackable.add(new Bricks(i,y,50,20));
				}
			}
		}
	}
	
	public static void firstLevel(){
		//removes all unbreakable bricks in first level
		unBreackable.removeAll(unBreackable);
		//removes all bricks in first level
		brick.removeAll(brick);
		//creates new array list for breakable bricks
		brick =new ArrayList<Bricks>();
		////Double for loop that assigns breakable bricks in x and y-axis in first level
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
		    	Random rand = new Random();
		    	//makes sure that only every third brick is given a place in the first level
		        int randomNum = rand.nextInt(3);
		        if(randomNum==1){
		        	//Adds breakable bricks to new location
		        	brick.add(new Bricks(i,y,50,20));
				}
			}
		}
		//creates array list of unbreakable bricks
		unBreackable = new ArrayList<Bricks>();
		
		for(int i = 50; i<maxWidth -50; i+=100){
			for(int y=50; y < 200; y+=40){
		    	Random rand = new Random();
		    	//makes sure that only 1/7 unbreakable brick is given a place in the first level
		        int randomNum = rand.nextInt(7);
		        if(randomNum==1){
		        	//adds unbreakable bricks to new location
		        	unBreackable.add(new Bricks(i,y,50,20));
				}
			}
		}
	}
	
	
	public static void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Bricks b : brick){
			//sets color of bricks to pink
			g.setColor(Color.pink);
			//fills bricks with color
			g.fill(b);
		}
		for(Bricks b : unBreackable){
			//sets color of unbreakable bricks to red
			g.setColor(Color.red);
			//fills unbreakable bricks with color
			g.fill(b);
		}
	}
	
	public static void newLevel() {
		//if all not unbreakable bricks are destroyed, create new array list of bricks
		if(brick.size() == 0){
			//creates array list of bricks
			brick =new ArrayList<Bricks>();
			for(int i = 50; i<maxWidth -50; i+=50){
				for(int y=50; y < 200; y+=20){
			    	Random rand = new Random();
			    	//makes sure that only every second brick is given a place in new level
			        int randomNum = rand.nextInt(2);
			        if(randomNum==1){
			        	//adds breakable bricks to new location
			        	brick.add(new Bricks(i,y,50,20));
					}
				}
			}
			
			//removes all unbreakable bricks when new level starts
			unBreackable.removeAll(unBreackable);
			//creates new array list of unbreakable bricks when new level starts
			unBreackable = new ArrayList<Bricks>();
			for(int i = 50; i<maxWidth -50; i+=100){
				for(int y=50; y < 200; y+=40){
			    	Random rand = new Random();
			    	//makes sure that only 1/7 unbreakable brick is given a place
			        int randomNum = rand.nextInt(7);
			        if(randomNum==1){
			        	//adds unbreakable bricks to new location when new level starts
			        	unBreackable.add(new Bricks(i,y,50,20));
					}
				}
			}
			//increases level by 1
			currentLevel++;
			
		}
		
	}
	
	
	
	
	
}

