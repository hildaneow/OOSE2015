package example;
import org.newdawn.slick.*;

//import java.util.logging.Level;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Bricks extends Rectangle implements ICollidableObject{
	
	

	private static final long serialVersionUID = 1L;
	
	public Bricks(float x, int y, float width, float height){
		super(x, y, width, height);
		
	}
		
	/* 
	 * @see example.ICollidableObject#getBounceDirection(example.Ball)
	 */
	public Vector2f getBounceDirection(Ball ball) {
		Vector2f direction = ball.getDirection();
		direction.y = -direction.y;
		
		
		return direction;
	}
	
	/**
	 * returns the x of a specific brick.
	 * @return
	 */
	public int PositionOfBrickX(){
		int xX;
		xX = (int) this.x;
		return xX;
	}
	/**
	 * returns the y of a specific brick
	 * @return
	 */
	public int PositionOfBrickY(){
		int yY;
		yY = (int) this.y;
		return yY;
	}
	

}
