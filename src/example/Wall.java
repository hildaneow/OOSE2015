package example;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Wall extends Rectangle implements ICollidableObject {

	private static final long serialVersionUID = 1L;
	
	private boolean horizontal;

	public Wall(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.horizontal = width > height;
	}

	public Vector2f getBounceDirection(Ball ball) {
		
			
		//vector for ball direction
		Vector2f direction = ball.getDirection();
			//changes y direction
		if (horizontal) {			
			direction.y = -direction.y;
		} else {
			//changes x direction
			direction.x = -direction.x;
		}
			
		return direction;
		}
	}


