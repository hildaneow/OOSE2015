package example;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;


public class Ball extends Circle {

	private static final long serialVersionUID = 1L;
	
	//declare a vector for ball direction
	private Vector2f direction;
	//declares and initializes velocity for ball
	public static float velocity = 0.05f;

	/**
	 * Creates a new ball and gives it a direction vector.
	 * @param centerPointX
	 * @param centerPointY
	 * @param radius
	 */
	public Ball(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		//initial direction for ball, when game starts
		direction = new Vector2f(-5f, -9f); 
	}
	
	
	/**
	 * Takes a collidable object and changes the direction
	 *  to the bounce direction off that collidable object
	 * @param w
	 */
	public void collide(ICollidableObject w) {
		//gets new direction vector from collision
		direction = w.getBounceDirection(this);
	}
	
	/**
	 * updates the position of an object
	 * @param delta
	 */
	public void updatePosition(int delta) {
		float x = getCenterX();
		float y = getCenterY();
		//sets center to the new center for ball
		setCenterX(x + this.velocity*this.direction.x*delta);
		setCenterY(y + this.velocity*this.direction.y*delta);
	}
	
	/**
	 * returns the direction
	 * @return
	 */
	public Vector2f getDirection(){
		//returns direction if ball has not collided with anything
		return this.direction;
	}
	
	
	

}
