package example;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends Circle {

	private static final long serialVersionUID = 1L;
	
	private Vector2f direction;
	private float velocity = 0.01f;

	public Ball(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		direction = new Vector2f(-5f, -9f);
	}

	public void collide(Wall w) {
		direction = w.getBounceDirection(direction);
	}
	
	public void updatePosition(int delta) {
		float x = getCenterX();
		float y = getCenterY();
		setCenterX(x + this.velocity*this.direction.x*delta);
		setCenterY(y + this.velocity*this.direction.y*delta);
	}

}
