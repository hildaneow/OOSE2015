package example;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class PlayerBar extends Rectangle implements ICollidableObject {
	
	private static final long serialVersionUID = 1L;
	
	private float velocity = 0.5f;

	private float limit;
	private float dwidth;
	private float halfWidth;

	public PlayerBar(float x, int y, float width, float height) {
		super(x, y, width, height);
		this.halfWidth = width/2;
		dwidth = width;
	}
	
//	public void barSize(float width){
//		
//	}
/*	
	public void barSize(int delta) {
		float offset = delta * this.velocity;
		updatePosition(getCenterX(), offset);
	}
	private void updatePosition(float x, float offset){
		float newCenter = Math.min(Math.max((x+offset), this.halfWidth), this.limit-this.halfWidth); // how does this work
		setCenterX(newCenter);
	}
	
*/	

	public void moveLeft(int delta) {
		float offset = -delta * this.velocity;
		updatePosition(getCenterX(), offset);
	}

	public void moveRight(int delta) {
		float offset = delta * this.velocity;
		updatePosition(getCenterX(), offset);
	}

	public void setHorizontalLimit(float limit) {
		this.limit  = limit;
	}
	
	private void updatePosition(float x, float offset){
		float newCenter = Math.min(Math.max((x+offset), this.halfWidth), this.limit-this.halfWidth); // how does this work
		setCenterX(newCenter);
	}

	public Vector2f getBounceDirection(Ball ball) {
		Vector2f direction = ball.getDirection();
		if (ball.getMinX() < this.getMinX() || ball.getMaxX() > this.getMaxX()) {
			direction.x = -direction.x;
		} else {
			direction.y = -direction.y;
		}
		return direction;
	}

}
