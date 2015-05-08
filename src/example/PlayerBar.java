package example;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class PlayerBar extends Rectangle implements ICollidableObject {
	
	private static final long serialVersionUID = 1L;
	
	private float velocity = 0.5f;

	private float limit;
	private float halfWidth;

	public PlayerBar(float x, int y, float width, float height) {
		super(x, y, width, height);
		this.halfWidth = width/2;
	}
	
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
	
	public static float extendBar(int delta, int buffTime){
		SimpleSlickGame.bufferTime += delta;
		if(SimpleSlickGame.bufferTime < buffTime){
			return 150;
		}
		SimpleSlickGame.bufferTime = 0;
		return 100;
	}
	
	public static float SmallerBar(int delta, int buffTime){
		SimpleSlickGame.bufferTime += delta;
		if(SimpleSlickGame.bufferTime < buffTime){
			return 50;
		}
		SimpleSlickGame.bufferTime = 0;
		return 100;
	}
	
	
	

}
