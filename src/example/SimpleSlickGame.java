package example;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	
	private static int maxWidth = 640;
	private static int maxHeight = 480;
	private PlayerBar playerBar;
	private Ball ball;
	private List<Wall> walls;

	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		float width = 70f;
		float height = 20f;
		playerBar = new PlayerBar((maxWidth -  width)/2, maxHeight-100, width, height);
		playerBar.setHorizontalLimit(maxWidth);
		
		float ballRadius = 10f;
		ball = new Ball((maxWidth -  ballRadius)/2, maxHeight-200, ballRadius);
		
		walls = new ArrayList<Wall>();
		walls.add(new Wall(0f, 0f, 1f, maxHeight)); // left
		walls.add(new Wall(maxWidth, 0f, 1f, maxHeight)); // right
		walls.add(new Wall(0f, 0f, maxWidth, 1f)); // top
		walls.add(new Wall(0f, maxHeight, maxWidth, 1f)); // bottom
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		applyPlayerInput(gc, delta);
		updateBallPosition(delta);
	}
	
	private void updateBallPosition(int delta) {
		for (Wall w : walls) {
			if (w.intersects(ball)){
				ball.collide(w);
				break;
			}
		}
		ball.updatePosition(delta);
	}
	
	private void applyPlayerInput(GameContainer gc, int delta) {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_LEFT)){
			playerBar.moveLeft(delta);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			playerBar.moveRight(delta);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.draw(playerBar);
		g.draw(ball);
		System.out.println("Rendering" + playerBar.getX());
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(maxWidth, maxHeight, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}