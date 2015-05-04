package example;
import org.newdawn.slick.*;

//import java.util.logging.Level;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Bricks extends Rectangle implements ICollidableObject{
	
	
	private boolean horizontal;
	private static final long serialVersionUID = 1L;
	
	public Bricks(float x, int y, float width, float height){
		super(x, y, width, height);
		this.horizontal = width > height; // how does this work?
	}
		
	public Vector2f getBounceDirection(Ball ball) {
		Vector2f direction = ball.getDirection();
		if (horizontal) {
			direction.y = -direction.y;
		} else {
			direction.x = -direction.x;
		}
		
		return direction;
	}
	
	//public int PositionOfBrick(){
		//return x;
	//}
	
	

}
