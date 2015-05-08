package example;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author RasmusMadsen
 *
 */
public class Ball extends Circle {

	private static final long serialVersionUID = 1L;
	
	private Vector2f direction;
	public static float velocity = 0.05f;

	/**
	 * Creates a new ball and gives it a direction vector.
	 * @param centerPointX
	 * @param centerPointY
	 * @param radius
	 */
	public Ball(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		direction = new Vector2f(-5f, -9f); //this should not be the same everytime
	}
	
	
	/**
	 * Takes a collidable object and changes the direction
	 *  to the bounce direction off that collidable object
	 * @param w
	 */
	public void collide(ICollidableObject w) {
		direction = w.getBounceDirection(this);
	}
	
	/**
	 * updates the position of an object
	 * @param delta
	 */
	public void updatePosition(int delta) {
		float x = getCenterX();
		float y = getCenterY();
		setCenterX(x + this.velocity*this.direction.x*delta);
		setCenterY(y + this.velocity*this.direction.y*delta);
	}
	
	/**
	 * returns the direction
	 * @return
	 */
	public Vector2f getDirection(){
		return this.direction;
	}
	
	
	

}
