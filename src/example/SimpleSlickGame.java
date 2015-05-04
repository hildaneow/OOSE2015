package example;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{

	public static final int menu = 0;
	public static final int play = 1;
	
	private static int maxWidth = 640;
	private static int maxHeight = 480;
	private PlayerBar playerBar;
	private Ball ball;
	private List<Wall> walls;
	private List<Bricks> brick;
	private int lives;
	private int score;
	private GameGUI GUI;
	private List<PowerUp> PU;

	public float width = 70f;
	public float height = 20f;
	
	float downTime = 4;
	boolean downT = false;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
		//this.addState(new Menu(menu));
		//this.addState(new Play(play));
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		playerBar = new PlayerBar((maxWidth -  width)/2, maxHeight-100, width, height);
		playerBar.setHorizontalLimit(maxWidth);
		
		PU = new ArrayList<PowerUp>();
		
		GUI = new GameGUI();
		
		brick =new ArrayList<Bricks>();
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
				brick.add(new Bricks(i,y,50,20));
			}
			
		}
			
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
		//playerBar.barSize(width);
		applyPlayerInput(gc, delta);
		updateBallPosition(delta);
		lives = GUI.gameLives(4);
		score = GUI.gameScore();
	}
	
	private void updateBallPosition(int delta) {
		if (playerBar.intersects(ball)) {
			ball.collide(playerBar);
		}
		else {
			for (Wall w : walls) {
				if (w.intersects(ball)){
					//System.out.println("hit wall");
					ball.collide(w);
					break;
				}
			}
		}
		//Remove brick, bounce ball and spawn powerup on ball-brick collision:
		for (Iterator<Bricks> it = brick.iterator(); it.hasNext(); ) {
		    Bricks b = it.next();
		    if (b.intersects(ball)) {
		        Random rand = new Random();
		        //how often should PowerUps occur:
		        int randomNum = rand.nextInt(3);
		    	if(randomNum == 1){
		    		//Spawn PowerUp at the position of the destroyed brick:
			    	int brickX;
			    	int brickY;
			    	brickX = b.PositionOfBrickX();
			    	brickY = b.PositionOfBrickY();
			    	PU.add(new PowerUp(brickX+25,brickY,5));
		    	}
		    	ball.collide(b);
		        it.remove();
		        break;
		    }
		}
		
		//remove PowerUp when it hits playerbar:
		for (Iterator<PowerUp> it = PU.iterator(); it.hasNext(); ) {
		    PowerUp p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=true;
		        width = p.shortenBar(width);
		        it.remove();
		        break;
		    }
		}
//Make the playerbar normalsize after x time (sek now) this should be moved!
		if(downT == true){
			downTime-=delta;
		}
		if(downTime < -8000){
			width = 70;
			downTime = 4;
			downT=false;
		}
		//update the position of objects if no intersection is detected:
		playerBar.setWidth(width);
		ball.updatePosition(delta);
		for(PowerUp p : PU){
			p.updatePosition(delta);
		}	
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
		for(Bricks b : brick){
			g.draw(b);;
		}
		g.drawString("LIFE:"+ lives, 540, 10);
		g.drawString("SCORE:" + score, 540, 30);
		for(PowerUp p : PU){
			g.draw(p);
		}
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
	