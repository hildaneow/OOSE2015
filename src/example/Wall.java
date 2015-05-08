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
		
			
		
		Vector2f direction = ball.getDirection();
			
		if (horizontal) {			
			direction.y = -direction.y;
		} else {
			direction.x = -direction.x;
		}
			
		return direction;
		}
	}


