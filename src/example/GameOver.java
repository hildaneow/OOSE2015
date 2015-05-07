package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameOver {

	
	static Image endGame;
	public static LevelGenerator levelGenerator;
	public static List<Bricks> brick;
	public static List<Bricks> brick2;
	private static int maxWidth = 640;
	private static int maxHeight = 480;
	
	private static Bricks bigBrick;
	
	public static void init() {
		
		levelGenerator = new LevelGenerator();
		
		bigBrick = new Bricks(100,350,450, 100);
		brick =new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth-50; i+=100){
			for(int y=50; y < maxHeight-50; y+=40){
		        	brick.add(new Bricks(i,y,50,20));
				}
			}
		

			try {
				endGame = new Image("res/Endtext.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		}
	

	public static void applyPlayerInput(GameContainer gc, int delta) {
		Input input = gc.getInput();	
		if(input.isKeyDown(Input.KEY_SPACE)){
			SimpleSlickGame.lives = 4;
			numberCounter.brickHit=0;
			levelGenerator.firstLevel();
		}
	}



	
	public static void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Bricks b : brick){
			g.setColor(Color.gray);
			g.fill(b);
		}
		g.drawImage(endGame, 100, 100);
		g.setColor(Color.darkGray);
		g.fill(bigBrick);
		g.setColor(Color.white);
		g.drawString("Your score was:", 240, 350);
		g.drawString(""+numberCounter.brickHit, 300,375);
		g.drawString("Press SPACE to restart",220, 420);
		
	}
	
	
	
	
}
