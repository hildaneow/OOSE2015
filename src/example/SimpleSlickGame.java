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
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SimpleSlickGame extends BasicGame
{

	private Sound sound;
	public LevelGenerator levelGenerator;
	public GameOver gameOver;
	private static int maxWidth = 640;
	private static int maxHeight = 480;
	private PlayerBar playerBar;
	private List<Wall> walls;
	//private List<Bricks> brick2;
	public static int lives = 4;
	private int score;
	private GameGUI GUI;
	
	private int myMod = 0;
	
	//make lists of differnt powerups
	private List<PowerUpExtendBar> PUEB;
	private List<PowerUpSmallerBar> PUSB;
	private List<PowerUpNewBall> PUNB;
	private List<PowerUpIncreaseSpeed> PUIS;
	private List<PowerUpDecreaseSpeed> PUDS;
	
	private List<Ball> balls;
	
	
	float ballRadius = 10f;
	
	public static int bufferTime = 0;
	public static int bufferTime2 = 0;

	public float width = 70f;
	public float height = 20f;
	
	float downTime = 4;
	static int downT = 0;
	
	private Wall buttomLine;
	
	
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		levelGenerator = new LevelGenerator();
		gameOver = new GameOver();
		sound = new Sound("res/pew.wav");
		
		playerBar = new PlayerBar((maxWidth -  width)/2, maxHeight-100, width, height);
		playerBar.setHorizontalLimit(maxWidth);
		
		PUEB = new ArrayList<PowerUpExtendBar>();
		PUSB = new ArrayList<PowerUpSmallerBar>();
		PUNB = new ArrayList<PowerUpNewBall>();
		PUIS = new ArrayList<PowerUpIncreaseSpeed>();
		PUDS = new ArrayList<PowerUpDecreaseSpeed>();
		
		GUI = new GameGUI();
		
		buttomLine = new Wall(0f, maxHeight, maxWidth, 1f);
		
		LevelGenerator.init();
		GameOver.init();
		//brick2 = new ArrayList<Bricks>();
		/*
		for(int i = 50; i<maxWidth -50; i+=50){
			for(int y=50; y < 200; y+=20){
				brick.add(new Bricks(i,y,50,20));
			}
		}
			*/

		
		balls = new ArrayList<Ball>();
		balls.add(new Ball((maxWidth -  ballRadius)/2, maxHeight-200, ballRadius));
		
		walls = new ArrayList<Wall>();
		walls.add(new Wall(0f, 0f, 1f, maxHeight)); // left
		walls.add(new Wall(maxWidth, 0f, 1f, maxHeight)); // right
		walls.add(new Wall(0f, 0f, maxWidth, 1f)); // top
		//walls.add(new Wall(0f, maxHeight, maxWidth, 1f)); // bottom
	}
	
		
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(lives > 0){
		applyPlayerInput(gc, delta);
		updateBallPosition(delta);
		brickCollision();
		powerUpCollision();
		levelGenerator.newLevel();
		}
		if(lives < 1){
			GameOver.applyPlayerInput(gc, delta);
		}
		
	}
	
	private void brickCollision(){
	//Remove brick, bounce ball and spawn powerup on ball-brick collision:
	
		for(Ball ba : balls){
			
			for(int i = 0; i < levelGenerator.unBreackable.size();i++) {
				Bricks b = levelGenerator.unBreackable.get(i);
				if (b.intersects(ba)) {
					ba.collide(b);
					break;
				}
			}
			
			
			
			for(int i = 0; i < levelGenerator.brick.size();i++) {
				
			Bricks b = levelGenerator.brick.get(i);	
		//for (Iterator<levelGenerator>().Bricks> it = brick.iterator(); it.hasNext(); ) {
		    //Bricks b = it.next();
		    if (b.intersects(ba)) {
		    	numberCounter.scoreCounter();
		    	Random rand = new Random();
		        //how often should PowerUps occur:
		        int randomNum = rand.nextInt(2);
		    	if(randomNum == 1){
		    		
		    		//Spawn PowerUp at the position of the destroyed brick:
			    	int brickX;
			    	int brickY;
			    	brickX = b.PositionOfBrickX();
			    	brickY = b.PositionOfBrickY();
			    	
			    	
			    	
			    	Random rand1 = new Random();
			        //how often should PowerUps occur:
			        int randomNum1 = rand1.nextInt(5 + LevelGenerator.currentLevel * 2);
			    	
			    	
			    	if(LevelGenerator.currentLevel >= 0){
			    		//randomNum1 = randomNum1 - LevelGenerator.currentLevel;
			    	if(randomNum1 == 1) {
			    		//SMALLER BAR
			    		PUNB.add(new PowerUpNewBall(brickX+25,brickY,5));
			    		myMod++;
			    	}
			    	
			    	else if(randomNum1 == 2){
			      		PUDS.add(new PowerUpDecreaseSpeed(brickX+25,brickY,5));
			    		myMod++;
			    	}
			    	else if(randomNum1 == 3){
			    		PUEB.add(new PowerUpExtendBar(brickX+25,brickY,5));
			    		myMod++;
			    	}
			    	else {
			    		Random rand2 = new Random();
			    		int random2 = rand2.nextInt(2);
			    		
			    		if(random2 == 0){
			    		PUIS.add(new PowerUpIncreaseSpeed(brickX+25,brickY,5));
			    		}
			    		if (random2 == 1){
			    		PUSB.add(new PowerUpSmallerBar(brickX+25,brickY,5));
			    		myMod++;
			    		}
			    	}
			    	
			    	}
			    	
		    	}
		    	ba.collide(b);
		    	sound.play();
		        //it.remove();
		    	levelGenerator.brick.remove(b);
		        break;
		    }
		}
		}
	}
	
	private void powerUpCollision(){
		//remove PowerUp when it hits playerbar:
		for (Iterator<PowerUpExtendBar> it = PUEB.iterator(); it.hasNext(); ) {
			PowerUpExtendBar p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=1;
		        it.remove();
		        break;
		    }
		}
		for (Iterator<PowerUpSmallerBar> it = PUSB.iterator(); it.hasNext(); ) {
			PowerUpSmallerBar p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=2;
		        it.remove();
		        break;
		    }
		}
		for (Iterator<PowerUpNewBall> it = PUNB.iterator(); it.hasNext(); ) {
			PowerUpNewBall p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=3;
		        it.remove();
		        break;
		    }
		}
		for (Iterator<PowerUpIncreaseSpeed> it = PUIS.iterator(); it.hasNext(); ) {
			PowerUpIncreaseSpeed p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=4;
		        it.remove();
		        break;
		    }
		}
		for (Iterator<PowerUpDecreaseSpeed> it = PUDS.iterator(); it.hasNext(); ) {
			PowerUpDecreaseSpeed p = it.next();
		    if (p.intersects(playerBar)) {
		    	downT=5;
		        it.remove();
		        break;
		    }
		}
		
		
	}
	
	
	private void updateBallPosition(int delta) {
		
		for (Iterator<Ball> it = balls.iterator(); it.hasNext(); ) {
			Ball bo = it.next();
		    if (bo.intersects(buttomLine)) {
		    	lives--;
		    	numberCounter.minusBallCount();
		    	it.remove();
		    	if(numberCounter.numberOfBalls == 0){
		    		numberCounter.plusBallCount();
		    		balls.add(new Ball(playerBar.getX(),playerBar.getY(), ballRadius));
		    	}
		    	break;	
		    }
		}
			
		
		for(Ball ba : balls){
		if (playerBar.intersects(ba)) {
			ba.collide(playerBar);
		}
		//else if (buttomLine.intersects(ba)){

	
		else {
			for (Wall w : walls) {
				if (w.intersects(ba)){
					ba.collide(w);
					break;
				}
			}
		}
		}

//Make the playerbar normalsize after x time (sek now)... this should be moved!
		if(downT == 1){
			width = PlayerBar.extendBar(delta, 5000);
			if(width == 70){
				downT = 0;
			}
		}
		if(downT == 2){
			width = PlayerBar.SmallerBar(delta, 5000);
			if(width == 70){
				downT = 0;
			}
		}
		if(downT == 3){
			balls.add(new Ball(playerBar.getX(),playerBar.getY(), ballRadius));
			numberCounter.plusBallCount();
			//System.out.println(numberCounter.numberOfBalls);
			downT = 0;
		}
		if(downT == 4){
			Ball.velocity *=1.5f;
			downT = 0;
		}
		if(downT == 5){
			Ball.velocity *=0.75f;
			downT = 0;
		}
		
		
		//update the position of objects if no intersection is detected:
		playerBar.setWidth(width);
		//balls.updatePosition(delta);
		for(Ball b : balls){
			b.updatePosition(delta);
		}
		
		for(PowerUpExtendBar p : PUEB){
			p.updatePosition(delta);
		}
		for(PowerUpSmallerBar p : PUSB){
			p.updatePosition(delta);
		}	
		for(PowerUpNewBall p : PUNB){
			p.updatePosition(delta);
		}	
		for(PowerUpIncreaseSpeed p : PUIS){
			p.updatePosition(delta);
		}	
		for(PowerUpDecreaseSpeed p : PUDS){
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
		g.setColor(Color.blue);
		g.fill(playerBar);

		g.setColor(Color.white);
		for(Ball b : balls){
			g.fill(b);
		}
		
		LevelGenerator.render( gc, g);
	//	for(Bricks b : brick){
	//		g.draw(b);
	//	}
		g.drawString("LIFE:"+ lives, 540, 10);
		g.drawString("SCORE:" + numberCounter.brickHit, 540, 30);
		for(PowerUpExtendBar p : PUEB){
			g.setColor(Color.green);
			g.draw(p);
		}
		for(PowerUpSmallerBar p : PUSB){
			g.setColor(Color.red);
			g.draw(p);
		}
		for(PowerUpNewBall p : PUNB){
			g.setColor(Color.orange);
			g.draw(p);
		}
		for(PowerUpIncreaseSpeed p : PUIS){
			g.setColor(Color.yellow);
			g.draw(p);
		}
		for(PowerUpDecreaseSpeed p : PUDS){
			g.setColor(Color.cyan);
			g.draw(p);
		}
		
		if(lives <= 0){
			GameOver.render(gc, g);
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
	