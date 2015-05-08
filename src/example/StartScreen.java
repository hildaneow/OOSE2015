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
		startscreen = new Image("res/startscreen.jpg");
	}

	public static void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(startscreen, 0, 0);
		g.drawString("PRESS SPACE TO START", 240, 350);				
	}
	
	public static void applyPlayerInput(GameContainer gc, int delta) 
	{
		Input input = gc.getInput();	
		if(input.isKeyDown(Input.KEY_SPACE)){
			SimpleSlickGame.lives = 4;
			numberCounter.brickHit=0;
			LevelGenerator.firstLevel();
		}
	}

	
}

