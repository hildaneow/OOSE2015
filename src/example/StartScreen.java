package example;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StartScreen {
	static Image startscreen;	
	
	public static void StartGame () throws SlickException
	{
		//initializes and declares start screen image
		startscreen = new Image("SoundsImagaes/startscreen.jpg");
	}

	public static void render(GameContainer gc, Graphics g) throws SlickException
	{
		//Creates start screen image
		g.drawImage(startscreen, 0, 0);
		//Creates string with "PRESS SPACE TO START" for start screen"
		g.drawString("PRESS SPACE TO START", 240, 350);				
	}
	
	public static void applyPlayerInput(GameContainer gc, int delta) 
	{
		//
		Input input = gc.getInput();	
		//Creates level, resets lives and resets number of bricks hit
		if(input.isKeyDown(Input.KEY_SPACE)){
			SimpleSlickGame.lives = 4;
			numberCounter.brickHit=0;
			LevelGenerator.firstLevel();
		}
	}

	
}

