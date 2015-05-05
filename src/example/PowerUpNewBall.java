package example;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class PowerUpNewBall extends Circle{
	
	private Vector2f direction;
	private float velocity = 0.01f;

	public PowerUpNewBall(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		direction = new Vector2f(0f, 9f);
	}
	public void updatePosition(int delta) {
		float x = getCenterX();
		float y = getCenterY();
		setCenterX(x + this.velocity*this.direction.x*delta);
		setCenterY(y + this.velocity*this.direction.y*delta);
	}

}
