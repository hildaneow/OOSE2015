package example;

import org.newdawn.slick.geom.Rectangle;

public class PlayerBar extends Rectangle {
	
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
		float newCenter = Math.min(Math.max((x+offset), this.halfWidth), this.limit-this.halfWidth);
		setCenterX(newCenter);
	}

}
